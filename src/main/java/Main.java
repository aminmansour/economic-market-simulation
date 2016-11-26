/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.ArrayBuilder;
import model.ChartBuillder;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {


        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        ArrayBuilder theArchitect = new ArrayBuilder();
        ArrayList<String> counties = new ArrayList<String>();
        counties.add("br");
        counties.add("gb");
        counties.add("us");

        ChartBuillder Ted = new ChartBuillder();



        //Scene scene  = new Scene(Ted.buildChart(theArchitect.buildArray(counties)),800,600);
        primaryStage.setScene(new Scene(Ted.buildChart(theArchitect.buildArray(counties,"1999","2005","SP.POP.TOTL")),800,600));
        primaryStage.show();
    }
}
