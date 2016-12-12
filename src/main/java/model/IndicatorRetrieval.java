package model;

import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

/**
 * Retrieves all the data to display and place within the indicator boxes from the world bank.
 * Created by Amans on 11/12/2016.
 */
public class IndicatorRetrieval {
    private ArrayList<DataPiece> listOfValues;


    /**
     * Pulls data for each location region from the world bank Api. The four ragions are europe,world , east china
     * and sub-africa and caches it within a linked hash map.
     *
     * @throws Exception
     */
    public IndicatorRetrieval() throws Exception {
        JSONParsing test = new JSONParsing();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String a = DataFactory.URL("WLD;SSF;ECS;EAS", "NY.GDP.MKTP.CD", "2014", Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1));
        JSONArray jsonObject = null;
        jsonObject = test.httpGET(a);
        JSONArray jaDataArray = (JSONArray) jsonObject.get(1);
        ArrayList<DataPiece> outer = new ArrayList<DataPiece>();

        if (jaDataArray != null) {
            for (int j = jaDataArray.length() - 1; j >= 0; j--) {
                ArrayList<String> inner = new ArrayList<String>();

                JSONObject jsonDataObject = jaDataArray.getJSONObject(j);
                try {
                    String year1 = jsonDataObject.getString("date");
                    String valueX1 = jsonDataObject.getString("value");
                    JSONObject countryArray = jsonDataObject.getJSONObject("country");
                    String countyName = countryArray.getString("value");
                    JSONObject indicatorArray = jsonDataObject.getJSONObject("indicator");
                    String indicatorName = indicatorArray.getString("value");
                    inner.add(valueX1);
                    inner.add(year1);
                    inner.add(countyName);
                    DataPiece dataPiece = new DataPiece(valueX1, year1, countyName, indicatorName);
                    outer.add(dataPiece);

                } catch (Exception eb) {

                }

            }
            listOfValues = outer;
        }

    }

    /**
     * Gets the data retrieved from the world bank api if any for each indicator box and returns it in a accessable form(LinkedHashMap)
     *
     * @return A ordered map where the key is a pair of strings. The gdp for that particular location and the increase in gdp
     * from previous year. The value is an integer that represent an increase in gdp if 1, decrease if -1.
     */
    public LinkedHashMap<Pair<String, String>, Integer> returnBox() {
        LinkedHashMap<Pair<String, String>, Integer> store = new LinkedHashMap();
        int index = 0;
        int indexForRegions = 0;
        while (index < listOfValues.size()) {
            Double range = 100 - (Double.parseDouble(listOfValues.get(index).getValue()) / 100) * Double.parseDouble(listOfValues.get(index + 1).getValue());
            Double gdp = Double.parseDouble(listOfValues.get(index + 1).getValue()) / Double.parseDouble("1000000000000");
            DecimalFormat twoDForm = new DecimalFormat("#.00");
            store.put(new Pair<String, String>(twoDForm.format(gdp.doubleValue()) + " Bil", round(range.doubleValue()) + "%"), Double.compare(range.doubleValue(), 0.0));
            index += 2;
            indexForRegions++;
        }
        return store;
    }

    /**
     *
     * @param value the value you wish to round up to
     * @return a double which is rounded up to 4 significant places
     */
    private String round(Double value) {
        ;
        int valOfRound = value.toString().indexOf(".") + 3;
        if (Integer.parseInt(value.toString().substring(valOfRound, valOfRound + 1)) < 5) {
            return value.toString().substring(0, value.toString().indexOf(".") + 3);
        } else {
            return value.toString().substring(0, value.toString().indexOf(".") + 2) + (Integer.parseInt(value.toString().substring(valOfRound - 1, valOfRound)) + 1);
        }

    }


}
