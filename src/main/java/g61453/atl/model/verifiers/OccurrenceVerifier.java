package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * A class representing an occurrence-based verifier for the Turing Machine game.
 * Occurrence verifiers check the number of occurrences of a specific value in a given code.
 * This class extends the general Verifier class and provides functionality for occurrence-based verification.
 */
public class OccurrenceVerifier extends Verifier {

    /**
     * Enum representing possible categories for the occurrence-based verifier.
     * Categories include NONE, ONE, TWO, or THREE based on the number of occurrences of the target value.
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
     * The target value for which occurrences are checked.
     */
    private final int targetValue;

    /**
     * Constructs an OccurrenceVerifier with the specified secret code and target value.
     *
     * @param verifierNo  The unique identifier for this verifier.
     * @param code        The secret code against which to check codes.
     * @param targetValue The value for which occurrences are checked.
     */
    public OccurrenceVerifier(String verifierNo, Code code, int targetValue) {
        super(verifierNo);
        this.targetValue = targetValue;
        this.secretCodeCategory = determineCategory(code);
    }

    /**
     * Determines the category based on the number of occurrences of the target value in the given code.
     * Categories include NONE, ONE, TWO, or THREE.
     *
     * @param code The code for which to determine the category.
     * @return The category based on the number of occurrences of the target value in the provided code.
     */
    private Category determineCategory(Code code) {
        int[] digits = code.getDigits();
        int counter = 0;
        for (int digit : digits) {
            if (digit == targetValue) counter++;
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
