package g61453.atl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single round in the Turing Machine game, during which a player provides a code
 * and selects verifiers to check against the secret code.
 */
public class Round {

    /**
     * The player's provided code for this round.
     */
    private String playerCode;

    /**
     * The list of verifier numbers selected by the player for checking against the secret code.
     */
    private final List<String> selectedVerifierNos;

    /**
     * Constructs a Round object with an empty list of selected verifiers.
     */
    public Round() {
        selectedVerifierNos = new ArrayList<>(3);
    }

    /**
     * Gets the player's provided code for this round.
     *
     * @return The player's provided code.
     */
    public String getPlayerCode() {
        return playerCode;
    }

    /**
     * Sets the player's provided code for this round.
     *
     * @param playerCode The player's provided code.
     * @throws TuringMachineException if the player has already selected verifiers for this round.
     */
    public void setPlayerCode(String playerCode) {
        if (!selectedVerifierNos.isEmpty()) {
            throw new TuringMachineException("To be able to enter a new code, you must move on to the next round or have not yet selected a checker");
        }
        this.playerCode = playerCode;
    }

    /**
     * Adds a verifier number to the list of selected verifiers for this round.
     *
     * @param verifierNo The verifier number to add.
     * @throws TuringMachineException if the player has already selected the maximum number of verifiers (3) for this round.
     */
    public void addVerifierNo(String verifierNo) {
        if (selectedVerifierNos.size() == 3) {
            throw new TuringMachineException("You can select up to 3 verifiers per round");
        }
        selectedVerifierNos.add(verifierNo);
    }

    /**
     * Checks if a verifier number has already been selected for this round.
     *
     * @param verifierNo The verifier number to check.
     * @return True if the verifier number is already selected, otherwise false.
     */
    public boolean isVerifierAlreadyUsed(String verifierNo) {
        for (String selectedVerifierNo : selectedVerifierNos) {
            if (selectedVerifierNo.equals(verifierNo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Unselects a verifier by removing its number from the list of selected verifiers for this round.
     *
     * @param verifierNo The verifier number to unselect.
     */
    public boolean unselectVerifier(String verifierNo) {
        return selectedVerifierNos.remove(verifierNo);
    }

    /**
     * Gets the number of verifiers that have been selected by the player for this round.
     *
     * @return The number of selected verifiers for this round.
     */
    public int getNumberSelectedVerifier() {
        return selectedVerifierNos.size();
    }
}
