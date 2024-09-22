package g61453.atl.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player();

    @Test
    @DisplayName("incScore() should increment the player score of 1")
    void incScoreShouldIncrementPlayerScore() {
        player.incScore();
        int score = player.getScore();
        assertEquals(1, score);
    }

    @Test
    @DisplayName("decScore() should decrement the player score of 1")
    void decScore() {
        player.incScore();
        player.decScore();
        int score = player.getScore();
        assertEquals(0, score);
    }
}