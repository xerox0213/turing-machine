package g61453.atl.model;

import g61453.atl.oo.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuringMachineTest {

    ProblemsManager problemsManager = new ProblemsManager();
    Game game = new Game(problemsManager);
    TuringMachine turingMachine = new TuringMachine(game);
    FakeObserver fakeObserver = new FakeObserver();

    class FakeObserver implements Observer {
        GameStatus gameStatus;
        GameData gameData;
        VerifierResult verifierResult;
        boolean isEqualToSecretCode;

        @Override
        public void update(String event, Object state) {
            switch (event) {
                case "game-init":
                    gameData = (GameData) state;
                    break;
                case "verifier-result":
                    verifierResult = (VerifierResult) state;
                    break;
                case "game-status":
                    gameStatus = (GameStatus) state;
                    break;
                case "guess-secret-code":
                    isEqualToSecretCode = (boolean) state;
                    break;
            }
        }
    }

    @BeforeEach
    void setUp() {
        problemsManager.readProblems();
        turingMachine.initGame("1");
        game.addObserver(fakeObserver);
    }

    @Test
    @DisplayName("initGame() should initialize the game with the given problem n°")
    void initGameShouldInitializeGameWithGivenProblemNo() {
        String problemNo = "1";
        turingMachine.initGame(problemNo);
        String problemNoRes = fakeObserver.gameData.problem().getProblemNo();
        assertEquals(problemNo, problemNoRes);
    }

    @Test
    @DisplayName("initGame() should initialize the game with a random problem")
    void testInitGame() {
        turingMachine.initGame();
        String problemNoRes = fakeObserver.gameData.problem().getProblemNo();
        assertNotNull(problemNoRes);
    }

    @Test
    @DisplayName("selectVerifier() should select the given verifier with the given n°")
    void selectVerifierShouldSelectGivenSelectVerifier() {
        String code = "123";
        String givenVerifierNo = "4";
        turingMachine.enterCode(code);
        turingMachine.selectVerifier(givenVerifierNo);
        String verifierNoRes = fakeObserver.verifierResult.verifierNo();
        assertEquals(givenVerifierNo, verifierNoRes);
    }

    @Test
    @DisplayName("nextRound() should move to the next round")
    void nextRoundShouldMoveToNextRound() {
        turingMachine.nextRound();
        int roundNo = fakeObserver.gameStatus.roundNo();
        assertEquals(2, roundNo);
    }

    @Test
    @DisplayName("enterCode() should set the given code in the current round")
    void enterCodeShouldSetGivenCode() {
        String givenCode = "424";
        turingMachine.enterCode(givenCode);
        String codeRes = fakeObserver.gameStatus.playerCode();
        assertEquals(codeRes, givenCode);
    }

    @Test
    @DisplayName("guessSecretCode() should return true if the given code is the same that secret code")
    void guessSecretCodeShouldReturnTrue() {
        String givenCode = "241";
        turingMachine.guessSecretCode(givenCode);
        boolean res = fakeObserver.isEqualToSecretCode;
        assertTrue(res);
    }

    @Test
    @DisplayName("undo() should undo the last turing game command")
    void undoShouldUndoTheLastTuringGameCommand() {
        String givenCode1 = "451";
        turingMachine.enterCode(givenCode1);
        String playerCode1 = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode1, playerCode1);
        String givenCode2 = "242";
        turingMachine.enterCode(givenCode2);
        String playerCode2 = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode2, playerCode2);
        turingMachine.undo();
        String undoPlayerCode = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode1, undoPlayerCode);
    }

    @Test
    @DisplayName("undo() should redo the last undid command")
    void redoShouldRedoTheLastUndidCommand() {
        String givenCode1 = "451";
        turingMachine.enterCode(givenCode1);
        String playerCode1 = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode1, playerCode1);
        String givenCode2 = "242";
        turingMachine.enterCode(givenCode2);
        String playerCode2 = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode2, playerCode2);
        turingMachine.undo();
        String undoPlayerCode = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode1, undoPlayerCode);
        turingMachine.redo();
        String redoPlayerCode = fakeObserver.gameStatus.playerCode();
        assertEquals(givenCode2, redoPlayerCode);
    }
}