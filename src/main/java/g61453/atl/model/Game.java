package g61453.atl.model;

import g61453.atl.model.verifiers.Verifier;
import g61453.atl.oo.Observable;
import g61453.atl.oo.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * The Game class encapsulates the core logic and state of the Turing Machine game.
 * It manages the currently selected problem, available verifiers, rounds played, player participation,
 * and communication with observers to notify them of relevant game events.
 * The Game class is designed to be used as part of the overall Turing Machine game model,
 * with its functionality being accessed and orchestrated through a higher-level facade,
 * typically represented by the TuringMachine class.
 */
public class Game implements Observable {

    /**
     * The currently selected problem for the game.
     */
    private Problem problem;

    /**
     * The list of available verifiers for the game.
     */
    private final List<Verifier> verifiers;

    /**
     * The list of rounds played in the game.
     */
    private final List<Round> rounds;

    /**
     * The player participating in the game.
     */
    private final Player player;

    /**
     * The manager responsible for handling game problems.
     */
    private final ProblemsManager problemsManager;

    /**
     * The factory for creating verifiers based on the secret code.
     */
    private Factory verifierFactory;

    /**
     * The list of observers interested in game events.
     */
    private final List<Observer> observers;

    /**
     * Constructs a TuringMachine with the specified ProblemsManager.
     *
     * @param problemsManager The ProblemsManager responsible for managing game problems.
     */
    public Game(ProblemsManager problemsManager) {
        this.problemsManager = problemsManager;
        observers = new ArrayList<>();
        rounds = new ArrayList<>();
        verifiers = new ArrayList<>();
        player = new Player();
    }

    /**
     * Initializes the game with a specific problem identified by its number.
     *
     * @param problemNo The number identifying the specific problem for the game.
     */
    public void initGame(String problemNo) {
        problem = problemsManager.getSpecificProblem(problemNo);
        setUp();
    }

    /**
     * Initializes the game with a random problem.
     */
    public void initGame() {
        problem = problemsManager.getRandomProblem();
        setUp();
    }

    /**
     * Selects a verifier during the game and updates the player's score accordingly.
     *
     * @param verifierNo The number identifying the verifier to be selected.
     */
    public void selectVerifier(String verifierNo) {
        Round currRound = getCurrRound();
        String playerCodeValue = currRound.getPlayerCode();
        if (playerCodeValue == null) {
            throw new TuringMachineException("You have to enter a code to select a verifier.");
        }

        Verifier selectedVerifier = getVerifier(verifierNo);
        if (selectedVerifier == null) {
            throw new TuringMachineException("The selected verifier do not exist");
        }

        if (!currRound.isVerifierAlreadyUsed(verifierNo)) {
            currRound.addVerifierNo(verifierNo);
            player.incScore();
            notifyObserver("game-status", createGameStatus());
        }

        Code playerCode = new Code(playerCodeValue);
        boolean result = selectedVerifier.check(playerCode);
        notifyObserver("verifier-result", new VerifierResult(verifierNo, result));
    }

    /**
     * Unselects a verifier by removing its number from the list of selected verifiers for the current round.
     * Decreases the player's score and notifies observers of the updated game status.
     *
     * @param verifierNo The verifier number to unselect.
     */
    public void unselectVerifier(String verifierNo) {
        if (getCurrRound().unselectVerifier(verifierNo)) {
            player.decScore();
            notifyObserver("game-status", createGameStatus());
        }
    }

    /**
     * Makes a guess for the secret code and notifies observers of the result.
     *
     * @param code The player's guess for the secret code.
     */
    public void guessSecretCode(String code) {
        boolean isEqualToSecretCode = problem.isEqualToSecretCode(code);
        notifyObserver("guess-secret-code", isEqualToSecretCode);
    }

    /**
     * Enters a code during a round of the game.
     *
     * @param code The code entered by the player for verification.
     */
    public void enterCode(String code) {
        getCurrRound().setPlayerCode(code);
        notifyObserver("game-status", createGameStatus());
    }

    /**
     * Moves on to the next round of the game.
     */
    public void nextRound() {
        rounds.add(new Round());
        notifyObserver("game-status", createGameStatus());
    }

    /**
     * Moves the game to the previous round, undoing the last round's state changes.
     * This method removes the last Round instance from the list of played rounds,
     * effectively reverting the game to the state it was in before the last round.
     * Observers are notified of the updated game status after moving to the previous round.
     */
    public void previousRound() {
        rounds.removeLast();
        notifyObserver("game-status", createGameStatus());
    }

    public String getPlayerCode() {
        return getCurrRound().getPlayerCode();
    }

    @Override
    public void notifyObserver(String event, Object object) {
        observers.forEach(observer -> observer.update(event, object));
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void setUp() {
        if (problem == null) {
            throw new TuringMachineException("The selected problem does not exist");
        }
        List<String> verifierNos = problem.getVerifierNos();
        String secretCodeValue = problem.getSecretCode();
        verifierFactory = new VerifierFactory(secretCodeValue);
        verifierNos.forEach(verifierNo -> verifiers.add(createVerifier(verifierNo)));
        Round round = new Round();
        rounds.add(round);
        GameData gameData = new GameData(new Problem(problem), createGameStatus());
        notifyObserver("game-init", gameData);
    }

    private GameStatus createGameStatus() {
        Round round = getCurrRound();
        String playerCode = round.getPlayerCode();
        int roundNo = rounds.size();
        int score = player.getScore();
        int numberSelectedVerifier = getCurrRound().getNumberSelectedVerifier();
        return new GameStatus(roundNo, score, numberSelectedVerifier, playerCode);
    }

    private Round getCurrRound() {
        return rounds.getLast();
    }

    private Verifier createVerifier(String verifierNo) {
        return verifierFactory.createVerifier(verifierNo);
    }

    private Verifier getVerifier(String verifierNo) {
        for (Verifier verifier : verifiers) {
            if (verifier.getVerifierNo().equals(verifierNo)) {
                return verifier;
            }
        }
        return null;
    }

}