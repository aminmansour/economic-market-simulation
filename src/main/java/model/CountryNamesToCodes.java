package model;

import java.util.ArrayList;

/**
 * Created by Sarosi on 27/11/2016.
 */
public class CountryNamesToCodes {
    public CountryNamesToCodes(){}

    public ArrayList<String> convert(ArrayList<String> names, CountryReader reader){
        ArrayList<String> codes = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
           String code = reader.getPairs().get(names.get(i));
           codes.add(code);

        }
        return codes;

    }

}
