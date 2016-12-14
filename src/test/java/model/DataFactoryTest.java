package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Amans on 27/11/2016.
 * Test collection to test all the methods in data facory
 */
public class DataFactoryTest {
    /**
     * Test to see if it successfully retrieved macroeconomic headlines from CNN.
     */
    @Test
    public void retrieveHeadlinesFromCorrectSiteTest() {

        try {
            DataFactory.retrieveHeadlines();
        }catch (Exception e){
            fail("The rss failed to read correctly");
        }
    }

    /**
     * Test to see if the reader accesses the file storage and pull data from it. Fails if the resulting strinng is null, meaning that the buffered reader failed to read the
     * word-bank.txt.
     */
    @Test
    public void getWordsFromFilesTest() {
        try {
            assertNotNull(DataFactory.getWordsFromFiles());
        } catch (Exception e) {
            fail("The words could not be retrieved from file");
        }
    }



}