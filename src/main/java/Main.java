/**
 * Created by Amans on 23/11/2016.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;
import org.json.JSONArray;
import view.HistoryPane;
import view.InterfaceScene;

import java.awt.*;
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
        System.setProperty("glass.accessible.force", "false");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icons/EconIcon.png")));
        InterfaceScene main = new InterfaceScene(primaryStage);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(840);
        primaryStage.setScene(main);
        primaryStage.show();
        primaryStage.setMaximized(true);
        LineChart<String, Number> chart2 = new LineChart<String, Number>(new CategoryAxis(),(new NumberAxis()));
        History history = new History();
        main.doWork(primaryStage, chart2, history);
        primaryStage.setTitle("Economics Virtual Assistant");

    }
}