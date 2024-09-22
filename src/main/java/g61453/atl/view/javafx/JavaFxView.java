package g61453.atl.view.javafx;

import g61453.atl.controller.JavaFxController;
import g61453.atl.model.GameData;
import g61453.atl.model.GameStatus;
import g61453.atl.model.Problem;
import g61453.atl.model.VerifierResult;
import g61453.atl.oo.Observer;
import g61453.atl.view.javafx.layouts.GameLayout;
import g61453.atl.view.javafx.layouts.ProblemLayout;
import g61453.atl.view.javafx.layouts.WinLoseLayout;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.List;

public class JavaFxView implements Observer {
    private ProblemLayout problemLayout;
    private GameLayout gameLayout;
    private WinLoseLayout winLoseLayout;
    private final JavaFxController javaFxController;
    private Scene scene;
    private final Stage primaryStage;

    public JavaFxView(JavaFxController javaFxController, Stage primaryStage) {
        this.javaFxController = javaFxController;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Turing Machine Game");
        String imageName = "images/icon-program.png";
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(imageName);
        if (is != null) {
            Image image = new Image(is);
            primaryStage.getIcons().add(image);
        }
    }

    public void closeStage() {
        primaryStage.close();
    }

    public void displayError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Turing Machine error");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }

    public void displayGame(GameData gameData) {
        gameLayout = new GameLayout(javaFxController, gameData);
        scene.setRoot(gameLayout);
    }

    public void displayProblems(List<Problem> problems) {
        problemLayout = new ProblemLayout(javaFxController, problems);
        scene = new Scene(problemLayout, 1000, 630);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void displayVerifierResult(VerifierResult verifierResult) {
        gameLayout.displayVerifierResult(verifierResult);
    }

    public void updateGameStatus(GameStatus gameStatus) {
        gameLayout.updateGameStatus(gameStatus);
    }

    public void displayWinOrLose(boolean result) {
        winLoseLayout = new WinLoseLayout(javaFxController, result);
        scene.setRoot(winLoseLayout);
    }

    @Override
    public void update(String event, Object state) {
        switch (event) {
            case "problems-read":
                displayProblems((List<Problem>) state);
                break;
            case "game-init":
                displayGame((GameData) state);
                break;
            case "game-status":
                updateGameStatus((GameStatus) state);
                break;
            case "verifier-result":
                displayVerifierResult((VerifierResult) state);
                break;
            case "guess-secret-code":
                displayWinOrLose((boolean) state);
                break;
        }
    }
}