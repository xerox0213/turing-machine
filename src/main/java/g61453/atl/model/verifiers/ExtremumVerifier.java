package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing an extremum verifier for the Turing Machine game.
 * This verifier checks if a specified digit in the given code represents the minimum or maximum value
 * among all digits, and categorizes the code accordingly.
 */
public class ExtremumVerifier extends Verifier {

    /**
     * Enumeration representing the categories for the extremum comparison.
     * Categories include NONE, FIRST, SECOND, and THIRD.
     */
    private enum Category {
        NONE,
        FIRST,
        SECOND,
        THIRD
    }

    /**
     * Enumeration representing the extremum types, either MIN or MAX.
     */
    public enum Extremum {
        MIN,
        MAX
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * The extremum type, either MIN or MAX.
     */
    private final Extremum extremum;

    /**
     * Constructs an ExtremumVerifier with the specified secret code and extremum type.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param code       The secret code against which to check codes.
     * @param extremum   The extremum type, either MIN or MAX.
     */
    public ExtremumVerifier(String verifierNo, Code code, Extremum extremum) {
        super(verifierNo);
        this.extremum = extremum;
        this.secretCodeCategory = determineCategory(code);
    }

    /**
     * Determines the category based on the extremum comparison in the given code.
     * Categories include NONE, FIRST, SECOND, and THIRD.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the extremum comparison in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int comparator = digits[0];
        int digitIndex = 0;

        for (int i = 1; i < digits.length; i++) {
            int digit = digits[i];
            if (comparator == digit) {
                digitIndex = -1;
                continue;
            }

            if (extremum == Extremum.MIN) {
                if (digit < comparator) digitIndex = i;
                comparator = Math.min(comparator, digit);
            } else if (extremum == Extremum.MAX) {
                if (digit > comparator) digitIndex = i;
                comparator = Math.max(comparator, digit);
            }
        }

        if (digitIndex == -1) return Category.NONE;
        else if (digitIndex == 0) return Category.FIRST;
        else if (digitIndex == 1) return Category.SECOND;
        else return Category.THIRD;
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
