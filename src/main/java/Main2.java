/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import view.HistoryPane;

import java.util.ArrayList;

public class Main2 extends Application {

    public static void main(String[] args) {


        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        History hist = new History();

        primaryStage.setScene(new Scene(new HistoryPane(hist)));
        primaryStage.show();
    }
}
