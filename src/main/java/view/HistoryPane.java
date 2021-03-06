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
import model.*;

import java.io.IOException;
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
    public HistoryPane(History hist) {
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

        Button clear = new Button("Delete History");
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
        //sets up actions listener to clear all histories in cache

        setUpClearListener(hist, clear);
        int i = 0;
        CountryCodeDictionary indicatorConverter = null;
        try {
            indicatorConverter = new CountryCodeDictionary("src/main/resources/storage/IndicatorCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        createButtonStackRepresentingCache(localhistory, valset, i, indicatorConverter);
    }

    //creates all buttons representing the cache data
    private void createButtonStackRepresentingCache(History localhistory, Collection<ArrayList<ArrayList<DataPiece>>> valset, int i, CountryCodeDictionary indicatorConverter) {
        for (ArrayList<ArrayList<DataPiece>> cachedDataInstance : valset) {
            //strings that will be represented  within button
                String historyID = localhistory.getKey(cachedDataInstance);
                String[] countries = historyID.split("\\+")[0].split("(?<=\\G..)");
                ;
                String restOfId = historyID.split("\\+")[1];
                String toYears = restOfId.substring(0, 8).substring(0, 4);
                String fromYears = restOfId.substring(0, 8).substring(4);
                String indicatorCode = restOfId.substring(8);
                String seperated = "";
                for (String country : countries) {
                    seperated = seperated + country + ",";
                }
                String indicatorString = new ConversionFactory().backwardsConvert(indicatorCode, indicatorConverter);
                //creates single button to view and review for a particular cached data
                createHistoryStoreButton(i, cachedDataInstance, toYears, fromYears, seperated, indicatorString);
                i++;

                if (gpFlowPane.getChildren().size() > 5) {
                    setCenter(new Label("Select a diagram to view"));
                } else {
                    setCenter(new Label("Nothing to show"));
                }

        }
    }

    //creates a button to represent particular data
    private void createHistoryStoreButton(int i, ArrayList k, String toYears, String fromYears, String seperated, String indicatorString) {
        Button bCacheInstancce = new Button(seperated + " " + toYears + " - " + fromYears + " " + indicatorString);
        bCacheInstancce.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setMargin(bCacheInstancce, new Insets(5, 0, 0, 0));
        gpFlowPane.add(bCacheInstancce, 0, i + 5);
        bCacheInstancce.setId("bQuery");
        ChartBuillder ch = new ChartBuillder();
        bCacheInstancce.setOnAction((event) -> {
            if (tgViewType.getSelectedToggle() == rbLine) {
                LineChart<String, Number> ln = ch.buildLineChart(k);
                setCenter(ln);
            }
            if (tgViewType.getSelectedToggle() == rbBar) {
                BarChart<String, Number> br = ch.buildBarChart(k);
                setCenter(br);
            }
        });
    }

    //add a listener to the clear button which removes all the cache history
    private void setUpClearListener(History hist, Button clear) {
        clear.setOnAction((event) -> {

            hist.clear();
            if (gpFlowPane.getChildren().size() > 5) {
                gpFlowPane.getChildren().remove(5, gpFlowPane.getChildren().size());
                setCenter(new Label("Nothing to show"));
            }
            ((Label) getCenter()).setText("Nothing to show");

        });
    }


}
