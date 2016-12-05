package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.*;
import view.ChartPane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sarosi on 30/11/2016.
 */
public class QueryController implements EventHandler<MouseEvent> {

    private ChartPane chartPane;

    public QueryController(ChartPane chartPane) throws Exception {
        this.chartPane = chartPane;


    }
//actionlistener of go calls this method

    @Override
    public void handle(MouseEvent event) {
        ArrayBuilder AB = new ArrayBuilder();
        CountryReader charles = null;
        try {
            charles = new CountryReader("src/main/resources/storage/CountryCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CountryReader indicatorConverter = null;
        try {
            indicatorConverter = new CountryReader("src/main/resources/storage/IndicatorCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> countries = chartPane.countrynames();
        ArrayList<String> counties =  new CountryNamesToCodes().convert(countries,charles);

        ArrayList<ArrayList<DataPiece>> toBeCharted = null;
        try {
            toBeCharted = AB.buildArray(counties,chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(),new CountryNamesToCodes().singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChartBuillder chartBuillder = new ChartBuillder();

//        chartPane.getChildren().removeAll();
        chartPane.setCenterLineChart(chartBuillder.buildLineChart(toBeCharted));

    }
}
