package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.*;
import view.ChartPane;
import view.DualPane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sarosi on 30/11/2016.
 */
public class DualController implements EventHandler<MouseEvent> {

    private DualPane dualPane;

    public DualController(DualPane dualPane) throws Exception {
        this.dualPane = dualPane;


    }
//actionlistener of go calls this method

    @Override
    public void handle(MouseEvent event) {
        ArrayBuilder AB = new ArrayBuilder();
        CountryReader charles = null;
        try {
            charles = new CountryReader("src/main/resources/storage/CountryCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CountryReader indicatorConverter = null;
        try {
            indicatorConverter = new CountryReader("src/main/resources/storage/IndicatorCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> countries = dualPane.countrynames();
        ArrayList<String> counties =  new CountryNamesToCodes().convert(countries,charles);

        ArrayList<ArrayList<DataPiece>> toBeCharted = null;
        try {
            toBeCharted = AB.buildArray(counties,dualPane.getTfFrom().getText(), dualPane.getTfTo().getText(),new CountryNamesToCodes().singleConvert(dualPane.getIndicators().getSelectionModel().getSelectedItem().toString(), indicatorConverter));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChartBuillder chartBuillder = new ChartBuillder();

//        chartPane.getChildren().removeAll();
        dualPane.setCenterLineChart(chartBuillder.buildLineChart(toBeCharted));

    }
}
