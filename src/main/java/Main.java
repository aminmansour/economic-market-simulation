/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.CountryCodeDictionary;
import model.History;
import view.InterfaceScene;

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
        CountryCodeDictionary charles = new CountryCodeDictionary(csvFile);
        ObservableList<String> years = FXCollections.observableArrayList();
        for (int i = 1915; i < 2017; i++) {
            years.add(Integer.toString(i));        }
        final CategoryAxis xAxis = new CategoryAxis(years);
        final NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> chart2 = new LineChart<String, Number>(xAxis,yAxis);
      XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        for (int i = 1960; i < 2017; i++) {
            series.getData().add(new XYChart.Data(Integer.toString(i),0 ));
        }
        chart2.getData().add(series);

        History history = new History();
        main.loadStore(primaryStage, chart2, history);

        //ADD ERROR HANDLING TO Arraybuider and ChartBuider for when there is no data for given year
        //ADD CSV of indicators and indicator codes eg.: Gross Domestic Product, NY.GDP.MKTP.CD
        primaryStage.setTitle("Economics Virtual Assistant");


    }
}