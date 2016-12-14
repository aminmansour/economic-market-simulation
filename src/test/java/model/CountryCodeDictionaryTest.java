package model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CountryCodeDictionaryTest {

    private ConversionFactory countryNamesToCodes = new ConversionFactory();
    private static ConversionFactory countryNamesToCodes1 = new ConversionFactory();


    /**
     *
     * The test is performed by creating two ArrayLists of String.
     * code is the ArrayList that should be produced if the method has correctly convert the country into code according to the CountryCodeCore.csv
     *
     * @throws Exception
     */
    @Test
    public void TestConvert() throws Exception {

        CountryCodeDictionary countryCodes = new CountryCodeDictionary("src/main/resources/storage/CountryCodesCore.csv");
        ArrayList<String> countries = new ArrayList<>();
        countries.add("United Kingdom");
        countries.add("Afghanistan");
        countries.add("Albania");
        countries.add("Algeria");
        countries.add("Andorra");
        countries.add("Angola");

        ArrayList<String> code = new ArrayList<>();
        code.add("GB");
        code.add("AF");
        code.add("AL");
        code.add("DZ");
        code.add("AD");
        code.add("AO");


        ArrayList<String> expectedOutput = code;
        ArrayList<String> actualOuput = countryNamesToCodes1.convert(countries , countryCodes);
        assertEquals(expectedOutput , actualOuput);

    }

    /**
     *
     * TestsingleConvert tests if the method have successfully convert a Countryname into a countryCode.
     * String name is countryname
     * String code is the countryCode
     *
     * @throws Exception
     */
    @Test
    public void TestsingleConvert() throws Exception {

        CountryCodeDictionary countryCodes = new CountryCodeDictionary("src/main/resources/storage/CountryCodesCore.csv");
        String name = "United Kingdom";
        String code = "GB";

        String expectedOutput = code;
        String actualOutput = countryNamesToCodes1.singleConvert(name , countryCodes);
        assertEquals(expectedOutput , actualOutput);

    }


    /**
     *
     * TestbackwardsConvert tests if the method have successfully convert a countryCode into a countryName according to CountryCodesCore.csv.
     *
     * @throws Exception
     */
    @Test
    public void TestbackwardsConvert() throws Exception {

        CountryCodeDictionary countryCodes = new CountryCodeDictionary("src/main/resources/storage/CountryCodesCore.csv");

        String expectedOutput = "Afghanistan";
        String actualOutput = countryNamesToCodes.backwardsConvert( "AF" , countryCodes) ;

        assertEquals(expectedOutput , actualOutput);

    }

}