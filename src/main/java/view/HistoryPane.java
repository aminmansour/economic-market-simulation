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
 */
public class HistoryPane extends BorderPane {


    private ToggleGroup tgViewType;
    private RadioButton rbBar;
    private RadioButton rbLine;

    private GridPane fl;

    private History localhistory;

    public HistoryPane(History hist){
        Button clear = new Button("Delete History");

        getStylesheets().add("css/chartPane-style.css");
        setPadding(new Insets(30, 0, 0, 306));
        localhistory = hist;
        Collection<ArrayList<ArrayList<DataPiece>>> valset =localhistory.getHistories().values();
        fl = new GridPane();
        fl.add(new Label("History: "),0,4);
        tgViewType = new ToggleGroup();
        rbBar = new RadioButton("bar-chart");
        rbLine = new RadioButton("line-chart");
        rbBar.setToggleGroup(tgViewType);
        rbLine.setToggleGroup(tgViewType);
        Label chartype = new Label("Chart Type: ");
        fl.add(chartype,0,1);
        fl.setMargin(clear, new Insets(0, 90, 0, 0));
        fl.add(rbBar,0,2);
        fl.add(rbLine,0,3);
        fl.setPadding(new Insets(10, 5, 10, 5));
        fl.add(clear, 0, 0);
        rbLine.setSelected(true);
        javafx.scene.control.ScrollPane scp = new javafx.scene.control.ScrollPane(fl);
        scp.setStyle("-fx-background-color: white; -fx-focus-color: transparent;   -fx-background: #FFFFFF; -fx-border-color: #FFFFFF;");
        setRight(scp);

        fl.setVgap(10);
        if (fl.getChildren().size() > 5) {
            setCenter(new Label("Select a diagram to view"));
        } else {
            setCenter(new Label("Nothing to show"));
        }
        clear.setOnAction((event) ->{

            hist.clear();
            if (fl.getChildren().size() > 5) {
                fl.getChildren().remove(5, fl.getChildren().size());
                setCenter(new Label("Nothing to show"));
            }
            ((Label) getCenter()).setText("Nothing to show");

        });


        int i = 0;

        CountryReader indicatorConverter = null;
        try {
            indicatorConverter = new CountryReader("src/main/resources/storage/IndicatorCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ArrayList k: valset){

            String historyID = localhistory.getId(k);

            String[] countries = historyID.split("\\+")[0].split("(?<=\\G..)");;
            String restOfId =  historyID.split("\\+")[1];

            String toYears = restOfId.substring(0,8).substring(0,4);
            String fromYears = restOfId.substring(0,8).substring(4);

            String indicatorCode = restOfId.substring(8);

            String seperated = "";

            for(String country : countries) {
                seperated = seperated + country + ",";
            }

            String indicatorString = new CountryNamesToCodes().backwardsConvert(indicatorCode, indicatorConverter);

            Button elemennt = new Button(seperated + " " + toYears + " - " + fromYears + " " + indicatorString);
            elemennt.setPadding(new Insets(10,10,10,10));
            GridPane.setMargin(elemennt,new Insets(5,0,0,0));
            fl.add(elemennt, 0,i+5);
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

            if (fl.getChildren().size() > 5) {
                setCenter(new Label("Select a diagram to view"));
            } else {
                setCenter(new Label("Nothing to show"));
            }

        }

    }


}
