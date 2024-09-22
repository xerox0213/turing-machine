package g61453.atl.view.javafx.layouts;

import g61453.atl.controller.JavaFxController;
import g61453.atl.model.*;
import g61453.atl.view.javafx.TMColors;
import g61453.atl.view.javafx.components.CommandButton;
import g61453.atl.view.javafx.components.GameStatusText;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.InputStream;
import java.util.List;

public class GameLayout extends VBox {
    private final JavaFxController javaFxController;
    private final HBox gameStatusContainer = new HBox();
    private final HBox gameVerifierContainer = new HBox();
    private final HBox gameCommandContainer = new HBox();
    private final Text playerScoreText = new GameStatusText();
    private final Text selectedProblemText = new GameStatusText();
    private final Text roundNoText = new GameStatusText();
    private final Text verifierNumberText = new GameStatusText();
    private final Text playerCodeText = new GameStatusText();
    private final TextField playerCodeTextField = new TextField();
    private final ScrollPane scrollPane = new ScrollPane();
    private final CommandButton enterCodeBtn = new CommandButton("Enter code", TMColors.GREEN);
    private final CommandButton nextRoundBtn = new CommandButton("Next round", TMColors.PINK);
    private final CommandButton guessSecretCodeBtn = new CommandButton("Guess secret code", TMColors.DARKBLUE);
    private final CommandButton undoBtn = new CommandButton("Undo", TMColors.YELLOW);
    private final CommandButton redoBtn = new CommandButton("Redo", TMColors.SKYBLUE);
    private final CommandButton giveUpBtn = new CommandButton("Give up", TMColors.RED);
    private final CommandButton quitBtn = new CommandButton("Quit game", TMColors.RED);

    public GameLayout(JavaFxController javaFxController, GameData gameData) {
        this.javaFxController = javaFxController;

        // === HANDLE GAME STATUS SECTION DISPLAY ===
        handleInitGameStatusDisplay(gameData.gameStatus(), gameData.problem());

        // === HANDLE GAME VERIFIER DISPLAY ===
        handleVerifierSectionDisplay(gameData.problem());

        // === HANDLE GAME BUTTON SECTION DISPLAY ===
        handleInitGameBtnSectionDisplay();

        // === HANDLE EVENT HANDLER CONNECTION ===
        connectEventHandler();

        // === HIMSELF ===
        this.setPadding(new Insets(30, 20, 30, 20));
        this.setSpacing(20);
    }

    public void updateGameStatus(GameStatus gameStatus) {
        playerScoreText.setText("Your score: " + gameStatus.score());
        roundNoText.setText("Rounds: " + gameStatus.roundNo());
        verifierNumberText.setText("Verifiers: " + gameStatus.verifiedValidators());
        playerCodeText.setText("Your code: " + (gameStatus.playerCode() == null ? "?" : gameStatus.playerCode()));
    }

