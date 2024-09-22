package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a digit sum verifier for the Turing Machine game.
 * This verifier compares the sum of the first and second digits in a given code with the value 6.
 * Categories include SMALLER, LARGER, and EQUAL based on the comparison result.
 */
public class DigitSumVerifier extends Verifier {

    /**
     * The categories representing the comparison result of the digit sum.
     */
    private enum Category {
        SMALLER,
        LARGER,
        EQUAL
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs a DigitSumVerifier with the specified secret code.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     */
    public DigitSumVerifier(String verifierNo, Code secretCode) {
        super(verifierNo);
        secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category based on the comparison result of the digit sum in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the comparison result of the digit sum in the provided code.
     */
    private Category determineCategory(Code code) {
        int firstDigit = code.getDigitAtIndex(0);
        int secondDigit = code.getDigitAtIndex(1);
        int sum = firstDigit + secondDigit;
        if (sum > 6) return Category.LARGER;
        else if (sum < 6) return Category.SMALLER;
        else return Category.EQUAL;
    }

    /**
     * Checks if the provided code belongs to the secret code category.
     *
     * @param code The code to be checked.
     * @return True if the code belongs to the secret code category, otherwise false.
     */
    public boolean check(Code code) {
        return determineCategory(code) == secretCodeCategory;
    }
}
