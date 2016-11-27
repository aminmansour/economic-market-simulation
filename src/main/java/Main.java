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
import model.CountryNamesToCodes;
import model.CountryReader;
import view.InterfaceScene;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        String csvFile = "C:\\Users\\Sarosi\\Desktop\\Core\\src\\main\\resources\\storage\\CountryCodesCore.csv";
        CountryReader charles = new CountryReader(csvFile);
        ArrayList<String> lands = new ArrayList<String>();
        lands.add("Hungary");
        lands.add("Bulgaria");
        lands.add("Jordan");
        lands.add("Morocco");
        CountryNamesToCodes converter = new CountryNamesToCodes();
        ArrayList<String> counties =  converter.convert(lands,charles);


        LineChart<String,Number> chart = new ChartBuillder().buildLineChart(new ArrayBuilder().buildArray(counties,"1995","2005","NY.GNP.MKTP.CD"));




        primaryStage.setWidth(1080);
        primaryStage.setHeight(640);
        primaryStage.setTitle("Macro Economics");
        primaryStage.setScene(new InterfaceScene(primaryStage,chart));
        primaryStage.show();
    }
}