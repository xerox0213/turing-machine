package g61453.atl.model.commands;

import g61453.atl.command.Command;
import g61453.atl.model.Game;

/**
 * The NextRoundCommand class represents a command in the Turing Machine game model to advance the game to the next round.
 * It implements the Command interface and follows the command pattern, allowing execution and cancellation of game commands.
 * When executed, it triggers the nextRound method on the associated Game instance, progressing the game to the next round.
 * If the command needs to be canceled, it invokes the previousRound method on the Game instance to revert to the previous round.
 * The NextRoundCommand is not reversible, and attempts to cancel it will result in moving back to the previous round without preserving
 * the exact state of the current round.
 */
public class NextRoundCommand implements Command {
    /**
     * The Game instance that will be affected by the execution and cancellation of this command.
     */
    private final Game receiver;

    /**
     * Constructs a NextRoundCommand with the specified Game instance.
     *
     * @param receiver The Game instance associated with this command.
     */
    public NextRoundCommand(Game receiver) {
        this.receiver = receiver;
    }

    /**
     * Executes the command, advancing the game to the next round.
     */
    @Override
    public void execute() {
        receiver.nextRound();
    }

    /**
     * Cancels the command, moving the game back to the previous round.
     */
    @Override
    public void cancel() {
        receiver.previousRound();
    }

    /**
     * Indicates whether the command is reversible.
     * The NextRoundCommand is not reversible, and this method always returns false.
     *
     * @return False, as the NextRoundCommand cannot be reversed.
     */
    @Override
    public boolean isReversible() {
        return false;
    }
}
