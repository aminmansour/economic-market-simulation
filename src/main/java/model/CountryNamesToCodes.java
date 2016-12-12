package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    /**
     *
     * @param name the name of the code of indicator/country you wish to convert to code
     * @param reader the file you wish to use to convert the name into code
     * @return
     */
    public static String singleConvert(String name, CountryReader reader) {

        return reader.getPairs().get(name);

    }

    /**
     *
     * @param code the code of the indicator/country you wish to convert back to its meaningful name
     * @param reader the file you wish to use to convert the name into name
     * @return
     */
    public String backwardsConvert(String code, CountryReader reader) {

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
