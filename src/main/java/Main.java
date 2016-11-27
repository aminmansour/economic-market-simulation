/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.InterfaceScene;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setWidth(1080);
        primaryStage.setHeight(640);
        primaryStage.setTitle("Macro Economics");
        primaryStage.setScene(new InterfaceScene(primaryStage));
        primaryStage.show();
    }
}
