package view;

import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by denissaidov on 28/11/2016.
 */
public class ChartPane extends BorderPane {

    private ArrayList<CountryNode> CountryNodeArray = new ArrayList<CountryNode>();
    private Button go;
    private ComboBox<String> indicators;
    private TextField tfFrom;
    private TextField tfTo;


    public ChartPane(LineChart<String,Number> linechart) throws IOException {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

        setPadding(new Insets(0,0,0,300));
        setCenter(linechart);

        go = new Button("GO");
        Button go2 = new Button("PPAP");
        go.setPrefSize(50,25);
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(35,5,0,0));


        setRight(grid);



        indicators = new ComboBox<String>();
        indicators.getItems().addAll(
                "Hani",
                "Marc"
                );

        indicators.getSelectionModel().selectFirst();
        indicators.prefWidth(200);

        grid.add(go, 0, 0);
        grid.add(indicators, 0, 1);

        Label from = new Label("From:");

        tfFrom = new TextField("2005");

        Label to = new Label("To:");

         tfTo = new TextField("2005");

        grid.add(from, 0, 2);
        grid.add(tfFrom, 0, 3);
        grid.add(to, 0, 4);
        grid.add(tfTo, 0, 5);

        CountryNode CN = new CountryNode();
        grid.add(CN,0,6);
        CountryNodeArray.add(CN);
    }

    public void makeMeBigger(int width, int height) {
        System.out.println(this.getParent().getParent());
    }

    public ArrayList<CountryNode> getCountryNodeArray() {
        return CountryNodeArray;
    }

    public Button getGo() {
        return go;
    }

    public ComboBox<String> getIndicators() {
        return indicators;
    }

    public TextField getTfFrom() {
        return tfFrom;
    }

    public TextField getTfTo() {
        return tfTo;
    }

    public ArrayList<String> countrynames() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < CountryNodeArray.size(); i++) {
            names.add(CountryNodeArray.get(i).getCountries().getSelectionModel().toString());

        }
        return names;

    }
}