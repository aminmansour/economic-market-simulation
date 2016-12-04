package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.ChartPane;

/**
 * Created by hanitawil on 01/12/2016.
 */
public class ContriesController implements EventHandler<MouseEvent>{

    private ChartPane chartPane;

    public ContriesController(ChartPane chartPane) {
        this.chartPane = chartPane;
    }

    @Override
    public void handle(MouseEvent event) {



    }
}
