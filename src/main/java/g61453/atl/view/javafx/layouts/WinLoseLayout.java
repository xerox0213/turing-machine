package g61453.atl.view.javafx.layouts;

import g61453.atl.controller.JavaFxController;
import g61453.atl.view.javafx.TMColors;
import g61453.atl.view.javafx.components.CommandButton;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.InputStream;

public class WinLoseLayout extends VBox {

    public WinLoseLayout(JavaFxController javaFxController, boolean result) {
        Text text = new Text();
        CommandButton quitGameBtn = new CommandButton("Quit game", TMColors.RED);
        EventHandler<MouseEvent> quitGame = e -> {
            JavaFxController.Event event = JavaFxController.Event.GIVE_UP;
            javaFxController.sendUserEvent(event, null);
        };
        quitGameBtn.setOnMouseClicked(quitGame);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("images/robot" + (result ? "a" : "c") + ".png");
        if (is != null) {
            Image image = new Image(is);
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(250);
            this.getChildren().add(imageView);
        }
        if (result) {
            text.setText("Bingo! You've cracked the code! Excellent job!");
            text.setFill(TMColors.GREEN.getDark());
        } else {
            text.setText("Wops, it's not the secret code, you have been defeated by the machine!");
            text.setFill(TMColors.RED.getDark());
        }
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(text, quitGameBtn);
        this.setSpacing(30);
    }
}
