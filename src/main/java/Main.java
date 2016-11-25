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

    public static void main(String[] args) {
        testJSONParsing test = new testJSONParsing();
        JSONArray jsonarr = null;
        String pages = null;
        String page = null;
        try {
            jsonarr = test.httpGET("http://api.worldbank.org/countries/all/indicators/SP.POP.TOTL?format=jsonP&prefix=Getdata");
        } catch(Exception exe){
            //Your error handling code
        }

        HashMap<String, String> applicationSettings = new HashMap<String,String>();
        for(int i=0; i<jsonarr.length(); i++){
            String value = jsonarr.getJSONObject(i).getString("page");
            String name = jsonarr.getJSONObject(i).getString("pages");
            applicationSettings.put(name, value);
        }

        for ( String key : applicationSettings.keySet() ) {
            System.out.println( key );
        }



        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new StackPane(),1000,600));
        primaryStage.show();
    }
}
