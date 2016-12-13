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
 * History pane which will contain and display a list of all the data that has been cached recently and allow the user to display
 * the data in either a line chart or bar chart.
 */
public class HistoryPane extends BorderPane {


    private ToggleGroup tgViewType;
    private RadioButton rbBar;
    private RadioButton rbLine;
    private GridPane gpFlowPane;

    /**
     * a view displaying the search history of previous queries.
     * @param hist the archive of previous searches
     */
    public HistoryPane(History hist){
        Button clear = new Button("Delete History");

        getStylesheets().add("css/chartPane-style.css");
        setPadding(new Insets(30, 0, 0, 306));
        History localhistory = hist;
        Collection<ArrayList<ArrayList<DataPiece>>> valset = localhistory.getDataStore().values();
        gpFlowPane = new GridPane();
        gpFlowPane.add(new Label("History: "), 0, 4);
        tgViewType = new ToggleGroup();
        rbBar = new RadioButton("bar-chart");
        rbLine = new RadioButton("line-chart");
        rbBar.setToggleGroup(tgViewType);
        rbLine.setToggleGroup(tgViewType);
        Label chartype = new Label("Chart Type: ");
        gpFlowPane.add(chartype, 0, 1);
        gpFlowPane.setMargin(clear, new Insets(0, 90, 0, 0));
        gpFlowPane.add(rbBar, 0, 2);
        gpFlowPane.add(rbLine, 0, 3);
        gpFlowPane.setPadding(new Insets(10, 5, 10, 5));
        gpFlowPane.add(clear, 0, 0);
        rbLine.setSelected(true);
        javafx.scene.control.ScrollPane scp = new javafx.scene.control.ScrollPane(gpFlowPane);
        scp.setStyle("-fx-background-color: white; -fx-focus-color: transparent;   -fx-background: #FFFFFF; -fx-border-color: #FFFFFF;");
        setRight(scp);
        gpFlowPane.setVgap(20);
        clear.setOnAction((event) ->{

            hist.clear();
            if (gpFlowPane.getChildren().size() > 5) {
                gpFlowPane.getChildren().remove(5, gpFlowPane.getChildren().size());
                setCenter(new Label("Nothing to show"));
            }
            ((Label) getCenter()).setText("Nothing to show");

        });


        int i = 0;
        createHistoryRecords(localhistory, valset, i);

    }

    //creates a list of all the data recorded in cache and adds to each a listener which allows the user to click on it
    private void createHistoryRecords(History localhistory, Collection<ArrayList<ArrayList<DataPiece>>> valset, int i) {
        for (ArrayList k: valset){

            Button elemennt = new Button(localhistory.getKey(k));
            elemennt.setPadding(new Insets(5,5,5,0));
            GridPane.setMargin(elemennt,new Insets(5,0,0,0));
            gpFlowPane.add(elemennt, 0, i + 5);
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

            if (gpFlowPane.getChildren().size() > 5) {
                setCenter(new Label("Select a diagram to view"));
            } else {
                setCenter(new Label("Nothing to show"));
            }

        }
    }


}
