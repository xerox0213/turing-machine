package g61453.atl.model;

import g61453.atl.model.verifiers.Verifier;

/**
 * An interface representing a factory for creating verifiers in the Turing Machine game.
 * Implementations of this interface are responsible for creating instances of the Verifier interface
 * based on the specified verifier number and secret code value.
 */
public interface Factory {

    /**
     * Creates a verifier based on the provided verifier number and secret code value.
     *
     * @param verifierNo      The number identifying the type of verifier to be created.
     * @return A new instance of the Verifier interface.
     */
    Verifier createVerifier(String verifierNo);
}
