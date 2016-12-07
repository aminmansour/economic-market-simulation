/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;
import view.InterfaceScene;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        String csvFile = "src/main/resources/storage/CountryCodesCore.csv";
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

        System.out.println("deSerializing");
        History history = new History();
        System.out.println("stopped deSerializing");

        //ADD ERROR HANDLING TO Arraybuider and ChartBuider for when there is no data for given year
        //ADD CSV of indicators and indicator codes eg.: Gross Domestic Product, NY.GDP.MKTP.CD

        primaryStage.setWidth(1200);
        primaryStage.setHeight(640);
        primaryStage.setTitle("Project Core");
//        BorderPane bp = new BorderPane();
//        Button go = new Button("GO");
//        StackPane st = new StackPane();
//        st.getChildren().add(go);
//        bp.setRight(st);

        //bp.setCenter(chart);

        primaryStage.setScene(new InterfaceScene(primaryStage, chart, history));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                try{
                FileOutputStream fos = new FileOutputStream("src/main/resources/storage/hashmap.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(history.getHistories());
                oos.close();
                fos.close();
                System.out.printf("Serialized HashMap data is saved in hashmap.ser");
            }catch(IOException ioe)
            {
                ioe.printStackTrace();
            }

            }
        });
    }
}