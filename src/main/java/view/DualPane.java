package view;

import controller.DualController;
import controller.QueryController;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ArrayBuilder;
import model.ChartBuillder;
import model.CountryReader;

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



    public DualPane() throws Exception {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

            ArrayList<String> count = new ArrayList<String>();
            count.add("br");

            LineChart<String, Number> linechart= ChartBuillder.buildLineChart(new ArrayBuilder().buildArray(count, "1999", "2000", "NY.GNP.MKTP.CD"));
        linechart.setAnimated(true);



        getStylesheets().add("css/chartPane-style.css");

        VBox vbox = new VBox();

        HBox hbox = new HBox();

        setPadding(new Insets(20,0,0,300));
        setCenter(linechart);

        addCountry = new Button("Add Country");
        addCountry.setId("add");

        go = new Button("Query");
        go.setId("go");
        go.setPrefSize(50,25);
        grid = new GridPane();
        ScrollPane SCP = new ScrollPane(grid);
        SCP.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(35,5,0,0));
        grid.getStyleClass().add("options-menu");
        SCP.setMaxWidth(450);
        SCP.setStyle("-fx-background-color: white; -fx-focus-color: transparent;   -fx-background: #FFFFFF; -fx-border-color: #FFFFFF;");
        setRight(SCP);
        BorderPane.setMargin(SCP, new Insets(10, 0, 0, 0));
        minWidthProperty().bind(Bindings.createDoubleBinding(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return SCP.getViewportBounds().getWidth();
            }
        }, SCP.viewportBoundsProperty()));

        String csvFile = "src/main/resources/storage/IndicatorCodesCore.csv";
        ArrayList<String> cnames = new CountryReader(csvFile).getCountrynames();

        indicators = new ComboBox<String>();
        indicators.setId("dropdown");

        for(int i = 0; i < cnames.size(); ++i) {
            indicators.getItems().add(i, cnames.get(i));
        }

        indicators.getSelectionModel().selectFirst();
        indicators.setMaxWidth(250);

        Label lIndicators = new Label("Indicator:");
        grid.add(lIndicators, 0,0);

        grid.add(indicators, 0, 1);

        Label from = new Label("From:");

        tfFrom = new TextField("2004");

        Label to = new Label("To:");

        tfTo = new TextField("2006");

        grid.add(from, 0, 2);
        grid.add(tfFrom, 0, 3);
        grid.add(to, 0, 4);
        grid.add(tfTo, 0, 5);

        GridPane belowCountries = new GridPane();
        belowCountries.setAlignment(Pos.CENTER_RIGHT);
        belowCountries.add(addCountry, 0, 0);

        HBox hbGo = new HBox();
        hbGo.setAlignment(Pos.CENTER_RIGHT);
        hbGo.setPadding(new Insets(5,0,0,0));
        hbGo.getChildren().add(go);

        belowCountries.add(hbGo, 0, 1);

        Label lCountries = new Label("Countries:");
        grid.add(lCountries, 0, 6);

        grid.add(belowCountries, 0, 9);

        CountryNode cn = new CountryNode("Select a country");
        cn.setPadding(new Insets(2.5,0,2.5,0));

        CountryNodeArray.add(cn);

        countriesPane = new GridPane();

        countriesPane.add(cn, 0, 0);
        grid.add(countriesPane, 0, 7);

        addCountry.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                CountryNode newNode = null;
                newNode = new CountryNode("Select a country");


                Button minus = new Button("-");
                minus.setId(Integer.toString((CountryNodeArray.size() - 1)));
                minus.getStyleClass().add("minus");

                minus.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {

                        System.out.println(minus.getId());

                        int ROWofNode = countriesPane.getRowIndex(minus);
                        int COLofNode = countriesPane.getColumnIndex(minus) - 1;

                        Node result = null;
                        ObservableList<Node> childrens = countriesPane.getChildren();
                        System.out.println(childrens.size());

                        for (Node node : childrens) {
                            if (countriesPane.getRowIndex(node) == ROWofNode && countriesPane.getColumnIndex(node) == COLofNode) {
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

                if (newNode != null) {
                    newNode.setPadding(new Insets(2.5, 0, 2.5, 0));
                }
                countriesPane.add(newNode, 0, (CountryNodeArray.size() - 1));
                countriesPane.add(minus, 1, (CountryNodeArray.size() - 1));
            }
        });

        go.setOnMousePressed(new DualController(this));

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