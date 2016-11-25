/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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




        urlBuilder Bob = new urlBuilder();

        String a =  Bob.URL("br","SP.ADO.TFRT","2004", "2006");

        JSONArray Bonobo = test.httpGET(a);

        JSONArray John = (JSONArray)Bonobo.get(1);
        // gets year by index
        JSONObject Hani = John.getJSONObject(1);

        System.out.println(Hani);
        //gets value from given year
        Double value1 = Double.parseDouble(Hani.getString("value"));
        String year1 = Hani.getString("date");



        JSONObject countryArray = Hani.getJSONObject("country");
        String countyName = countryArray.getString("value");

       // String coutryName = countryObject.getString("value");









        System.out.println(value1);
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(countyName);

        series1.getData().add(new XYChart.Data(year1, value1));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1);

        stage.setScene(scene);
        stage.show();
    }
}
