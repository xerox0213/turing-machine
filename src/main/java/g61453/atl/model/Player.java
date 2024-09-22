package g61453.atl.model;

/**
 * Represents a player in the Turing Machine game.
 * Each player has a score that can be incremented.
 */
public class Player {

    /**
     * The score of the player.
     */
    private int score = 0;

    /**
     * Increments the player's score by 1.
     */
    public void incScore() {
        score += 1;
    }

    /**
     * Decrements the player's score by 1.
     */
    public void decScore() {
        score -= 1;
    }

    /**
     * Gets the current score of the player.
     *
     * @return The player's current score.
     */
    public int getScore() {
        return score;
    }
}
