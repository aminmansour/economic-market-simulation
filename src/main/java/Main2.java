/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import jdk.nashorn.internal.scripts.JO;
import model.testJSONParsing;
import model.urlBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main2 extends Application {

    public static void main(String[] args) throws Exception {





        launch(args);



    }


    @Override
    public void start(Stage stage) throws Exception {

        testJSONParsing test = new testJSONParsing();
        JSONArray jsonArray = test.httpGET("http://api.worldbank.org/countries/all/indicators/SP.POP.TOTL?format=json");

        System.out.println(jsonArray.get(1));

        urlBuilder Bob = new urlBuilder();

        String a = Bob.URL("br","SP.ADO.TFRT","1961", "2014");

        JSONArray Bonobo = test.httpGET(a);

        JSONArray John = (JSONArray)Bonobo.get(1);

        ArrayList<Pair<String, Number>> masterArray = new ArrayList<Pair<String, Number>>();

        //JSONObject Hani = John.getJSONObject(1);

       // String country = Hani.getString()

        for(int i = John.length() - 1; i >= 0; --i) {

            JSONObject current = John.getJSONObject(i);

            double value1 = Double.parseDouble(current.getString("value"));

            String year1 = current.getString("date");

            Pair<String, Number> temp = new Pair<String, Number>(year1, value1);

            masterArray.add(temp);

        }

        // gets year by index
        //JSONObject Hani = John.getJSONObject(1);

        //System.out.println(Hani);
        //gets value from given year


        stage.setTitle("Line Chart Motherfucker!");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Line Chart Motherfucker!");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Brazil");

        for(Pair<String, Number> pair: masterArray) {
            series1.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
        }


        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1);

        stage.setScene(scene);
        stage.show();
    }
}
