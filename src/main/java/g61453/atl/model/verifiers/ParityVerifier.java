package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a parity-based verifier for the Turing Machine game.
 * Parity verifiers check if a specific digit of a given code is even or odd.
 * This class extends the general Verifier class and provides functionality for parity-based verification.
 */
public class ParityVerifier extends Verifier {

    /**
     * Enum representing possible categories for the parity-based verifier.
     * Categories include ODD or EVEN based on the parity of the specified digit.
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
     * The index of the digit in the code to be verified for parity.
     */
    private final int digitIndex;

    /**
     * Constructs a ParityVerifier with the specified secret code and digit index.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param code       The secret code against which to check codes.
     * @param digitIndex The index of the digit in the code to be verified for parity.
     */
    public ParityVerifier(String verifierNo, Code code, int digitIndex) {
        super(verifierNo);
        this.digitIndex = digitIndex;
        this.secretCodeCategory = determineCategory(code);
    }

    /**
     * Determines the category of the specified digit in the given code based on its parity.
     * Categories include EVEN or ODD.
     *
     * @param code The code for which to determine the category.
     * @return The category of the specified digit in the provided code based on its parity.
     */
    private Category determineCategory(Code code) {
        int digit = code.getDigitAtIndex(digitIndex);
        if (digit % 2 == 0) return Category.EVEN;
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
