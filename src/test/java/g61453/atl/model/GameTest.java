package g61453.atl.model;

import g61453.atl.oo.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {


    ProblemsManager problemsManager = new ProblemsManager();
    Game game = new Game(problemsManager);
    FakeObserver fakeObserver = new FakeObserver();

    class FakeObserver implements Observer {
        public GameData gameData;
        public VerifierResult verifierResult;
        public GameStatus gameStatus;
        public boolean isEqualToSecretCode;

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
        game.addObserver(fakeObserver);
        game.initGame("1");
    }

    @Test
    @DisplayName("initGame() should initialize the game with the given problem n°")
    void initGameShouldInitializeGameWithGivenProblemNo() {
        String problemNo = "1";
        game.initGame(problemNo);
        String problemNoRes = fakeObserver.gameData.problem().getProblemNo();
        assertEquals(problemNo, problemNoRes);
    }

    @Test
    @DisplayName("initGame() should initialize the game with a random problem")
    void testInitGame() {
        game.initGame();
        String problemNoRes = fakeObserver.gameData.problem().getProblemNo();
        assertNotNull(problemNoRes);
    }

    @Test
    @DisplayName("selectVerifier() should select the given verifier with the given n°")
    void selectVerifier() {
        String code = "123";
        String givenVerifierNo = "4";
        game.enterCode(code);
        game.selectVerifier(givenVerifierNo);
        String verifierNoRes = fakeObserver.verifierResult.verifierNo();
        assertEquals(givenVerifierNo, verifierNoRes);
    }

    @Test
    @DisplayName("unselectVerifier() should unselect the previous verifier")
    void unselectVerifier() {
        String code = "123";
        String givenVerifierNo = "4";
        game.enterCode(code);
        game.selectVerifier(givenVerifierNo);
        game.unselectVerifier(givenVerifierNo);
        int playerScore = fakeObserver.gameStatus.score();
        int verifiedValidators = fakeObserver.gameStatus.verifiedValidators();
        assertEquals(0, playerScore);
        assertEquals(0, verifiedValidators);
    }

    @Test
    @DisplayName("guessSecretCode() should return true if the given code is the same that secret code")
    void guessSecretCodeShouldReturnTrue() {
        String givenCode = "241";
        game.guessSecretCode(givenCode);
        boolean res = fakeObserver.isEqualToSecretCode;
        assertTrue(res);
    }

    @Test
    @DisplayName("guessSecretCode() should return false if the given code is not the same that secret code")
    void guessSecretCodeShouldReturnFalse() {
        String givenCode = "424";
        game.guessSecretCode(givenCode);
        boolean res = fakeObserver.isEqualToSecretCode;
        assertFalse(res);
    }

    @Test
    @DisplayName("enterCode() should set the given code in the current round")
    void enterCodeShouldSetGivenCode() {
        String givenCode = "424";
        game.enterCode(givenCode);
        String codeRes = fakeObserver.gameStatus.playerCode();
        assertEquals(codeRes, givenCode);
    }

    @Test
    @DisplayName("nextRound() should move to the next round")
    void nextRoundShouldMoveToNextRound() {
        game.nextRound();
        int roundNo = fakeObserver.gameStatus.roundNo();
        assertEquals(2, roundNo);
    }

    @Test
    @DisplayName("previousRound() should restore the previous round")
    void previousRoundShouldRestorePreviousRound() {
        game.nextRound();
        game.previousRound();
        int roundNo = fakeObserver.gameStatus.roundNo();
        assertEquals(1, roundNo);
    }
}