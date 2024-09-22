package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a digit-based verifier for the Turing Machine game.
 * Digit verifiers check a specific digit in a given code against a specified value.
 * This class extends the general Verifier class and provides functionality for digit-based verification.
 */
public class DigitVerifier extends Verifier {

    /**
     * Enum representing possible categories for the digit-based verifier.
     * Categories include EQUAL, LARGER, or SMALLER based on a specified value.
     */
    private enum Category {
        EQUAL,
        LARGER,
        SMALLER
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * The index of the digit in the code to be verified.
     */
    private final int digitIndex;

    /**
     * The value against which the specified digit is compared.
     */
    private final int value;

    /**
     * Constructs a DigitVerifier with the specified secret code, digit index, and comparison value.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     * @param digitIndex The index of the digit in the code to be verified.
     * @param value      The value against which the specified digit is compared.
     */
    public DigitVerifier(String verifierNo, Code secretCode, int digitIndex, int value) {
        super(verifierNo);
        this.digitIndex = digitIndex;
        this.value = value;
        this.secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category of the specified digit in the given code.
     * Categories include LARGER, SMALLER, and EQUAL based on a specified value.
     *
     * @param code The code for which to determine the category.
     * @return The category of the specified digit in the provided code.
     */
    private Category determineCategory(Code code) {
        int digit = code.getDigitAtIndex(digitIndex);
        if (digit > value) return Category.LARGER;
        else if (digit < value) return Category.SMALLER;
        else return Category.EQUAL;
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
