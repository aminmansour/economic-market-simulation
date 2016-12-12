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
 */

public class DualPane extends BorderPane {

    private ArrayList<CountryNode> CountryNodeArray = new ArrayList<CountryNode>();
    private Button go;
    private ComboBox<String> indicators;
    private TextField tfFrom;
    private TextField tfTo;
    private GridPane grid;
    private Button addCountry;
    private GridPane countriesPane;

    /**
     * a view to compare two different graphs on the same pane
     * @param history an archive of previous searches
     * @throws Exception
     */

    public DualPane(History history) throws Exception {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
        setPadding(new Insets(20,0,0,300));
        VBox gpStack = new VBox();

        ArrayList<String> count = new ArrayList<String>();
//        count.add("br");
//
        LineChart<String, Number> chart2 = new LineChart<String, Number>(new CategoryAxis(), (new NumberAxis()));
        LineChart<String, Number> chart1 = new LineChart<String, Number>(new CategoryAxis(), (new NumberAxis()));
        chart1.setAnimated(true);
        chart2.setAnimated(true);
        ChartPane child = new ChartPane(chart1, history);
        ChartPane child1 = new ChartPane(chart2, history);
        gpStack.getChildren().add(child);
        gpStack.setAlignment(Pos.CENTER_LEFT);
        GridPane.setHalignment(cpChartOne, HPos.CENTER);
        GridPane.setHalignment(cpChartTwo, HPos.CENTER);
        gpStack.getChildren().add(cpChartTwo);
        VBox.setVgrow(cpChartOne, Priority.ALWAYS);
        cpChartOne.setMarginWithIn();
        cpChartTwo.setMarginWithIn();


        VBox.setVgrow(cpChartTwo, Priority.ALWAYS);
        cpChartOne.maxWidth(Double.MAX_VALUE);
        cpChartTwo.maxWidth(Double.MAX_VALUE);
        setCenter(gpStack);

        setPickOnBounds(false);
        gpStack.setPickOnBounds(false);


    }


}