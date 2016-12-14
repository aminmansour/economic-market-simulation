package controller;

import javafx.event.EventHandler;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.*;
import view.ChartPane;
import view.CountryNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hanitawil on 30/11/2016.
 * This is the controller which provides listeners to the addd,query and minus button. When a query is made by a user
 *,The controller alerts the
 */
public class QueryController implements EventHandler<MouseEvent> {

    private ChartPane chartPane;

    public QueryController(ChartPane chartPane) {
        this.chartPane = chartPane;


    }


    /**
     * the eventhandler for gathering information from the user query and displaying the information in the requested chart format
     * @param event the button press on Chartpane's go button
     */
    @Override
    public void handle(MouseEvent event) {
        if (isValid(chartPane.getTfFrom().getText().trim()) && isValid(chartPane.getTfTo().getText().trim())) {
            if (Integer.parseInt(chartPane.getTfFrom().getText().trim()) - Integer.parseInt(chartPane.getTfTo().getText().trim()) <= 0) {
                if (!(((CountryNode) chartPane.getGpCountries().getChildren().get(0)).getCountries().getValue().equals("Select a country") && chartPane.getGpCountries().getChildren().size() == 1)) {
                    DataRetriever query = new DataRetriever();
                    CountryCodeDictionary crListOfCountries = null;
                    try {
                        crListOfCountries = new CountryCodeDictionary("src/main/resources/storage/CountryCodesCore.csv");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //loads indicators
                    CountryCodeDictionary indicatorConverter = null;
                    try {
                        indicatorConverter = new CountryCodeDictionary("src/main/resources/storage/IndicatorCodesCore.csv");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<String> countriesFormatted = chartPane.countrynames();
                    ArrayList<String> countriesCoded = ConversionFactory.convert(countriesFormatted, crListOfCountries);

                    History adding = chartPane.getHistory();

                    String countries = "";

                    for (String code : countriesCoded) {
                        countries = countries + code;
                    }

                    ChartBuillder chartBuillder = new ChartBuillder();

                    Boolean isInsideMap = false;
                    String savedMapId = null;

                    String searchId = countries + "+" + chartPane.getTfFrom().getText() + chartPane.getTfTo().getText() + ConversionFactory.singleConvert(chartPane.getCbIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter);

                    for (String mapId : adding.getDataStore().keySet()) {

                        if (adding.compareIds(mapId, searchId)) {
                            isInsideMap = true;
                            savedMapId = mapId;
                            break;
                        }
                    }
                    //checks if conditions to populate line chat is met
                    retrieveAndPopulateLine(query, indicatorConverter, countriesCoded, adding, countries, chartBuillder, isInsideMap, savedMapId);
                    //checks if conditions to populate bar chat is met
                    retrieveAndPopulateBar(query, indicatorConverter, countriesCoded, adding, countries, chartBuillder, isInsideMap, savedMapId);
                    //only one can be true
                }

            } else {
                printError("The year " + chartPane.getTfTo().getText().trim() + " is before the year " + chartPane.getTfFrom().getText().trim() + " ", "Change the dates");
            }
        }


    }

    private void retrieveAndPopulateLine(DataRetriever query, CountryCodeDictionary indicatorConverter, ArrayList<String> countriesCoded, History adding, String countries, ChartBuillder chartBuillder, Boolean isInsideMap, String savedMapId) {
        //checks what radio button is ticked
        if (chartPane.getTgViewType().getSelectedToggle() == chartPane.getRbLine()) {
            if (isInsideMap) {
                ArrayList<ArrayList<DataPiece>> newChart = adding.getChartData(savedMapId);
                LineChart<String, Number> xy = chartBuillder.buildLineChart(newChart);
                //builds line and sets it center
                chartPane.setCenterBarChart(xy);

            } else {
                ArrayList<ArrayList<DataPiece>> toBeCharted = null;
                try {
                    toBeCharted = query.buildArray(countriesCoded, chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(), ConversionFactory.singleConvert(chartPane.getCbIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
                    LineChart<String, Number> charts = chartBuillder.buildLineChart(toBeCharted);
                    chartPane.setCenterBarChart(charts);
                } catch (Exception e) {
                    chartPane.setCenter(new Label("The result failed!"));
                }


                adding.getDataStore().put(countries + "+" + chartPane.getTfFrom().getText() + chartPane.getTfTo().getText() + ConversionFactory.singleConvert(chartPane.getCbIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter), toBeCharted);
            }
        }
    }

    private void retrieveAndPopulateBar(DataRetriever query, CountryCodeDictionary indicatorConverter, ArrayList<String> countriesCoded, History adding, String countries, ChartBuillder chartBuillder, Boolean isInsideMap, String savedMapId) {
        //checks what radio button is ticked
        if (chartPane.getTgViewType().getSelectedToggle() == chartPane.getRbBar()) {
            if (isInsideMap) {

                ArrayList<ArrayList<DataPiece>> newChart = adding.getChartData(savedMapId);
                BarChart<String, Number> xy = chartBuillder.buildBarChart(newChart);
                chartPane.setCenterBarChart(xy);
                //builds bar chart and sets it center
            } else {
                ArrayList<ArrayList<DataPiece>> toBeCharted = null;
                try {
                    toBeCharted = query.buildArray(countriesCoded, chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(), ConversionFactory.singleConvert(chartPane.getCbIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
                    LineChart<String, Number> charts = chartBuillder.buildLineChart(toBeCharted);
                    chartPane.setCenterBarChart(charts);
                } catch (Exception e) {
                    chartPane.setCenter(new Label("The result failed!"));

                }


                adding.getDataStore().put(countries + "+" + chartPane.getTfFrom().getText() + chartPane.getTfTo().getText() + ConversionFactory.singleConvert(chartPane.getCbIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter), toBeCharted);
            }
        }
    }

    //encapsulates error printing into one method with the main and secondary message of error pane specified
    private void printError(String messageHeader, String submessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("css/chartPane-style.css");
        dialogPane.getStyleClass().add("alert");
        alert.setContentText(submessage);
        alert.setHeaderText(messageHeader);
        //displays the alert pane
        alert.showAndWait();
    }

    /**
     * Checks if a particular year is a valid one.Checks between range of 1915 to current year
     *
     * @param input The year
     * @return true it's within range, false if otherwise
     */
    public boolean isValid(String input) {
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        //current year
        if (input.matches("(191[5-9]|19[2-9]\\d|200\\d|" + year.substring(0, 3) + "[0-" + year.substring(3) + "])")) {
            return true;
        }
        printError("The year value must be a valid year and consist of numbers", "A valid year is between is 1915 and the current year");
        return false;
        }
    }




