package model;

import org.junit.Test;
import org.json.JSONArray;


import java.net.MalformedURLException;
import java.util.Calendar;

import static org.junit.Assert.*;

public class JSONParsingTest {

    private JSONParsing jsonParsing = new JSONParsing();

    /**
     *
     * This method tests with an incorrect input of an URL.
     * Passes when correctly spotted MalformedURLException.
     *
     * @throws Exception
     */
    @Test
    public void httpGETCorrectUrlTest() throws Exception {

        try{

            String a = "incorrect url";
            jsonParsing.httpGET(a);
            fail();

        }catch ( MalformedURLException e ){

        }

    }

    /**
     *
     * This method tests whether it accepts a correct URL input.
     *
     * @throws Exception
     */
    @Test
    public void httpGETWithIncorrectUrlTest() throws Exception {

//        String a = DataFactory.URL("gb", "NY.GDP.MKTP.CD", "1999", Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1));
//        JSONArray actualOutput = jsonParsing.httpGET(a);
//        System.out.println(actualOutput);
        try{

            String a = DataFactory.URL("gb", "NY.GDP.MKTP.CD", "1999", Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1));
            jsonParsing.httpGET(a);

        }catch ( MalformedURLException e ){
            fail();
        }

    }


}