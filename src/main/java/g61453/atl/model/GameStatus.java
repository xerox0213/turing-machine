package g61453.atl.model;

/**
 * Represents the current status of the Turing Machine game.
 * Includes information such as the current round number, player score,
 * number of verified validators during the round, and the player's entered code.
 *
 * @param roundNo            The current round number in the game.
 * @param score              The player's current score in the game.
 * @param verifiedValidators The number of validators successfully verified by the player.
 * @param playerCode         The code entered by the player in the current round.
 */
public record GameStatus(int roundNo, int score, int verifiedValidators, String playerCode) {
}
