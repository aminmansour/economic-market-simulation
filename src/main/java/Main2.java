/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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

import java.lang.reflect.Array;
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

        //test.printJsonObject(jsonObj);


        ArrayList<ArrayList<ArrayList<String>>> galaxy = new  ArrayList<ArrayList<ArrayList<String>>>();

        ArrayList<String> countries = new ArrayList<String>();

        countries.add("br");
        countries.add("gb");

        for (int w = 0; w < countries.size(); w++) {


            urlBuilder Bob = new urlBuilder();

            String a = Bob.URL(countries.get(w), "SP.ADO.TFRT", "1990", "2006");

            JSONArray Bonobo = test.httpGET(a);

            JSONArray John = (JSONArray) Bonobo.get(1);

            //System.out.println(John);

            ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();

            for (int i = John.length() - 1; i >= 0; i--) {
                ArrayList<String> inner = new ArrayList<String>();
                JSONObject Jake = John.getJSONObject(i);
                Double value1 = Double.parseDouble(Jake.getString("value"));
                String valueX1 = Jake.getString("value");
                String year1 = Jake.getString("date");
                JSONObject countryArray = Jake.getJSONObject("country");
                String countyName = countryArray.getString("value");
                inner.add(valueX1);
                inner.add(year1);
                inner.add(countyName);
                outer.add(inner);


            }
            System.out.println("outer");
            System.out.println(outer);

            JSONObject Hani = John.getJSONObject(1);


            System.out.println(Hani);

            Double value1 = Double.parseDouble(Hani.getString("value"));

            String year1 = Hani.getString("date");
            JSONObject countryArray = Hani.getJSONObject("country");
            String countyName = countryArray.getString("value");

            // String coutryName = countryObject.getString("value");


            System.out.println(value1);

            galaxy.add(outer);

        }
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        ArrayList<XYChart.Series> SerialKiller = new ArrayList<XYChart.Series>();




        for (int q = 0; q < galaxy.size(); q++) {
            SerialKiller.add(new XYChart.Series());
            SerialKiller.get(q).setName(galaxy.get(q).get(0).get(2));

            for (int i = 0; i < galaxy.get(q).size(); i++) {
                System.out.println(SerialKiller.get(q).getName());
                SerialKiller.get(q).getData().add(new XYChart.Data(galaxy.get(q).get(i).get(1), Double.parseDouble(galaxy.get(q).get(i).get(0))));
            }

            lineChart.getData().add(SerialKiller.get(q));

        }


        Scene scene  = new Scene(lineChart,800,600);

        stage.setScene(scene);
        stage.show();
    }
}
