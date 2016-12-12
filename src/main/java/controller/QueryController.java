package controller;

import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import model.*;
import view.ChartPane;
import view.CountryNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hanitawil on 30/11/2016.
 */
public class QueryController implements EventHandler<MouseEvent> {

    private ChartPane chartPane;

    public QueryController(ChartPane chartPane) {
        this.chartPane = chartPane;


    }
//actionlistener of go calls this method


    /**
     * the eventhandler for gathering information from the user query and displaying the information in the requested chart format
     * @param event the button press on Chartpane's go button
     */
    @Override
    public void handle(MouseEvent event) {
        if (isValid(chartPane.getTfFrom().getText().trim()) && isValid(chartPane.getTfTo().getText().trim())) {
            if (Integer.parseInt(chartPane.getTfFrom().getText().trim()) - Integer.parseInt(chartPane.getTfTo().getText().trim()) <= 0) {
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

            } else {
                printError("The year " + chartPane.getTfTo().getText().trim() + " is before the year " + chartPane.getTfFrom().getText().trim() + " ", "Change the dates");
            }
        }


    }

    public void printError(String messageHeader, String submessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("css/chartPane-style.css");
        dialogPane.getStyleClass().add("alert");
        alert.setContentText(submessage);
        alert.setHeaderText(messageHeader);
        alert.showAndWait();
    }


    public boolean isValid(String input) {
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        if (input.matches("(191[5-9]|19[2-9]\\d|200\\d|" + year.substring(0, 3) + "[0-" + year.substring(3) + "])")) {
            return true;
        }
        printError("The year value must be a valid year and consist of numbers", "A valid year is between is 1915 and the current year");
        return false;
        }
    }




