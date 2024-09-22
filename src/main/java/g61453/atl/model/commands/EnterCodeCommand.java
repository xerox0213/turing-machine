package g61453.atl.model.commands;

import g61453.atl.command.Command;
import g61453.atl.model.Game;

/**
 * The EnterCodeCommand class represents a command to enter a code in the context of the Turing Machine game.
 * It encapsulates the logic to execute and cancel the operation, allowing for reversible actions.
 * This command is associated with a specific Game instance as the receiver, capturing both the new code to be
 * executed and the previous code for cancellation.
 */
public class EnterCodeCommand implements Command {

    /**
     * The Game instance associated with this command, serving as the receiver of the enter code operation.
     */
    private final Game receiver;

    /**
     * The new code to be executed by the command.
     */
    private final String newCode;

    /**
     * The old code, representing the state before the execution of the command.
     */
    private final String oldCode;

    /**
     * Constructs an EnterCodeCommand with the specified Game instance and new code.
     *
     * @param game    The Game instance associated with this command.
     * @param newCode The new code to be executed.
     */
    public EnterCodeCommand(Game game, String newCode) {
        this.receiver = game;
        this.oldCode = game.getPlayerCode();
        this.newCode = newCode;
    }

    /**
     * Executes the command by entering the new code in the associated Game instance.
     */
    @Override
    public void execute() {
        receiver.enterCode(newCode);
    }

    /**
     * Cancels the executed command by reverting the associated Game instance to its previous state.
     */
    @Override
    public void cancel() {
        receiver.enterCode(oldCode);
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
