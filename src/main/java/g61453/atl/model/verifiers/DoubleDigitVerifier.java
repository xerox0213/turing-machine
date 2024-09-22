package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a double-digit verifier for the Turing Machine game.
 * This verifier checks if a digit in a given code appears exactly two times (but not three).
 */
public class DoubleDigitVerifier extends Verifier {

    /**
     * The categories representing the presence of a double digit in the code.
     */
    private enum Category {
        TRUE,
        FALSE
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs a DoubleDigitVerifier with the specified secret code.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     */
    public DoubleDigitVerifier(String verifierNo, Code secretCode) {
        super(verifierNo);
        secretCodeCategory = determineCategory(secretCode);
    }


    /**
     * Determines the category based on the presence of a double digit in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the presence of a double digit in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int counter = 0;
        for (int i = 0; i < digits.length; i++) {
            for (int j = i + 1; j < digits.length; j++) {
                if (digits[i] == digits[j]) counter++;
            }
            if (counter >= 1) break;
        }

        if (counter == 1) return Category.TRUE;
        else return Category.FALSE;
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
