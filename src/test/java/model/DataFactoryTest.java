package model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Amans on 27/11/2016.
 * Test collection to test all the methods in data facory
 */
public class DataFactoryTest {
    /**
     * Test for if the correct data on macroeconomics is retrieved  from CNN.
     */
    @Test
    public void retrieveHeadlinesFromCorrectSiteTest() {

        try {
            DataFactory.retrieveHeadlines();
        }catch (Exception e){
            fail("The rss failed to read correctly");
        }
    }

    @Test
    public void getWordsFromFilesTest() {
        try {
            assertNotNull(DataFactory.getWordsFromFiles());
        } catch (Exception e) {
            fail("The words could not be retrieved from file");
        }
    }



}