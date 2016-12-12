/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.CountryNamesToCodes;
import model.CountryReader;
import model.History;
import view.InterfaceScene;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icons/EconIcon.png")));
        InterfaceScene main = new InterfaceScene(primaryStage);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(840);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.setMaximized(true);
        String csvFile = "src/main/resources/storage/CountryCodesCore.csv";
        CountryReader charles = new CountryReader(csvFile);
        LineChart<String, Number> chart2 = new LineChart<String, Number>(new CategoryAxis(),(new NumberAxis()));
        History history = new History();
        main.loadStore(primaryStage, chart2, history);

        //ADD ERROR HANDLING TO Arraybuider and ChartBuider for when there is no data for given year
        //ADD CSV of indicators and indicator codes eg.: Gross Domestic Product, NY.GDP.MKTP.CD
        primaryStage.setTitle("Economics Virtual Assistant");


    }
}