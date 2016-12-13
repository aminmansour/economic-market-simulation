package model;

import controller.QueryController;
import org.junit.Test;
import view.ChartPane;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DataRetrievalTest {

    private ChartPane chartPane;
    private DataRetriever arrayBuilder = new DataRetriever();
    private QueryController queryController = new QueryController(chartPane);

    /**
     *
     * This test is performed by comparing two ArrayList of ArrayList of DataPiece.
     * ExpectedOutput is gotten from the internet.
     * ActualOutput is produced calling the buildArray method.
     *
     * @throws Exception
     */
    @Test
    public void buildArray() throws Exception {

        ArrayList<String> countries = new ArrayList<>();
        ArrayList<ArrayList<DataPiece>> expectedOutput = new ArrayList<>();
        ArrayList<DataPiece> in = new ArrayList<>();
        DataPiece dp = new DataPiece("1565408509949.85", "1999","United Kingdom","GDP (current US$)");
        in.add(dp);
        expectedOutput.add(in);
        countries.add("gb");

        ArrayList<ArrayList<DataPiece>> actualOutput = arrayBuilder.buildArray(countries, "1999" , "1999" , "NY.GDP.MKTP.CD");
        assertEquals(expectedOutput+ "1", actualOutput+ "1");



    }

}