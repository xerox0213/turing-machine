package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a digit sum parity verifier for the Turing Machine game.
 * This verifier checks if the sum of the digits in a given code is even or odd.
 * Categories include ODD and EVEN based on the digit sum.
 */
public class DigitSumParityVerifier extends Verifier {

    /**
     * The categories representing the parity of the digit sum.
     */
    private enum Category {
        ODD,
        EVEN
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs a DigitSumParityVerifier with the specified secret code.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     */
    public DigitSumParityVerifier(String verifierNo, Code secretCode) {
        super(verifierNo);
        secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category based on the parity of the digit sum in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the parity of the digit sum in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int sum = 0;
        for (int digit : digits) sum += digit;
        if (sum % 2 == 0) return Category.EVEN;
        else return Category.ODD;
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
