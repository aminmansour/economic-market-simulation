package controller;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
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

            History adding = chartPane.getHistory();

            String countries = "";

            for (String code : countriesCoded) {
                countries = countries + code;
            }

            ChartBuillder chartBuillder = new ChartBuillder();


            Boolean isInsideMap = false;
            String savedMapId = null;

            String searchId = countries + "+" + chartPane.getTfFrom().getText() + chartPane.getTfTo().getText() + CountryNamesToCodes.singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter);

            for (String mapId : adding.getHistories().keySet()) {

                if (adding.compareIds(mapId, searchId)) {
                    isInsideMap = true;
                    savedMapId = mapId;
                    break;
                }
            }

            if (chartPane.getTgViewType().getSelectedToggle() == chartPane.getRbLine()) {

                if (isInsideMap) {

                    ArrayList<ArrayList<DataPiece>> newChart = adding.getLineChart(savedMapId);

                    System.out.println("Bibitibopbop");

                    LineChart<String, Number> xy = chartBuillder.buildLineChart(newChart);
                    chartPane.setCenterLineChart(xy);

                } else {
                    ArrayList<ArrayList<DataPiece>> toBeCharted = null;
                    try {
                        toBeCharted = query.buildArray(countriesCoded, chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(), CountryNamesToCodes.singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    LineChart<String, Number> charts = chartBuillder.buildLineChart(toBeCharted);
                    chartPane.setCenterLineChart(charts);

                    adding.getHistories().put(countries + "+" + chartPane.getTfFrom().getText() + chartPane.getTfTo().getText() + CountryNamesToCodes.singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter), toBeCharted);
                }
            }
            if (chartPane.getTgViewType().getSelectedToggle() == chartPane.getRbBar()) {
                if (isInsideMap) {

                    ArrayList<ArrayList<DataPiece>> newChart = adding.getLineChart(savedMapId);

                    System.out.println("Bibitibopbop");
                    BarChart<String, Number> xy = chartBuillder.buildBarChart(newChart);

                    chartPane.setCenterLineChart(xy);

                } else {
                    ArrayList<ArrayList<DataPiece>> toBeCharted = null;
                    try {
                        toBeCharted = query.buildArray(countriesCoded, chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(), CountryNamesToCodes.singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    BarChart<String, Number> charts = chartBuillder.buildBarChart(toBeCharted);
                    chartPane.setCenterLineChart(charts);

                    adding.getHistories().put(countries + "+" + chartPane.getTfFrom().getText() + chartPane.getTfTo().getText() + CountryNamesToCodes.singleConvert(chartPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter), toBeCharted);
                }
            }
            }
        }
    }




