package g61453.atl.model.verifiers;

import g61453.atl.model.Code;

/**
 * An abstract class representing a verifier for the Turing Machine game.
 * Verifiers are responsible for checking if a given code belongs to a specific category
 * based on a predefined secret code category. This class serves as a template for various verifiers
 * that will be implemented in the Turing Machine game.
 */
public abstract class Verifier {
    /**
     * The unique identifier for this verifier.
     */
    private final String verifierNo;

    /**
     * Constructs a Verifier with the specified verifier number.
     *
     * @param verifierNo The unique identifier for this verifier.
     */
    public Verifier(String verifierNo) {
        this.verifierNo = verifierNo;
    }

    /**
     * Gets the verifier number.
     *
     * @return The unique identifier for this verifier.
     */
    public String getVerifierNo() {
        return verifierNo;
    }

    /**
     * Checks if a provided code belongs to the secret code category.
     *
     * @param code The code to be checked.
     * @return True if the code belongs to the secret code category, otherwise false.
     */
    public abstract boolean check(Code code);
}
