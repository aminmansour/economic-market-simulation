package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.*;
import view.ChartPane;
import view.CountryNode;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sarosi on 30/11/2016.
 */
public class QueryController implements EventHandler<MouseEvent> {

    private ChartPane chartPane;

    public QueryController(ChartPane chartPane) {
        this.chartPane = chartPane;


    }
//actionlistener of go calls this method

    @Override
    public void handle(MouseEvent event) {
        if (!(((CountryNode) chartPane.getCountriesPane().getChildren().get(0)).getCountries().getValue().equals("Select a country") && chartPane.getCountriesPane().getChildren().size() == 1)) {
            ArrayBuilder query = new ArrayBuilder();
            CountryReader crListOfCountries = null;
            try {
                crListOfCountries = new CountryReader("src/main/resources/storage/CountryCodesCore.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }

            CountryReader indicatorConverter = null;
            try {
                indicatorConverter = new CountryReader("src/main/resources/storage/IndicatorCodesCore.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }

            ArrayList<String> countriesFormatted = chartPane.countrynames();
            ArrayList<String> countriesCoded = CountryNamesToCodes.convert(countriesFormatted, crListOfCountries);

            ArrayList<ArrayList<DataPiece>> toBeCharted = null;
            try {
                toBeCharted = query.buildArray(countriesCoded, chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(), CountryNamesToCodes.singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
            } catch (Exception e) {
                e.printStackTrace();
            }

            ChartBuillder chartBuillder = new ChartBuillder();

//        chartPane.getChildren().removeAll();
            chartPane.setCenterLineChart(chartBuillder.buildLineChart(toBeCharted));

        }
    }

}
