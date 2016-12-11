package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by denissaidov on 09/12/2016.
 *  Retrieves data from the internet on stock prices of the 4 major technology companies. Google,Microsott,Apple and Yahoo.
 */
public class StockIndicators {

    private String aaplBid;
    private String aaplChangeinPercent;
    private String msftBid;
    private String msftChangeinPercent;
    private String googlBid;
    private String googlChangeinPercent;
    private String yhooBid;
    private String yhooChangeinPercent;

    /**
     * Loads the stock prices of each company from Yahoo Apis and converts JSON data to strings.
     *
     * @throws Exception an Exception is thrown if a access to the internet is made when the user is offline
     */
    public StockIndicators() throws Exception {
        testJSONParsing test = new testJSONParsing();

        try {
            JSONArray jsonObject = test.httpGET("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20%28%22AAPL,MSFT,GOOGL,YHOO%22%29&env=store://datatables.org/alltableswithkeys&format=json", "StockIndicators");
            System.out.println(jsonObject);
            JSONObject jsb = (JSONObject) jsonObject.getJSONObject(0);
            JSONObject jsbQuery = (JSONObject) jsb.getJSONObject("query");
            JSONObject jsbResults = (JSONObject) jsbQuery.getJSONObject("results");
            JSONArray jsaQuote = (JSONArray) jsbResults.getJSONArray("quote");

            JSONObject jsbAAPL = (JSONObject) jsaQuote.get(0);
            JSONObject jsbMSFT = (JSONObject) jsaQuote.get(1);
            JSONObject jsbGOOGL = (JSONObject) jsaQuote.get(2);
            JSONObject jsbYHOO = (JSONObject) jsaQuote.get(3);

            aaplBid = jsbAAPL.getString("Bid");
            aaplChangeinPercent = jsbAAPL.getString("ChangeinPercent");
            msftBid = jsbMSFT.getString("Bid");
            msftChangeinPercent = jsbMSFT.getString("ChangeinPercent");
            googlBid = jsbGOOGL.getString("Bid");
            googlChangeinPercent = jsbGOOGL.getString("ChangeinPercent");
            yhooBid = jsbYHOO.getString("Bid");
            yhooChangeinPercent = jsbYHOO.getString("ChangeinPercent");
        } catch (Exception E) {
            aaplBid = "N/A";
            aaplChangeinPercent = "N/A";
            msftBid = "N/A";
            msftChangeinPercent = "N/A";
            googlBid = "N/A";
            googlChangeinPercent = "N/A";
            yhooBid = "N/A";
            yhooChangeinPercent = "N/A";
        }
    }

    /**
     * Return Apple's latest bid.
     */
    public String getAAPLBid() {
        return aaplBid;
    }

    /**
     * Return Apple's latest change in percentage.
     */
    public String getAAPLPercent() {
        return aaplChangeinPercent;
    }

    /**
     * Return Microsoft's latest bid.
     */
    public String getMSFTBid() {
        return msftBid;
    }

    /**
     * Return Microsoft's latest change in percentage.
     */
    public String getMSFTPercent() {
        return msftChangeinPercent;
    }

    /**
     * @return Google's latest bid.
     */
    public String getGOOGLBid() {
        return googlBid;
    }

    /**
     *  @return Google's latest change in percentage.
     */
    public String getGOOGLPercent() {
        return googlChangeinPercent;
    }

    /**
     * @return Yahoo's latest bid.
     */
    public String getYHOOBid() {
        return yhooBid;
    }

    /**
     * @return Yahoo's latest change in percentage.
     */
    public String getYHOOPercent() {
        return yhooChangeinPercent;
    }

}
