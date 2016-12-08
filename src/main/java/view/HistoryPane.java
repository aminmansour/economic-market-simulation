package view;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.ChartBuillder;
import model.DataPiece;
import model.History;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by Sarosi on 07/12/2016.
 */
public class HistoryPane extends BorderPane {


    ToggleGroup  tgViewType;
    RadioButton   rbBar;
    RadioButton  rbLine;

    private GridPane fl;

    private History localhistory;

    public HistoryPane(History hist){
        Button clear = new Button("Delete History");

        getStylesheets().add("css/chartPane-style.css");
        setPadding(new Insets(30, 0, 0, 306));
        localhistory = hist;
        Collection<ArrayList<ArrayList<DataPiece>>> valset =localhistory.getHistories().values();
        fl = new GridPane();
        fl.setVgap(20);
        fl = new GridPane();
        fl.add(new Label("History: "),0,4);
        tgViewType = new ToggleGroup();
        rbBar = new RadioButton("bar-chart");
        rbLine = new RadioButton("line-chart");
        rbBar.setToggleGroup(tgViewType);
        rbLine.setToggleGroup(tgViewType);
        Label chartype = new Label("Chart Type: ");
        fl.add(chartype,0,1);
        fl.add(rbBar,0,2);
        fl.add(rbLine,0,3);
        fl.setPadding(new Insets(10, 5, 10, 5));
        fl.add(clear, 0, 0);
        rbLine.setSelected(true);
        javafx.scene.control.ScrollPane scp = new javafx.scene.control.ScrollPane(fl);
        scp.setStyle("-fx-background-color: white; -fx-focus-color: transparent;   -fx-background: #FFFFFF; -fx-border-color: #FFFFFF;");
        setCenter(new Label("Select a diagram to view"));
        setRight(scp);

        clear.setOnAction((event) ->{

            hist.clear();
            update();

        });


        int i = 0;


        for (ArrayList k: valset){

            Button elemennt = new Button(localhistory.getId(k));
            elemennt.setPadding(new Insets(5,5,5,0));
            GridPane.setMargin(elemennt,new Insets(5,0,0,0));
            fl.add(elemennt, 0,i+5);
            elemennt.setId("bQuery");
            ChartBuillder ch = new ChartBuillder();

            elemennt.setOnAction((event) -> {

                if (tgViewType.getSelectedToggle() == rbLine) {


                    LineChart<String, Number> ln = ch.buildLineChart(k);

                    setCenter(ln);
                    update();
                }
                if (tgViewType.getSelectedToggle() == rbBar) {

                    BarChart<String, Number> br = ch.buildBarChart(k);
                    setCenter(br);
                    update();
                }


            });
            i++;
        }
    }


    public void update(){

        /*

                fl.add(new Label("Lets do it like they do it on the discovery channel."),0,5);


        Collection<ArrayList<ArrayList<DataPiece>>> valset =localhistory.getHistories().values();

        int i = 0;

        for (ArrayList k: valset){

            Button elemennt = new Button(localhistory.getId(k));
            elemennt.setPadding(new Insets(5,5,5,0));
            GridPane.setMargin(elemennt,new Insets(5,0,0,0));
            fl.add(elemennt, 0,i+6);
            elemennt.setId("bQuery");
            ChartBuillder ch = new ChartBuillder();

            elemennt.setOnAction((event) -> {

                if (tgViewType.getSelectedToggle() == rbLine) {

                    LineChart<String, Number> ln = ch.buildLineChart(k);

                    setCenter(ln);
                }
                if (tgViewType.getSelectedToggle() == rbBar) {

                    BarChart<String, Number> br = ch.buildBarChart(k);
                    setCenter(br);
                }


            });
            i++;

        }
        */
        System.out.println("this feature workth not");
    }

}
