package model;

import java.io.Serializable;

/**
 * Created by Sarosi on 26/11/2016.
 * A class to allow for instances to store data retrieved from the query within.
 */
public class DataPiece implements Serializable {

    private String country;
    private String year;
    private String value;
    private String indicator;

    /**
     * An object encapsulating  a single piece of data containing value, year, country, and indicator
     * @param inValue input value
     * @param inYear input year
     * @param inCountry input country
     * @param inIndicator input indicator
     */

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
