package model;

/**
 * Created by Sarosi on 26/11/2016.
 */
public class DataPiece {

    private String country;
    private String year;
    private String value;
    private String indicator;

    public DataPiece(String inValue, String inYear, String inCountry, String inIndicator){
        country = inCountry;
        year = inYear;
        value = inValue;
        indicator = inIndicator;
    }

    public String getCountry(){
        return country;
    }
    public String getYear(){
        return year;
    }
    public String getValue(){
        return value;
    }
    public String getIndicator(){
        return indicator;
    }
    public String toString(){
        return "this is a datapiece with " + value + " " + year + " " + country + " " + indicator;
    }

}
