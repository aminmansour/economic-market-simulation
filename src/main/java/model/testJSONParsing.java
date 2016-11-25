package model;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by denissaidov on 25/11/2016.
 */
public class testJSONParsing {
    private final String USER_AGENT = "Mozilla/5.0";

    public testJSONParsing() {

    }

    // HTTP GET request
    public JSONArray httpGET(String urlIn) throws Exception {

        String url = urlIn;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        String JSONData = response.toString();

        JSONData = JSONData.substring(8, JSONData.length() - 1);

        JSONArray JSONArray = new JSONArray(JSONData);

        return JSONArray;
    }
}
