/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.scripts.JO;
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
import model.CountryReader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2{

    public static void main(String[] args) throws Exception {
        String csvFile = "C:\\Users\\Sarosi\\Desktop\\Core\\src\\main\\resources\\storage\\CountryCodesCore.csv";

        CountryReader charles = new CountryReader(csvFile);

        System.out.println( charles.getPairs());


    }
}

