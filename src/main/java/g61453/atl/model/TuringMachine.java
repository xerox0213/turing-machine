package g61453.atl.model;

import g61453.atl.command.Command;
import g61453.atl.model.commands.EnterCodeCommand;
import g61453.atl.model.commands.NextRoundCommand;
import g61453.atl.model.commands.SelectVerifierCommand;

/**
 * The TuringMachine class acts as a simplified facade for the Turing Machine game model, providing a convenient
 * interface for initiating and controlling the game. It delegates the core logic and functionality to the
 * underlying Game class, allowing for a separation of concerns and encapsulation of the game's internal workings.
 * The TuringMachine class includes methods for initializing the game, progressing through rounds, interacting
 * with verifiers, entering codes, and guessing the secret code. Additionally, it supports undo and redo operations
 * through the CommandsManager, providing a way to revert and reapply player actions.
 *
 * @see Game
 */
public class TuringMachine {

    /**
     * The underlying Game instance responsible for managing the game state and logic.
     */
    private final Game game;

    /**
     * The manager for handling undo and redo commands.
     */
    private final CommandsManager commandsManager;

    /**
     * Constructs a TuringMachine with the specified Game instance.
     *
     * @param game The Game instance representing the core logic of the Turing Machine game.
     */
    public TuringMachine(Game game) {
        this.game = game;
        this.commandsManager = new CommandsManager();
    }

    /**
     * Initializes the game with a specific problem identified by its number.
     *
     * @param problemNo The number identifying the specific problem for the game.
     */
    public void initGame(String problemNo) {
        game.initGame(problemNo);
    }

    /**
     * Initializes the game with a random problem.
     */
    public void initGame() {
        game.initGame();
    }

    /**
     * Moves on to the next round of the game.
     */
    public void nextRound() {
        Command nextRoundCommand = new NextRoundCommand(game);
        commandsManager.executeCommand(nextRoundCommand);
    }

    /**
     * Selects a verifier during the game and updates the player's score accordingly.
     *
     * @param verifierNo The number identifying the verifier to be selected.
     */
    public void selectVerifier(String verifierNo) {
        Command selectVerifierCommand = new SelectVerifierCommand(game, verifierNo);
        commandsManager.executeCommand(selectVerifierCommand);
    }

    /**
     * Enters a code during a round of the game.
     *
     * @param code The code entered by the player for verification.
     */
    public void enterCode(String code) {
        Command command = new EnterCodeCommand(game, code);
        commandsManager.executeCommand(command);
    }

    /**
     * Undoes the last executed command, reverting the game state.
     */
    public void undo() {
        commandsManager.undo();
    }

    /**
     * Redoes the last undone command, reapplying the recorded action.
     */
    public void redo() {
        commandsManager.redo();
    }

    /**
     * Makes a guess for the secret code and notifies observers of the result.
     *
     * @param code The player's guess for the secret code.
     */
    public void guessSecretCode(String code) {
        game.guessSecretCode(code);
    }
}
