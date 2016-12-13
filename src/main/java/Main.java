/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import model.*;
import view.InterfaceScene;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("image/logo-icon.png")));
        InterfaceScene main = new InterfaceScene(primaryStage);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(840);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.setMaximized(true);
        String csvFile = "src/main/resources/storage/CountryCodesCore.csv";
        CountryCodeDictionary charles = new CountryCodeDictionary(csvFile);
        LineChart<String, Number> chart2 = new LineChart<String, Number>(new CategoryAxis(),(new NumberAxis()));
        History history = new History();
        main.loadStore(primaryStage, chart2, history);

        //ADD ERROR HANDLING TO Arraybuider and ChartBuider for when there is no data for given year
        //ADD CSV of indicators and indicator codes eg.: Gross Domestic Product, NY.GDP.MKTP.CD
        primaryStage.setTitle("Economics Virtual Assistant");


    }
}