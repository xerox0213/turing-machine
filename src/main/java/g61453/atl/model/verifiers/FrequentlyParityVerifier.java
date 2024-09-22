package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a frequently occurring parity verifier for the Turing Machine game.
 * This verifier determines the majority parity (even or odd) in a given code.
 * Categories include MAJORITY_ODD and MAJORITY_EVEN based on the more frequently occurring parity.
 */
public class FrequentlyParityVerifier extends Verifier {

    /**
     * The categories representing the majority parity.
     */
    private enum Category {
        MAJORITY_ODD,
        MAJORITY_EVEN
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs a FrequentlyParityVerifier with the specified secret code.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param code       The secret code against which to check codes.
     */
    public FrequentlyParityVerifier(String verifierNo, Code code) {
        super(verifierNo);
        secretCodeCategory = determineCategory(code);
    }


    /**
     * Determines the category based on the majority parity in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the majority parity in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int oddCounter = 0;
        int evenCounter = 0;
        for (int digit : digits) {
            if (digit % 2 == 0) evenCounter++;
            else oddCounter++;
        }
        if (evenCounter > oddCounter) return Category.MAJORITY_EVEN;
        else return Category.MAJORITY_ODD;
    }

    /**
     * Checks if the provided code belongs to the secret code category.
     *
     * @param code The code to be checked.
     * @return True if the code belongs to the secret code category, otherwise false.
     */
    @Override
    public boolean check(Code code) {
        return determineCategory(code) == secretCodeCategory;
    }
}
