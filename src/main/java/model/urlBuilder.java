package model;

/**
 * Created by Sarosi on 25/11/2016.
 */
public class urlBuilder {

    public urlBuilder(){}


    public String URL(String countyCode, String indicartorType,String from, String to){
        String a = "http://api.worldbank.org/countries/"  + countyCode + "/indicators/" + indicartorType + "/?date=" + from + ":" + to + "&format=JSON";
        return a;
    }


}
