package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a comparer verifier for the Turing Machine game.
 * This verifier compares two specified digits in a given code.
 * The comparison categories include SMALLER, EQUAL, and LARGER.
 */
public class DoubleDigitComparerVerifier extends Verifier {

    /**
     * Enumeration representing the comparison categories for the double-digit comparison.
     * Categories include SMALLER, EQUAL, and LARGER.
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
     * The index of the first digit to be compared.
     */
    private final int firstDigitIndex;

    /**
     * The index of the second digit to be compared.
     */
    private final int secondDigitIndex;

    /**
     * Constructs a ComparerVerifier with the specified secret code, first digit index, and second digit index.
     *
     * @param verifierNo       The unique identifier for this verifier.
     * @param secretCode       The secret code against which to check codes.
     * @param firstDigitIndex  The index of the first digit to be compared.
     * @param secondDigitIndex The index of the second digit to be compared.
     */
    public DoubleDigitComparerVerifier(String verifierNo, Code secretCode, int firstDigitIndex, int secondDigitIndex) {
        super(verifierNo);
        this.firstDigitIndex = firstDigitIndex;
        this.secondDigitIndex = secondDigitIndex;
        this.secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category of the specified digits in the given code.
     * Categories include SMALLER, EQUAL, and LARGER based on the comparison.
     *
     * @param code The code for which to determine the category.
     * @return The category of the specified digits in the provided code.
     */
    private Category determineCategory(Code code) {
        int digit1 = code.getDigitAtIndex(firstDigitIndex);
        int digit2 = code.getDigitAtIndex(secondDigitIndex);
        if (digit1 > digit2) return Category.LARGER;
        else if (digit1 < digit2) return Category.SMALLER;
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
