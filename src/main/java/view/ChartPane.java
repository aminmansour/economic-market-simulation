package view;

import controller.QueryController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private GridPane grid;
    private Button addCountry;
    private GridPane countriesPane;



    public ChartPane(LineChart<String,Number> linechart) throws Exception {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

        setPadding(new Insets(0,0,0,300));
        setCenter(linechart);

        addCountry = new Button("Add Country");

        go = new Button("GO");
        Button go2 = new Button("PPAP");
        go.setPrefSize(50,25);
        grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(35,5,0,0));

        setRight(grid);

        indicators = new ComboBox<String>();
        indicators.getItems().addAll(
               "NY.GNP.MKTP.CD",
                "Hani",
                "Marc"
                );

        indicators.getSelectionModel().selectFirst();
        indicators.prefWidth(200);

        grid.add(go, 0, 0);
        grid.add(indicators, 0, 1);

        Label from = new Label("From:");

        tfFrom = new TextField("2004");

        Label to = new Label("To:");

        tfTo = new TextField("2006");

        grid.add(from, 0, 2);
        grid.add(tfFrom, 0, 3);
        grid.add(to, 0, 4);
        grid.add(tfTo, 0, 5);

        GridPane test = new GridPane();
        test.add(addCountry, 0, 0);

        grid.add(test, 0, 8);

        CountryNode cn = new CountryNode();
        //Button minus = new Button("-");
        CountryNodeArray.add(cn);

        countriesPane = new GridPane();
        countriesPane.add(cn, 0, 0);
        //countriesPane.add(minus, 1, 0);
        grid.add(countriesPane, 0, 7);


        CountryNode cn2 = new CountryNode();
        countriesPane.add(cn2,0,1);

        countriesPane.getChildren().remove(cn2);



        addCountry.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                CountryNode newNode = null;
                try {
                    newNode = new CountryNode();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Button minus = new Button("-");
                minus.setId(Integer.toString((CountryNodeArray.size()-1)));

                CountryNode finalNewNode = newNode;
                minus.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        System.out.println(minus.getId());

                        int ROWofNode = countriesPane.getRowIndex(minus);
                        int COLofNode = countriesPane.getColumnIndex(minus)-1;

                        Node result = null;
                        ObservableList<Node> childrens = countriesPane.getChildren();

                        for (Node node : childrens) {
                            if(countriesPane.getRowIndex(node) == ROWofNode && countriesPane.getColumnIndex(node) == COLofNode) {
                                result = node;
                                break;
                            }
                        }
                        System.out.println(result);

                        CountryNodeArray.remove(result);

                        countriesPane.getChildren().remove(minus);

                        countriesPane.getChildren().remove(result);

                    }
                });

                CountryNodeArray.add(newNode);

                countriesPane.add(newNode, 0, (CountryNodeArray.size()-1));
                countriesPane.add(minus, 1, (CountryNodeArray.size()-1));

            }
        });

        go.setOnMousePressed(new QueryController(this));

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

    public GridPane getGrid() {
        return grid;
    }

    public ArrayList<String> countrynames() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < CountryNodeArray.size(); i++) {
            names.add(CountryNodeArray.get(i).getCountries().getSelectionModel().getSelectedItem().toString());

        }
        return names;

    }

    public GridPane getCountriesPane(){
        return  countriesPane;
    }

    public void setCenterLineChart(LineChart<String,Number> lineChart){
        setCenter(lineChart);
    }


}