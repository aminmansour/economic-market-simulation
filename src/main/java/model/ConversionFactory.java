package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Sarosi on 27/11/2016.
 * Provides methods to allow for conversion between types i.e Country name to code
 */
public class ConversionFactory {
    /**
     *Converts a list of countries to their associated code.
     * @param names the full string name of the countries we are requesting the codes for
     * @param reader a countryreader which has processed a CSV
     * @return an ArrayList of country codes
     */
    public static ArrayList<String> convert(ArrayList<String> names, CountryCodeDictionary reader) {
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
           String code = reader.getPairs().get(names.get(i));
           codes.add(code);

        }
        return codes;
    }

    /**
     * Converts a country name to the associated code value.
     * @param name the name of the code of indicator/country you wish to convert to code
     * @param reader the file you wish to use to convert the name into code
     * @return
     */
    public static String singleConvert(String name, CountryCodeDictionary reader) {
        return reader.getPairs().get(name);
    }

    /**
     * Convert from country code to its associated name
     * @param code the code of the indicator/country you wish to convert back to its meaningful name
     * @param reader the file you wish to use to convert the name into name
     * @return
     */
    public String backwardsConvert(String code, CountryCodeDictionary reader) {

        HashMap<String, String> map = reader.getPairs();

        String answer = null;

        for(Map.Entry<String, String> chart : map.entrySet()) {

            if(Objects.equals(chart.getValue(), code)) {

                answer = chart.getKey();
            }

        }

        return answer;

    }
}
