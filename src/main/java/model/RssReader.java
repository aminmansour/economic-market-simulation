package model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Amans on 27/11/2016.
 */
public class RssReader {
    //"http://rss.cnn.com/rss/money_news_economy.rss"

    public static ArrayList<String> retrieveHeadlines(){
        try {
            URL rss = new URL("http://rss.cnn.com/rss/money_news_economy.rss");
            BufferedReader reader = new BufferedReader(new InputStreamReader(rss.openStream()));
            ArrayList<String> headlines = new ArrayList<String>();
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("<title>")) {
                    int first = currentLine.indexOf("<title");
                    String temp = currentLine.substring(first);
                    temp = temp.replace("<title>", "");
                    int last = currentLine.indexOf("</title>");
                    temp = temp.substring(0, last - 7);
                    if (!temp.equals("Economic news - CNNMoney.com")) {
                        headlines.add(temp);
                    }

                }
            }
            reader.close();
            return headlines;
        }catch (Exception e){
            return null;
        }
    }

}