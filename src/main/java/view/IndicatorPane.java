package view;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.BorderPane;

/**
 * Created by Amans on 30/11/2016.
 */
public class IndicatorPane extends BorderPane {
    public IndicatorPane(LineChart<String, Number> linechart) {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

        setPadding(new Insets(25, 0, 0, 300));
        setCenter(linechart);

    }
}
