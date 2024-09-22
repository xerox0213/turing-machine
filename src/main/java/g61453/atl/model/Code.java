package g61453.atl.model;

/**
 * A class representing a code in the Turing Machine game.
 * The code is a sequence of digits.
 */
public class Code {

    /**
     * The string representation of the code.
     */
    private final String code;

    /**
     * Constructs a Code object with the specified string code.
     *
     * @param code The string representation of the code.
     */
    public Code(String code) {
        this.code = code;
    }

    /**
     * Gets the digit at the specified index in the code.
     *
     * @param index The index of the digit to retrieve.
     * @return The numeric value of the digit at the specified index.
     */
    public int getDigitAtIndex(int index) {
        return Character.getNumericValue(code.charAt(index));
    }

    /**
     * Gets an array containing all digits of the code.
     *
     * @return An array containing all digits of the code.
     */
    public int[] getDigits() {
        int[] digits = new int[3];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = getDigitAtIndex(i);
        }
        return digits;
    }
}
