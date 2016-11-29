package model;

/**
 * Created by Sarosi on 25/11/2016.
 */
public class urlBuilder {

    public urlBuilder(){}

    /**
     * constructs a url query based on the input data
     *
     * @param countyCode the iso 2 county code
     * @param indicartorType the code for the indicator
     * @param from year
     * @param to year
     * @return a url to query the api
     */
    public String URL(String countyCode, String indicartorType,String from, String to){
        String a = "http://api.worldbank.org/countries/"  + countyCode + "/indicators/" + indicartorType + "/?date=" + from + ":" + to + "&format=JSON";
        return a;
    }


}
