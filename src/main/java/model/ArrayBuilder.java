package model;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Sarosi on 26/11/2016.
 */

public class ArrayBuilder {

    public ArrayBuilder() {

    }

    /**
     * this method generates the data required to build a line chart conatining the names of countries and the data for x and y axis
     * @param countries an array containing iso2 country codes
     * @param from starting date
     * @param to ending date
     * @param indicator the economic inicator wanted
     * @return county code, data and year sorted grouped by country and then by year
     * @throws Exception
     */
    public ArrayList<ArrayList<DataPiece>> buildArray(ArrayList<String> countries, String from, String to, String indicator) throws Exception {
        ArrayList<ArrayList<DataPiece>> galaxy = new ArrayList<ArrayList<DataPiece>>();

        CountryReader countryCodes = new CountryReader("src/main/resources/storage/CountryCodesCore.csv");
        CountryReader indicatorCodes = new CountryReader("src/main/resources/storage/IndicatorCodesCore.csv");


        for (int i = 0; i < countries.size(); i++) {

            JSONParsing test = new JSONParsing();


            String a = DataFactory.URL(countries.get(i), indicator, from, to);

            JSONArray jsonObject = null;

            try {
                jsonObject = test.httpGET(a);
            } catch (UnknownHostException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Not connected to the Internet");
                alert.setContentText("Ooops, looks like theres no internet connection." + "\n" + "Please reconnect and try again");

                alert.showAndWait();
            }
            JSONArray jaDataArray = null;

            try{
                jaDataArray = (JSONArray) jsonObject.get(1);
            } catch (JSONException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add("css/chartPane-style.css");
                dialogPane.getStyleClass().add("alert");
                alert.setTitle("Error");
                alert.setHeaderText("Can't Retrieve Data For " + new CountryNamesToCodes().backwardsConvert(countries.get(i),countryCodes));
                alert.setContentText("Ooops, looks like there's no data to be retrieved for " + new CountryNamesToCodes().backwardsConvert(countries.get(i),countryCodes) + " between the years " + from + " - " + to + " with the " + new CountryNamesToCodes().backwardsConvert(indicator,indicatorCodes) + " indicator.");

                alert.showAndWait();
            }

            ArrayList<DataPiece> outer = new ArrayList<DataPiece>();

            if(jaDataArray != null) {
                for (int j = jaDataArray.length() - 1; j >= 0; j--) {
                    ArrayList<String> inner = new ArrayList<String>();

                    JSONObject Jake = jaDataArray.getJSONObject(j);
                    try {
                        String year1 = Jake.getString("date");

                        String valueX1 = Jake.getString("value");

                        JSONObject countryArray = Jake.getJSONObject("country");
                        String countyName = countryArray.getString("value");
                        JSONObject indicatorArray = Jake.getJSONObject("indicator");
                        String indicatorName = indicatorArray.getString("value");
                        inner.add(valueX1);
                        inner.add(year1);
                        inner.add(countyName);
                        // outer.add(inner);
                        DataPiece dataPiece = new DataPiece(valueX1, year1, countyName, indicatorName);
                        outer.add(dataPiece);

                    } catch (Exception eb) {

                    }

                }
            }

            galaxy.add(outer);
        }
        return galaxy;
    }

}
