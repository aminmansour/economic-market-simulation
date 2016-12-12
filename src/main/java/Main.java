/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
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


        InterfaceScene main = new InterfaceScene(primaryStage);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(840);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.setMaximized(true);
        String csvFile = "src/main/resources/storage/CountryCodesCore.csv";
        CountryReader charles = new CountryReader(csvFile);
        ArrayList<String> lands = new ArrayList<String>();
        lands.add("Bulgaria");
        ArrayList<String> counties = new CountryNamesToCodes().convert(lands, charles);
        // BarChart<String,Number> chart = new ChartBuillder().buildLineChart(new ArrayBuilder().buildArray(counties,"1995","2005","NY.GNP.MKTP.CD"));
        LineChart<String, Number> chart2 = new LineChart<String, Number>(new CategoryAxis(),(new NumberAxis()));

        History history = new History();
        main.loadStore(primaryStage, chart2, history);

        //ADD ERROR HANDLING TO Arraybuider and ChartBuider for when there is no data for given year
        //ADD CSV of indicators and indicator codes eg.: Gross Domestic Product, NY.GDP.MKTP.CD

        primaryStage.setTitle("Project Core");
        //StockIndicators test = new StockIndicators();
        //System.out.println(test.getAAPLBid());

//        BorderPane bp = new BorderPane();
//        Button go = new Button("GO");
//        StackPane st = new StackPane();
//        st.getChildren().add(go);
//        bp.setRight(st);

        //bp.setCenter(chart);

        //primaryStage.setScene(new Scene(new ChartBuillder().buildBarChart(new ArrayBuilder().buildArray(counties,"1995","2005","NY.GNP.MKTP.CD"))));;


    }
}