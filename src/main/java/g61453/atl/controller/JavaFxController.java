package g61453.atl.controller;

import g61453.atl.model.Game;
import g61453.atl.model.ProblemsManager;
import g61453.atl.model.TuringMachine;
import g61453.atl.model.TuringMachineException;
import g61453.atl.view.javafx.JavaFxView;
import g61453.atl.view.javafx.layouts.GameLayout;
import g61453.atl.view.javafx.layouts.ProblemLayout;
import javafx.stage.Stage;

public class JavaFxController {
    public enum Event {
        NEXT_ROUND, SELECT_PROBLEM, SELECT_RANDOM_PROBLEM, ENTER_CODE, SELECT_VERIFIER, GIVE_UP, GUESS_SECRET_CODE, UNDO, REDO
    }

    TuringMachine turingMachine;
    JavaFxView javaFxView;

    public JavaFxController(Stage stage) {
        ProblemsManager problemsManager = new ProblemsManager();
        Game game = new Game(problemsManager);
        turingMachine = new TuringMachine(game);
        javaFxView = new JavaFxView(this, stage);
        game.addObserver(javaFxView);
        problemsManager.addObserver(javaFxView);
        problemsManager.readProblems();
    }

    public void sendUserEvent(Event event, Object state) {
        switch (event) {
            case SELECT_PROBLEM:
                selectProblem((String) state);
                break;
            case ENTER_CODE:
                enterCode((String) state);
                break;
            case SELECT_RANDOM_PROBLEM:
                selectRandomProblem();
                break;
            case SELECT_VERIFIER:
                selectVerifier((String) state);
                break;
            case NEXT_ROUND:
                nextRound();
                break;
            case GIVE_UP:
                giveUp();
                break;
            case GUESS_SECRET_CODE:
                guessSecretCode((String) state);
                break;
            case UNDO:
                undo();
                break;
            case REDO:
                redo();
                break;
        }
    }

    private void selectProblem(String verifierNo) {
        try {
            turingMachine.initGame(verifierNo);
        } catch (TuringMachineException tme) {
            javaFxView.displayError(tme.getMessage());
        }
    }

    private void selectRandomProblem() {
        try {
            turingMachine.initGame();
        } catch (TuringMachineException tme) {
            javaFxView.displayError(tme.getMessage());
        }
    }

    private void enterCode(String code) {
        try {
            turingMachine.enterCode(code);
        } catch (TuringMachineException tme) {
            javaFxView.displayError(tme.getMessage());
        }
    }

    private void nextRound() {
        turingMachine.nextRound();
    }

    private void giveUp() {
        javaFxView.closeStage();
        System.exit(0);
    }

    private void selectVerifier(String verifierNo) {
        try {
            turingMachine.selectVerifier(verifierNo);
        } catch (TuringMachineException tme) {
            javaFxView.displayError(tme.getMessage());
        }
    }

    private void undo() {
        try {
            turingMachine.undo();
        } catch (TuringMachineException tme) {
            javaFxView.displayError(tme.getMessage());
        }
    }

    private void redo() {
        try {
            turingMachine.redo();
        } catch (TuringMachineException tme) {
            javaFxView.displayError(tme.getMessage());
        }
    }

    private void guessSecretCode(String code) {
        turingMachine.guessSecretCode(code);
    }
}
