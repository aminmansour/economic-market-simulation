package model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import static org.junit.Assert.*;

/**
 * Created by Amans on 27/11/2016.
 */
public class RssReaderTest {
    @Test
    public void retrieveHeadlinesFromCorrectSiteTest() {
        try {
            RssReader.retrieveHeadlines("http://rss.cnn.com/rss/money_news_economy.rss");
        }catch (Exception e){
           fail("The rss failed to read correctly");
        }
    }


}