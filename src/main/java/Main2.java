/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ArrayBuilder;
import model.ChartBuillder;
import model.CountryReader;
import model.CountryNamesToCodes;

import java.util.ArrayList;

public class Main2 extends Application {

    public static void main(String[] args) {


        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        ArrayBuilder theArchitect = new ArrayBuilder();
        String csvFile = "C:\\Users\\Sarosi\\Desktop\\Core\\src\\main\\resources\\storage\\CountryCodesCore.csv";
        CountryReader charles = new CountryReader(csvFile);
        ArrayList<String> lands = new ArrayList<String>();
        lands.add("Hungary");
        lands.add("Bulgaria");
        lands.add("Jordan");
        lands.add("Morocco");
        CountryNamesToCodes converter = new CountryNamesToCodes();
        ArrayList<String> counties =  converter.convert(lands,charles);



        ChartBuillder Ted = new ChartBuillder();


        //LineChart<String,Number> lineChart = Ted.buildChart(theArchitect.buildArray(counties, "1999", "2005","NY.GDP.MKTP.CD" ));
        //Scene scene  = new Scene(Ted.buildChart(theArchitect.buildArray(counties, "1999", "2005","NY.GDP.MKTP.CD" )),800,600);
        primaryStage.setScene(new Scene(Ted.buildLineChart(theArchitect.buildArray(counties,"1995","2005","NY.GNP.MKTP.CD")),800,600));
        primaryStage.show();
    }
}
