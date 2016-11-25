/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.testJSONParsing;

public class Main extends Application {

    public static void main(String[] args) {

        testJSONParsing test = new testJSONParsing();
        try {
            test.httpGET("http://api.worldbank.org/countries/all/indicators/SP.POP.TOTL?format=jsonP&prefix=Getdata");
        } catch(Exception exe){
            //Your error handling code
        }

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new StackPane(),1000,600));
        primaryStage.show();
    }
}
