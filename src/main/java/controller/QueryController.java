package controller;

import model.ArrayBuilder;
import model.DataPiece;
import view.ChartPane;

import java.util.ArrayList;

/**
 * Created by Sarosi on 30/11/2016.
 */
public class QueryController {

    public QueryController(){

    }
//actionlistener of go calls this method
    public ArrayList<ArrayList<DataPiece>> makeLineChart(ChartPane chartPane) throws Exception {


        ArrayBuilder AB = new ArrayBuilder();
        ArrayList<String> countries = new ArrayList<String>();

        return  AB.buildArray(chartPane.countrynames(),chartPane.getTfFrom().getText(), chartPane.getTfTo().getText(),chartPane.getIndicators().getSelectionModel().toString());

    }





}
