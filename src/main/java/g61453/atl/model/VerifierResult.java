package g61453.atl.model;

/**
 * A record representing the result of a verifier in the Turing Machine game.
 * It includes the verifier number and a boolean result indicating whether the code passed the verification.
 */
public record VerifierResult(String verifierNo, boolean result) {
}
