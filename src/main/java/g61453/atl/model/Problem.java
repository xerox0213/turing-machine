package g61453.atl.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a problem in the Turing Machine game.
 * Each problem is identified by a unique problem number and consists of various attributes
 * such as luck, difficulty, a list of verifier numbers, and a secret code.
 */
public class Problem {

    /**
     * The unique problem number.
     */
    private final String problemNo;

    /**
     * The luck associated with the problem.
     */
    private final String luck;

    /**
     * The difficulty level of the problem.
     */
    private final String difficulty;

    /**
     * The list of verifier numbers associated with the problem.
     */
    private final List<String> verifierNos;

    /**
     * The secret code associated with the problem.
     */
    private final String secretCode;


    /**
     * Constructs a Problem with the specified attributes.
     *
     * @param problemNo   The unique problem number.
     * @param luck        The luck associated with the problem.
     * @param difficulty  The difficulty level of the problem.
     * @param verifierNos The list of verifier numbers associated with the problem.
     * @param secretCode  The secret code associated with the problem.
     */
    public Problem(String problemNo, String luck, String difficulty, List<String> verifierNos, String secretCode) {
        this.problemNo = problemNo;
        this.luck = luck;
        this.difficulty = difficulty;
        this.verifierNos = verifierNos;
        this.secretCode = secretCode;
    }

    /**
     * Copy constructor to create a new Problem instance with the same attributes as an existing problem.
     *
     * @param problem The existing problem to copy.
     */
    public Problem(Problem problem) {
        this(problem.problemNo, problem.luck, problem.difficulty, new ArrayList<>(problem.verifierNos), problem.secretCode);
    }

    /**
     * Gets the unique problem number.
     *
     * @return The problem number.
     */
    public String getProblemNo() {
        return problemNo;
    }

    /**
     * Gets the luck associated with the problem.
     *
     * @return The luck value.
     */
    public String getLuck() {
        return luck;
    }

    /**
     * Gets the difficulty level of the problem.
     *
     * @return The difficulty level.
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Gets an unmodifiable list of verifier numbers associated with the problem.
     *
     * @return The list of verifier numbers.
     */
    public List<String> getVerifierNos() {
        return Collections.unmodifiableList(verifierNos);
    }

    /**
     * Gets the secret code associated with the problem.
     * Package-private method to restrict access outside of the class.
     *
     * @return The secret code.
     */
    String getSecretCode() {
        return secretCode;
    }

    /**
     * Checks if the provided code is equal to the secret code of the problem.
     *
     * @param code The code to compare with the secret code.
     * @return True if the codes are equal, otherwise false.
     */
    public boolean isEqualToSecretCode(String code) {
        return Objects.equals(code, secretCode);
    }

    @Override
    public String toString() {
        return "Problem n°" + problemNo + " (Difficulty: " + difficulty + ", " + "Luck: " + luck + ")";
    }
}
