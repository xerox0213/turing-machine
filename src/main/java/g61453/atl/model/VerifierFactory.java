package g61453.atl.model;

import g61453.atl.model.verifiers.*;

/**
 * A factory implementation for creating verifiers in the Turing Machine game.
 * This factory follows the Factory interface and provides a concrete implementation
 * for creating various verifier instances based on the specified verifier number and secret code value.
 */
public class VerifierFactory implements Factory {

    /**
     * The secret code value used as a basis for verifier creation.
     */
    private final String secretCodeValue;

    /**
     * Constructs a VerifierFactory with the specified secret code value.
     *
     * @param secretCodeValue The secret code value against which verifiers will be created.
     */
    public VerifierFactory(String secretCodeValue) {
        this.secretCodeValue = secretCodeValue;
    }

    /**
     * Creates a verifier based on the provided verifier number and secret code value.
     *
     * @param verifierNo The number identifying the type of verifier to be created.
     * @return A new instance of the Verifier interface corresponding to the given verifier number.
     * @throws TuringMachineException if the specified verifier number is invalid or not supported.
     */
    @Override
    public Verifier createVerifier(String verifierNo) {
        Code secretCode = new Code(secretCodeValue);
        return switch (verifierNo) {
            case "1" -> new DigitVerifier(verifierNo, secretCode, 0, 1);
            case "2" -> new DigitVerifier(verifierNo, secretCode, 0, 3);
            case "3" -> new DigitVerifier(verifierNo, secretCode, 1, 3);
            case "4" -> new DigitVerifier(verifierNo, secretCode, 1, 4);
            case "5" -> new ParityVerifier(verifierNo, secretCode, 0);
            case "6" -> new ParityVerifier(verifierNo, secretCode, 1);
            case "7" -> new ParityVerifier(verifierNo, secretCode, 2);
            case "8" -> new OccurrenceVerifier(verifierNo, secretCode, 1);
            case "9" -> new OccurrenceVerifier(verifierNo, secretCode, 3);
            case "10" -> new OccurrenceVerifier(verifierNo, secretCode, 4);
            case "11" -> new DoubleDigitComparerVerifier(verifierNo, secretCode, 0, 1);
            case "12" -> new DoubleDigitComparerVerifier(verifierNo, secretCode, 0, 2);
            case "13" -> new DoubleDigitComparerVerifier(verifierNo, secretCode, 1, 2);
            case "14" -> new ExtremumVerifier(verifierNo, secretCode, ExtremumVerifier.Extremum.MIN);
            case "15" -> new ExtremumVerifier(verifierNo, secretCode, ExtremumVerifier.Extremum.MAX);
            case "16" -> new FrequentlyParityVerifier(verifierNo, secretCode);
            case "17" -> new EvenDigitCounterVerifier(verifierNo, secretCode);
            case "18" -> new DigitSumParityVerifier(verifierNo, secretCode);
            case "19" -> new DigitSumVerifier(verifierNo, secretCode);
            case "20" -> new RepetitionsVerifier(verifierNo, secretCode);
            case "21" -> new DoubleDigitVerifier(verifierNo, secretCode);
            case "22" -> new OrderVerifier(verifierNo, secretCode);
            default -> throw new TuringMachineException("The verifier doesn't exist");
        };
    }
}
