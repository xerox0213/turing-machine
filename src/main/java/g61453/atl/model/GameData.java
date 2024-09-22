package g61453.atl.model;

/**
 * Represents the data associated with the current state of the Turing Machine game.
 * It includes information about the current problem being played and the game status.
 *
 * @param problem    The current problem being played in the game.
 * @param gameStatus The status of the game, including round information, player score, verified validators,
 *                   and the player's entered code.
 */
public record GameData(Problem problem, GameStatus gameStatus) {
}
