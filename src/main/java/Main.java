/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
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


        String csvFile = "src\\main\\resources\\storage\\CountryCodesCore.csv";
        CountryReader charles = new CountryReader(csvFile);
        ArrayList<String> lands = new ArrayList<String>();
        lands.add("Hungary");
        lands.add("Bulgaria");
        lands.add("Jordan");
        lands.add("Morocco");
        lands.add("United Kingdom");
        lands.add("Netherlands");
        ArrayList<String> counties =  new CountryNamesToCodes().convert(lands,charles);
        LineChart<String,Number> chart = new ChartBuillder().buildLineChart(new ArrayBuilder().buildArray(counties,"1995","2005","NY.GNP.MKTP.CD"));

        //ADD ERROR HANDLING TO Arraybuider and ChartBuider for when there is no data for given year
        //ADD CSV of indicators and indicator codes eg.: Gross Domestic Product, NY.GDP.MKTP.CD

        primaryStage.setWidth(1080);
        primaryStage.setHeight(640);
        primaryStage.setTitle("Project Core");
        BorderPane bp = new BorderPane();
        Button go = new Button("GO");
        StackPane st = new StackPane();
        st.getChildren().add(go);
        bp.setRight(st);

        bp.setCenter(chart);

        primaryStage.setScene(new Scene(bp));
        primaryStage.show();
    }
}