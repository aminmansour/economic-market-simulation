package model;
import java.io.*;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CountryReaderTest {

    /**
     *
     * This method tests the getPairs method by comparing two HashMap<String , String> variables.
     * actualOutput gets the HashMap by calling the method
     * expectedOutput manually added the information that should be gotten when calling the method
     *
     * @throws Exception
     */
    @Test
    public void getPairs() throws Exception {

        String csvFile = "src/test/resources/CountryCodesMock.csv";
        CountryCodeDictionary countryReader = new CountryCodeDictionary(csvFile);
        HashMap<String, String> actualOutput =  countryReader.getPairs();
        HashMap<String, String> expectedOutput = new HashMap<>();
        expectedOutput.put("United Kingdom","GB");

        assertEquals(expectedOutput , actualOutput);


    }

    /**
     *
     * This method tests the getCountrynames method by comparing two ArrayList<String> variables.
     * actualOutput gets the ArrayList of String by calling the method
     * expectedOutput manually added the information that should be gotten when calling the method
     *
     * @throws Exception
     */
    @Test
    public void getCountrynames() throws Exception {
        String csvFile = "src/test/resources/CountryCodesMock.csv";
        CountryCodeDictionary countryReader = new CountryCodeDictionary(csvFile);
        ArrayList<String> actualOutput =  countryReader.getCountrynames();
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("United Kingdom");

        assertEquals(expectedOutput , actualOutput);


    }

}