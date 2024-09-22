package g61453.atl;

import g61453.atl.controller.JavaFxController;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainJavaFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        JavaFxController javaFxController = new JavaFxController(stage);
    }
}
