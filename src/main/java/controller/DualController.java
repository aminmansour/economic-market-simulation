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

    /**
     * controller for compare mode
     * @param dualPane a pane containing 2 charts
     * @throws Exception
     */
    public DualController(DualPane dualPane) throws Exception {
        this.dualPane = dualPane;


    }
//actionlistener of go calls this method

    @Override
    /**
     * handling the event of a query on the dualpane
     */
    public void handle(MouseEvent event) {
        ArrayBuilder AB = new ArrayBuilder();
        CountryReader charles = null;
        try {
            charles = new CountryReader("src/main/resources/storage/CountryCodesCore.csv");
        } catch (IOException e) {
            e.printStackTrace();


        }
    }
}