    private void handleInitGameBtnSectionDisplay() {
        playerCodeTextField.setPromptText("Type your code here");
        playerCodeTextField.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            char character = e.getCharacter().charAt(0);
            if (character < 49 || character > 53) e.consume();
            if (playerCodeTextField.getText().length() == 3) e.consume();
        });
        playerCodeTextField.setMaxHeight(Double.MAX_VALUE);
        gameCommandContainer.getChildren().addAll(playerCodeTextField, enterCodeBtn, guessSecretCodeBtn, nextRoundBtn, undoBtn, redoBtn, giveUpBtn);
        HBox.setHgrow(playerCodeTextField, Priority.ALWAYS);
        gameCommandContainer.setAlignment(Pos.CENTER);
        gameCommandContainer.setSpacing(20);
        gameCommandContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
        gameCommandContainer.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        gameCommandContainer.setPadding(new Insets(10));
        this.getChildren().add(gameCommandContainer);
    }

    private void handleInitGameStatusDisplay(GameStatus gameStatus, Problem problem) {
        selectedProblemText.setText("Problem n°" + problem.getProblemNo());
        updateGameStatus(gameStatus);
        gameStatusContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
        gameStatusContainer.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
        gameStatusContainer.setPadding(new Insets(15));
        gameStatusContainer.getChildren().addAll(selectedProblemText, playerScoreText, roundNoText, verifierNumberText, playerCodeText);
        gameStatusContainer.setAlignment(Pos.CENTER);
        gameStatusContainer.setSpacing(20);
        this.getChildren().add(gameStatusContainer);
    }

    public void displayVerifierResult(VerifierResult verifierResult) {
        Alert.AlertType alertType = Alert.AlertType.INFORMATION;
        Alert alert = new Alert(alertType);
        alert.setHeaderText("Verifier n°" + verifierResult.verifierNo() + " result: " + verifierResult.result());
        alert.showAndWait();
    }

    private void handleVerifierSectionDisplay(Problem problem) {
        String[] robotLetters = {"A", "B", "C", "D", "E", "F"};
        List<String> verifierNos = problem.getVerifierNos();
        for (int i = 0; i < verifierNos.size(); i++) {
            VBox verifierContainer = new VBox();
            CommandButton selectVerifierBtn = getSelectVerifierBtn(verifierNos.get(i));
            String verifierImgPath = "images/card" + verifierNos.get(i) + ".png";
            String robotImgPath = "images/robot" + robotLetters[i] + ".png";
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream verifierImgIs = classLoader.getResourceAsStream(verifierImgPath);
            InputStream robotImgIs = classLoader.getResourceAsStream(robotImgPath);
            if (verifierImgIs != null && robotImgIs != null) {
                Image verifierImage = new Image(verifierImgIs);
                ImageView verifierImageView = new ImageView(verifierImage);
                verifierImageView.setPreserveRatio(true);
                verifierImageView.setFitWidth(260);
                Image robotImage = new Image(robotImgIs);
                ImageView robotImageView = new ImageView(robotImage);
                robotImageView.setPreserveRatio(true);
                robotImageView.setFitWidth(100);
                verifierContainer.getChildren().addAll(robotImageView, verifierImageView, selectVerifierBtn);
                verifierContainer.setAlignment(Pos.CENTER);
                verifierContainer.setSpacing(10);
                verifierContainer.setPadding(new Insets(10));
                verifierContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
                verifierContainer.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));
                gameVerifierContainer.getChildren().add(verifierContainer);
            }
        }
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
        scrollPane.setContent(gameVerifierContainer);
        scrollPane.setFitToHeight(true);
        scrollPane.setPadding(new Insets(0, 0, 10, 0));
        gameVerifierContainer.setAlignment(Pos.CENTER);
        gameVerifierContainer.setSpacing(30);
        this.getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    private CommandButton getSelectVerifierBtn(String verifierNo) {
        CommandButton selectVerifierBtn = new CommandButton("Select verifier", TMColors.ORANGE);
        selectVerifierBtn.setMaxWidth(Double.MAX_VALUE);
        selectVerifierBtn.setUserData(verifierNo);
        EventHandler<MouseEvent> selectVerifier = e -> {
            JavaFxController.Event event = JavaFxController.Event.SELECT_VERIFIER;
            javaFxController.sendUserEvent(event, verifierNo);
        };
        selectVerifierBtn.setOnMouseClicked(selectVerifier);
        return selectVerifierBtn;
    }

    private void connectEventHandler() {
        EventHandler<MouseEvent> moveNextRound = e -> {
            JavaFxController.Event event = JavaFxController.Event.NEXT_ROUND;
            javaFxController.sendUserEvent(event, null);
        };

        EventHandler<MouseEvent> giveUp = e -> {
            JavaFxController.Event event = JavaFxController.Event.GIVE_UP;
            javaFxController.sendUserEvent(event, null);
        };

        EventHandler<MouseEvent> enterCode = e -> {
            JavaFxController.Event event = JavaFxController.Event.ENTER_CODE;
            if (playerCodeTextField.getText().length() < 3) {
                displayErrorCode();
                return;
            }
            javaFxController.sendUserEvent(event, playerCodeTextField.getText());
            playerCodeTextField.setText("");
        };

        EventHandler<MouseEvent> guessCode = e -> {
            if (playerCodeTextField.getText().length() < 3) {
                displayErrorCode();
                return;
            }
            JavaFxController.Event event = JavaFxController.Event.GUESS_SECRET_CODE;
            javaFxController.sendUserEvent(event, playerCodeTextField.getText());
        };

        EventHandler<MouseEvent> undo = e -> {
            JavaFxController.Event event = JavaFxController.Event.UNDO;
            javaFxController.sendUserEvent(event, null);
        };

        EventHandler<MouseEvent> redo = e -> {
            JavaFxController.Event event = JavaFxController.Event.REDO;
            javaFxController.sendUserEvent(event, null);
        };

        giveUpBtn.setOnMouseClicked(giveUp);
        nextRoundBtn.setOnMouseClicked(moveNextRound);
        enterCodeBtn.setOnMouseClicked(enterCode);
        guessSecretCodeBtn.setOnMouseClicked(guessCode);
        undoBtn.setOnMouseClicked(undo);
        redoBtn.setOnMouseClicked(redo);
        quitBtn.setOnMouseClicked(giveUp);
    }

    private void displayErrorCode() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid code");
        alert.setHeaderText("You must first type a 3-digit code in the text field!");
        alert.showAndWait();
    }
}
