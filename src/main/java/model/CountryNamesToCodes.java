package model;

import java.util.ArrayList;

/**
 * Created by Sarosi on 27/11/2016.
 */
public class CountryNamesToCodes {
    /**
     *
     * @param names the full string name of the countries we are requesting the codes for
     * @param reader a countryreader which has processed a CSV
     * @return an ArrayList of country codes
     */
    public static ArrayList<String> convert(ArrayList<String> names, CountryReader reader) {
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
           String code = reader.getPairs().get(names.get(i));
           codes.add(code);

        }
        return codes;
    }
    
    public static String singleConvert(String name, CountryReader reader) {

        return reader.getPairs().get(name);

    }

    
}
