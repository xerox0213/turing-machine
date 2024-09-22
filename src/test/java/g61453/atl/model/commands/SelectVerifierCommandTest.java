package g61453.atl.model.commands;

import g61453.atl.command.Command;
import g61453.atl.model.*;
import g61453.atl.oo.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectVerifierCommandTest {

    String code = "123";
    ProblemsManager problemsManager = new ProblemsManager();
    Game game = new Game(problemsManager);
    String verifierNo = "4";
    Command selectVerifierCommand = new SelectVerifierCommand(game, verifierNo);

    static class FakeObserver implements Observer {
        public VerifierResult verifierResult;
        public GameStatus gameStatus;

        @Override
        public void update(String event, Object state) {
            if (event.equals("verifier-result")) {
                verifierResult = (VerifierResult) state;
            } else if (event.equals("game-status")) {
                gameStatus = (GameStatus) state;
            }
        }
    }

    @BeforeEach
    void setUp() {
        problemsManager.readProblems();
        game.initGame("1");
    }

    @Test
    @DisplayName("execute() should select the given verifier")
    void executeShouldSelectTheGivenVerifier() {
        FakeObserver fakeObserver = new FakeObserver();
        game.addObserver(fakeObserver);
        game.enterCode(code);
        selectVerifierCommand.execute();
        String verifierNo = fakeObserver.verifierResult.verifierNo();
        assertEquals("4", verifierNo);
    }

    @Test
    @DisplayName("execute() should unselect the previous given verifier")
    void cancelShouldUnselectTheGivenVerifier() {
        FakeObserver fakeObserver = new FakeObserver();
        game.addObserver(fakeObserver);
        game.enterCode(code);
        selectVerifierCommand.cancel();
        int playerScore = fakeObserver.gameStatus.score();
        int verifierValidators = fakeObserver.gameStatus.verifiedValidators();

        assertEquals(0, playerScore);
        assertEquals(0, verifierValidators);
    }
}