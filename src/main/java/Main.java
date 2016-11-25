/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.testJSONParsing;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        testJSONParsing test = new testJSONParsing();
        JSONArray jsonArray = test.httpGET("http://api.worldbank.org/countries/all/indicators/SP.POP.TOTL?format=json");

        System.out.println(jsonArray.get(1));

        //test.printJsonObject(jsonObj);


        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new StackPane(),1000,600));
        primaryStage.show();
    }
}
