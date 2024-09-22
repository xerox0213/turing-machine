package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing a repetitions verifier for the Turing Machine game.
 * This verifier checks for repetitions in a given code and categorizes them as NONE, DOUBLE, or TRIPLE.
 */
public class RepetitionsVerifier extends Verifier {

    /**
     * The categories representing the repetition count in the code.
     */
    private enum Category {
        NONE,
        DOUBLE,
        TRIPLE
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs a RepetitionsVerifier with the specified secret code.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     */
    public RepetitionsVerifier(String verifierNo, Code secretCode) {
        super(verifierNo);
        secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category based on the repetition count in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the repetition count in the provided code.
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

        if (counter == 0) return Category.NONE;
        else if (counter == 1) return Category.DOUBLE;
        else return Category.TRIPLE;
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
