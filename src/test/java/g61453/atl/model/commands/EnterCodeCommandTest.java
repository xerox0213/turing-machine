package g61453.atl.model.commands;

import g61453.atl.model.Game;
import g61453.atl.model.Problem;
import g61453.atl.model.ProblemsManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnterCodeCommandTest {

    String code = "123";
    Game game = new Game(new ProblemsManager());
    EnterCodeCommand enterCodeCommand;

    @BeforeEach
    void setUp() {
        game.nextRound();
        enterCodeCommand = new EnterCodeCommand(game, code);
    }

    @Test
    @DisplayName("execute() should set the given code in the current round")
    void executeShouldSetGivenCode() {
        enterCodeCommand.execute();
        assertEquals(code, game.getPlayerCode());
    }

    @Test
    @DisplayName("cancel() should restore the old code in the current round")
    void cancelShouldRestoreOldCode() {
        enterCodeCommand.execute();
        enterCodeCommand.cancel();
        assertNull(game.getPlayerCode());
    }
}