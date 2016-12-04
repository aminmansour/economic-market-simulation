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

    /**
     *
     * @return the country the data represents
     */
    public String getCountry(){
        return country;
    }
    /**
     *
     * @return the year the data represents
     */
    public String getYear(){
        return year;
    }
    /**
     *
     * @return the value the data represents
     */
    public String getValue(){
        return value;
    }
    /**
     *
     * @return the indicator the data represents
     */
    public String getIndicator(){
        return indicator;
    }
    /**
     *
     * @return a string describing the data
     */
    public String toString(){
        return "this is a datapiece with " + value + " " + year + " " + country + " " + indicator;
    }

}
