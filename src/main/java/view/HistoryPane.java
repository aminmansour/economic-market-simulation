package view;

import javafx.event.*;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.ChartBuillder;
import model.DataPiece;
import model.History;

import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import javafx.scene.input.MouseEvent;


/**
 * Created by Sarosi on 07/12/2016.
 */
public class HistoryPane extends BorderPane {

    private History localhistory;

    public HistoryPane(History hist){
        getStylesheets().add("css/chartPane-style.css");
        setPadding(new Insets(30, 0, 0, 306));
        localhistory = hist;
       Collection<ArrayList<ArrayList<DataPiece>>> valset =localhistory.getHistories().values();
       GridPane fl = new GridPane();
       fl.setPadding(new Insets(10,5,10,5));
       javafx.scene.control.ScrollPane scp = new javafx.scene.control.ScrollPane(fl);
        scp.setStyle("-fx-background-color: white; -fx-focus-color: transparent;   -fx-background: #FFFFFF; -fx-border-color: #FFFFFF;");

        setRight(scp);


       int i = 0;
        for (ArrayList k: valset){

//        String s = hist.getHistories().get(0).get(i).get(0).getIndicator() + "for";
            Button elemennt = new Button(localhistory.getId(k));
            elemennt.setPadding(new Insets(5,5,5,0));
            GridPane.setMargin(elemennt,new Insets(5,0,0,0));
            fl.add(elemennt, 0,i);
            elemennt.setId("bQuery");
            ChartBuillder ch = new ChartBuillder();
            LineChart<String,Number> ln = ch.buildLineChart(k);
            elemennt.setOnAction((event) -> {
                setCenter(ln);            });
            i++;
        }
    }

}
