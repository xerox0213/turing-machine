package g61453.atl.model.commands;

import g61453.atl.command.Command;
import g61453.atl.model.Game;
import g61453.atl.model.ProblemsManager;
import g61453.atl.model.TuringMachineException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextRoundCommandTest {

    String code = "123";
    ProblemsManager problemsManager = new ProblemsManager();
    Game game = new Game(problemsManager);
    Command nextRoundCommand = new NextRoundCommand(game);


    @BeforeEach
    void setUp() {
        problemsManager.readProblems();
        game.initGame("1");
    }

    @Test
    @DisplayName("execute() should move to the next round")
    void executeShouldMoveToNextRound() {
        game.enterCode(code);
        game.selectVerifier("4");
        assertThrows(TuringMachineException.class, () -> game.enterCode(code));
        nextRoundCommand.execute();
        assertNull(game.getPlayerCode());
        assertDoesNotThrow(() -> game.enterCode(code));
    }

    @Test
    @DisplayName("execute() should move to the previous round")
    void cancelShouldMoveToPreviousRound() {
        game.enterCode(code);
        game.selectVerifier("4");
        nextRoundCommand.execute();
        nextRoundCommand.cancel();
        assertThrows(TuringMachineException.class, () -> game.enterCode(code));
        assertEquals(code, game.getPlayerCode());
    }
}