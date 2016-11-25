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
import model.urlBuilder;

import java.util.HashMap;

public class Main extends Application {

    public static void main(String[] args) throws Exception {

        testJSONParsing test = new testJSONParsing();
        JSONArray jsonArray = test.httpGET("http://api.worldbank.org/countries/all/indicators/SP.POP.TOTL?format=json");

        System.out.println(jsonArray.get(1));

        //test.printJsonObject(jsonObj);




        urlBuilder Bob = new urlBuilder();

        String a =  Bob.URL("br","SP.ADO.TFRT","2004", "2006");

       JSONArray Bonobo = test.httpGET(a);

       JSONArray John = (JSONArray)Bonobo.get(1);
        // gets year by index
       JSONObject Hani = John.getJSONObject(1);

        System.out.println(Hani);
        //gets value from given year
        String loudScreaming = Hani.getString("value");

        System.out.println(loudScreaming);



        //launch(args);



    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new StackPane(),1000,600));
        primaryStage.show();
    }
}
