package g61453.atl.model;

import g61453.atl.command.Command;

import java.util.Stack;

/**
 * Manages the execution, undo, and redo of commands in the Turing Machine game.
 * Keeps track of the command history for undo and redo operations.
 */
public class CommandsManager {

    /**
     * Stack to store executed commands for undo operations.
     */
    private final Stack<Command> undoHistory;

    /**
     * Stack to store undone commands for redo operations.
     */
    private final Stack<Command> redoHistory;

    /**
     * Constructs a CommandsManager with empty undo and redo histories.
     */
    public CommandsManager() {
        this.undoHistory = new Stack<>();
        this.redoHistory = new Stack<>();
    }

    /**
     * Executes the given command and adds it to the undo history.
     *
     * @param command The command to execute.
     */
    public void executeCommand(Command command) {
        command.execute();
        undoHistory.add(command);
        redoHistory.clear();
    }

    /**
     * Undoes the last executed command and moves it to the redo history.
     *
     * @throws TuringMachineException if there is no command to undo.
     */
    public void undo() {
        Command command;
        try {
            command = undoHistory.pop();
        } catch (Exception e) {
            throw new TuringMachineException("There is no command to undo");
        }
        redoHistory.push(command);
        command.cancel();
    }

    /**
     * Redoes the last undone command and moves it back to the undo history.
     *
     * @throws TuringMachineException if there is no command to redo.
     */
    public void redo() {
        Command command;
        try {
            command = redoHistory.pop();
        } catch (Exception e) {
            throw new TuringMachineException("There is no command to redo");
        }

        undoHistory.push(command);
        command.execute();
    }

}
