package g61453.atl.view.javafx.layouts;

import g61453.atl.controller.JavaFxController;
import g61453.atl.model.Problem;
import g61453.atl.view.javafx.TMColors;
import g61453.atl.view.javafx.components.CommandButton;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.InputStream;
import java.util.List;

public class ProblemLayout extends VBox {
    private final CommandButton validateBtn;
    private final CommandButton randomBtn;
    private final HBox btnContainer;
    private final ChoiceBox<Problem> problemChoiceBox;
    private final InputStream is;
    private final JavaFxController javaFxController;

    public ProblemLayout(JavaFxController javaFxController, List<Problem> problems) {
        this.javaFxController = javaFxController;
        btnContainer = new HBox();
        validateBtn = new CommandButton("Validate", TMColors.GREEN);
        randomBtn = new CommandButton("Random", TMColors.DARKBLUE);
        problemChoiceBox = new ChoiceBox<>();
        problemChoiceBox.setMaxHeight(Double.MAX_VALUE);
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.setSpacing(10);
        problemChoiceBox.getItems().addAll(problems);
        problemChoiceBox.setValue(problems.getFirst());
        btnContainer.getChildren().addAll(problemChoiceBox, validateBtn, randomBtn);
        String imageName = "images/turing_machine_logo.png";
        ClassLoader classLoader = this.getClass().getClassLoader();
        is = classLoader.getResourceAsStream(imageName);
        if (is != null) {
            Image image = new Image(is);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            this.getChildren().add(imageView);
        }
        this.setAlignment(Pos.BASELINE_CENTER);
        this.getChildren().addAll(btnContainer);
        EventHandler<MouseEvent> selectProblem = e -> {
            JavaFxController.Event event = JavaFxController.Event.SELECT_PROBLEM;
            Problem selectedProblem = problemChoiceBox.getValue();
            String selectedProblemNo = selectedProblem.getProblemNo();
            javaFxController.sendUserEvent(event, selectedProblemNo);
        };
        EventHandler<MouseEvent> randomProblem = e -> {
            JavaFxController.Event event = JavaFxController.Event.SELECT_RANDOM_PROBLEM;
            javaFxController.sendUserEvent(event, null);
        };
        validateBtn.setOnMouseClicked(selectProblem);
        randomBtn.setOnMouseClicked(randomProblem);
    }
}
