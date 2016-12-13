package model;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by denissaidov on 25/11/2016.
 * A class to allow for json parsing of queries to DataPieces which can be used within the program
 */
public class JSONParsing {
    private final String USER_AGENT = "Mozilla/5.0";

    public JSONParsing() {

    }

    /**
     * Makes a HTTP GET request
     *
     * @param urlIn url input
     * @return Json array from API
     * @throws Exception if URL incorrect
     */
    public JSONArray httpGET(String urlIn) throws Exception {

        String url = urlIn;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        JSONArray returnArray = new JSONArray(response.toString());
        return returnArray;
    }

    // HTTP GET request
//    public JSONArray httpGET(String urlIn, String typeIn) throws Exception {
//
//        String url = urlIn;
//
//        URL obj = new URL(url);
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        int responseCode = con.getResponseCode();
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//        //print result
//        JSONArray returnArray = new JSONArray("[" + response.toString() + "]");
//        return returnArray;
//    }
}
