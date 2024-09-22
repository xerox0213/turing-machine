package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing an order verifier for the Turing Machine game.
 * This verifier checks if the three digits in a given code are in ascending or descending order.
 */
public class OrderVerifier extends Verifier {

    /**
     * The categories representing the order of digits in the code.
     */
    private enum Category {
        NONE,
        ASCENDING,
        DESCENDING
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs an OrderVerifier with the specified secret code.
     *
     * @param verifierNo  The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     */
    public OrderVerifier(String verifierNo, Code secretCode) {
        super(verifierNo);
        secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category based on the order of digits in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the order of digits in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int sign = Integer.signum(digits[1] - digits[0]);
        for (int i = 2; i < digits.length; i++) {
            int newSign = Integer.signum(digits[i] - digits[i - 1]);
            if (sign == 0 || newSign != sign) return Category.NONE;
        }

        if (sign == 1) return Category.ASCENDING;
        else return Category.DESCENDING;
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
