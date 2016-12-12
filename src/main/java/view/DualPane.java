package view;

import controller.DualController;
import controller.QueryController;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.ArrayBuilder;
import model.ChartBuillder;
import model.CountryReader;
import model.History;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by denissaidov on 28/11/2016.
 * Create two Chart panes view on top of each other and displays it to the user
 */

public class DualPane extends BorderPane {


    /**
     * Create two charts panes with their associated line charts and stacks them on top each other in the view
     * @param history encapsulates a hash map containing previous queries
     * @throws Exception The exception is thrown when either data doesnt exist or there is no internet
     */
    public DualPane(History history) throws Exception {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
        setPadding(new Insets(20,0,0,300));
        VBox gpStack = new VBox();
        LineChart<String, Number> lcChart1 = new LineChart<String, Number>(new CategoryAxis(), (new NumberAxis()));
        LineChart<String, Number> lcChart2 = new LineChart<String, Number>(new CategoryAxis(), (new NumberAxis()));
        lcChart2.setAnimated(true);
        lcChart1.setAnimated(true);
        ChartPane cpChart1 = new ChartPane(lcChart2, history);
        ChartPane cpChart2 = new ChartPane(lcChart1, history);
        gpStack.getChildren().add(cpChart1);
        gpStack.setAlignment(Pos.CENTER_LEFT);
        GridPane.setHalignment(cpChart1, HPos.CENTER);
        GridPane.setHalignment(cpChart2, HPos.CENTER);
        gpStack.getChildren().add(cpChart2);
        VBox.setVgrow(cpChart1, Priority.ALWAYS);
        cpChart1.setMarginWithIn();
        cpChart2.setMarginWithIn();
        VBox.setVgrow(cpChart2, Priority.ALWAYS);
        cpChart1.maxWidth(Double.MAX_VALUE);
        cpChart2.maxWidth(Double.MAX_VALUE);
        setCenter(gpStack);
        setPickOnBounds(false);
        gpStack.setPickOnBounds(false);


    }


}