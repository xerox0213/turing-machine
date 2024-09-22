package g61453.atl.model.commands;

import g61453.atl.command.Command;
import g61453.atl.model.Game;

/**
 * The SelectVerifierCommand class represents a command to select a verifier in the Turing Machine game.
 * It implements the Command interface, providing methods to execute the selection, cancel the selection,
 * and check whether the command is reversible.
 */
public class SelectVerifierCommand implements Command {

    /**
     * The Game instance responsible for managing the game state and logic.
     */
    private final Game receiver;

    /**
     * The identifier of the verifier to be selected or unselected.
     */
    private final String verifierNo;

    /**
     * Constructs a SelectVerifierCommand with the specified Game instance and verifier identifier.
     *
     * @param receiver   The Game instance representing the core logic of the Turing Machine game.
     * @param verifierNo The identifier of the verifier to be selected or unselected.
     */
    public SelectVerifierCommand(Game receiver, String verifierNo) {
        this.receiver = receiver;
        this.verifierNo = verifierNo;
    }

    /**
     * Executes the command by selecting the specified verifier in the game.
     */
    @Override
    public void execute() {
        receiver.selectVerifier(verifierNo);
    }

    /**
     * Cancels the previously executed command by unselecting the verifier.
     */
    @Override
    public void cancel() {
        receiver.unselectVerifier(verifierNo);
    }

    /**
     * Indicates whether the command is reversible, allowing for cancellation.
     *
     * @return True if the command is reversible, otherwise false.
     */
    @Override
    public boolean isReversible() {
        return true;
    }
}
