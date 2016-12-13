package model;

import javafx.util.Pair;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class IndicatorRetrievalTest {


    /**
     *
     * This method is produced to test the returnBox method.
     * Two LinkedHashMaps are produced.
     * ExpectedOutput is manually filled with the corrected output that we should get
     * ActualOutput is gotten by calling the method
     *
     * @throws Exception
     */
    @Test
    public void returnBox() throws Exception {

        IndicatorRetrieval indicatorRetrieval = new IndicatorRetrieval();
        LinkedHashMap<Pair<String, String>, Integer> expectedOutput = new LinkedHashMap();
        expectedOutput.put(new Pair<String , String> ("73.50" + " Bil", "-5.74" + "%") , -1);
        expectedOutput.put(new Pair<String , String> ("1.57" + " Bil", "-2.76" + "%") , -1);
        expectedOutput.put(new Pair<String , String> ("19.99" + " Bil", "-4.67" + "%") , -1);
        expectedOutput.put(new Pair<String , String> ("21.28" + " Bil", "-4.57" + "%") , -1);
        LinkedHashMap<Pair<String, String>, Integer> actualOutput = indicatorRetrieval.returnBox();
        System.out.println(actualOutput);
        System.out.println(expectedOutput);

        assertEquals(expectedOutput , actualOutput);


    }

}