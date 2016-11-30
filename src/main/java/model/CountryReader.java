package model;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Sarosi on 27/11/2016.
 */
public class CountryReader {

    //ArrayList<Pair<String,String>> pairs;
    private HashMap<String, String> pairs;
   private  ArrayList<String> countrynames;
  private  ArrayList<String> countrycodes;


    /**
     * given a csv filepath extracts the country codes and country names into separate arrays a Key Value pair map
     * @param filepath  filepath of a csv file containing country names then iso2 codes
     * @throws IOException
     */
    public CountryReader(String filepath) throws IOException {
        String csvFile = "C:\\Users\\Sarosi\\Desktop\\Core\\src\\main\\resources\\storage\\CountryCodesCore.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filepath));

        // read file line by line
        String line = null;
        Scanner scanner = null;
        HashMap<String,String> inner = new HashMap<String,String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> codes = new ArrayList<String>();




        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            //scanner.useDelimiter(",");
            while (scanner.hasNext()) {

                String data = scanner.nextLine();
                String[] split = data.split(",");
                String name = split[0];
                names.add(name);
                String code = split[1];
                codes.add(code);
                Pair<String, String>  pair = new Pair<String, String>(name,code)     ;
                inner.put(name,code);


            }

        }

        //close reader
        reader.close();

        pairs = inner;
        countrycodes = codes;
        countrynames = names;



    }

    //ArrayList<String>

    /**
     *
     * @return a map of key value pairs where key is the country name value is the iso2 code
     */
    public  HashMap<String,String> getPairs() {
        return  pairs;
    }

    /**
     *
     * @return a list of iso2 codes
     */
    public ArrayList<String> getCountrycodes() {
        return countrycodes;
    }

    /**
     *
     * @return a list of country names
     */
    public ArrayList<String> getCountrynames() {
        return countrynames;
    }
}


