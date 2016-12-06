package model;

import org.json.JSONArray;
import org.json.JSONObject;

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

        for (int i = 0; i < countries.size(); i++) {


            testJSONParsing test = new testJSONParsing();

            urlBuilder urlBuilder = new urlBuilder();

            String a = urlBuilder.URL(countries.get(i), indicator, from, to);

            JSONArray jsonObject = test.httpGET(a);

            //System.out.println(a+"hi");

            System.out.println(jsonObject);

            JSONArray jaDataArray = (JSONArray) jsonObject.get(1);


            ArrayList<DataPiece> outer = new ArrayList<DataPiece>();

            for (int j = jaDataArray.length() - 1; j >= 0; j--) {
                ArrayList<String> inner = new ArrayList<String>();
                JSONObject Jake = jaDataArray.getJSONObject(j);
                Double value1 = Double.parseDouble(Jake.getString("value"));
                String valueX1 = Jake.getString("value");
                String year1 = Jake.getString("date");
                JSONObject countryArray = Jake.getJSONObject("country");
                String countyName = countryArray.getString("value");
                JSONObject indicatorArray = Jake.getJSONObject("indicator");
                String indicatorName = indicatorArray.getString("value");
                inner.add(valueX1);
                inner.add(year1);
                inner.add(countyName);
               // outer.add(inner);
                DataPiece dataPiece = new DataPiece(valueX1,year1,countyName,indicatorName);
                System.out.println(dataPiece);
                outer.add(dataPiece);

            }
            galaxy.add(outer);
        }
        return galaxy;
    }
}
