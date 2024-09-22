package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing an even digit counter verifier for the Turing Machine game.
 * This verifier counts the number of even digits in a given code.
 * Categories include NONE, ONE, TWO, and THREE based on the count of even digits.
 */
public class EvenDigitCounterVerifier extends Verifier {

    /**
     * The categories representing the count of even digits.
     */
    private enum Category {
        NONE,
        ONE,
        TWO,
        THREE
    }

    /**
     * The secret code category against which codes will be checked.
     */
    private final Category secretCodeCategory;

    /**
     * Constructs an EvenDigitCounterVerifier with the specified secret code.
     *
     * @param verifierNo The unique identifier for this verifier.
     * @param secretCode The secret code against which to check codes.
     */
    public EvenDigitCounterVerifier(String verifierNo, Code secretCode) {
        super(verifierNo);
        secretCodeCategory = determineCategory(secretCode);
    }

    /**
     * Determines the category based on the count of even digits in the given code.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the count of even digits in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int counter = 0;
        for (int digit : digits) {
            if (digit % 2 == 0) counter++;
        }
        if (counter == 0) return Category.NONE;
        else if (counter == 1) return Category.ONE;
        else if (counter == 2) return Category.TWO;
        else return Category.THREE;
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
