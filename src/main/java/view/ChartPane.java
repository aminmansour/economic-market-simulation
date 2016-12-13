package view;

import controller.QueryController;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.CountryCodeDictionary;
import model.History;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by hanitawil on 28/11/2016.
 * Creates a chart pane which will contain the graph with particular data loaded on to it. The data retrieved is from the query specified at the sidebar.
 * The side bar consists of several settings i.e indicator to allow the user to specify a query.
 */
public class ChartPane extends BorderPane {


    private CountryNode cn;
    private ArrayList<CountryNode> countriesArray = new ArrayList<CountryNode>();
    private Button bQuery;
    private ComboBox<String> indicators;
    private TextField tfFrom;
    private TextField tfTo;
    private GridPane grid;
    private Button addCountry;
    private GridPane countriesPane;
    private History history;
    private ToggleGroup tgViewType;
    private RadioButton rbBar;
    private RadioButton rbLine;

    /**
     *  creates a BorderPane containg the chart from the data queried and a sidebar for making a particular query.
     * @param linechart The intial linechart.
     * @param history The object which encapsulates a hashmmap containing the data retrieved from the queries.
     */
    public ChartPane(LineChart<String, Number> linechart, History history) {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
        this.history = history;
        getStylesheets().add("css/chartPane-style.css");
        setCenter(linechart);
        addCountry = new Button("Add Country");
        addCountry.setId("add");

        bQuery = new Button("Query");
        bQuery.setId("bQuery");
        bQuery.setPrefSize(50, 25);
        grid = new GridPane();
        ScrollPane spSidePanel = new ScrollPane(grid);
        spSidePanel.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding(new Insets(0,5,0,0));
        grid.getStyleClass().add("options-menu");
        spSidePanel.setMaxWidth(450);
        spSidePanel.setPadding(new Insets(0, 10, 0, 0));
        spSidePanel.setStyle("-fx-background-color: white; -fx-focus-color: transparent;   -fx-background: #FFFFFF; -fx-border-color: #FFFFFF;");
        setRight(spSidePanel);
        BorderPane.setMargin(spSidePanel, new Insets(10, 0, 0, 0));
        minWidthProperty().bind(Bindings.createDoubleBinding(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return spSidePanel.getViewportBounds().getWidth();
            }
        }, spSidePanel.viewportBoundsProperty()));

        String csvFile = "src/main/resources/storage/IndicatorCodesCore.csv";
        indicators = new ComboBox<String>();
        try {
            ArrayList<String> cnames = new CountryCodeDictionary(csvFile).getCountrynames();
            indicators.setId("dropdown");
            for (int i = 0; i < cnames.size(); ++i) {
                indicators.getItems().add(i, cnames.get(i));
            }
        } catch (IOException e) {
            DialogPane jdError = new DialogPane();
            jdError.getStyleClass().add("alert");
            jdError.setContentText("The storage files have either been deleted or corrupted");
            jdError.setVisible(true);
        }

        indicators.getSelectionModel().selectFirst();
        indicators.setMaxWidth(250);

        Label lIndicators = new Label("Indicator:");
        grid.add(lIndicators, 0,3);

        grid.add(indicators, 0, 4);

        Label from = new Label("From:");

        tfFrom = new TextField("2004");
        tfFrom.setMinHeight(28);

        Label to = new Label("To:");

        tfTo = new TextField("2006");
        tfTo.setMinHeight(28);

        grid.add(from, 0, 5);
        grid.add(tfFrom, 0, 6);
        grid.add(to, 0, 7);
        grid.add(tfTo, 0, 8);

        tgViewType = new ToggleGroup();
        rbBar = new RadioButton("bar-chart");
        rbLine = new RadioButton("line-chart");
        rbBar.setToggleGroup(tgViewType);
        rbLine.setToggleGroup(tgViewType);
        Label chartype = new Label("Chart Type: ");
        //grid.add(chartype,0,0);
        //grid.add(rbBar,0,2);
        //grid.add(rbLine,0,1);

        grid.add(chartype,0,9);
        GridPane gpChartType = new GridPane();

        HBox hbRbBar = new HBox();
        hbRbBar.setAlignment(Pos.CENTER_LEFT);
        HBox hbRbLine = new HBox();
        hbRbLine.setAlignment(Pos.CENTER_RIGHT);
        hbRbLine.setPadding(new Insets(0,0,0,0));
        hbRbBar.setPadding(new Insets(0,40,0,0));

        hbRbBar.getChildren().add(rbBar);
        hbRbLine.getChildren().add(rbLine);

        gpChartType.add(hbRbBar,0,0);
        gpChartType.add(hbRbLine,1,0);

        grid.add(gpChartType,0,10);

        GridPane belowCountries = new GridPane();
        rbLine.setSelected(true);

        //belowCountries.add(chartype,0,0);
        belowCountries.setAlignment(Pos.CENTER_RIGHT);
        belowCountries.add(addCountry, 1, 2);

        HBox hbGo = new HBox();
        hbGo.setAlignment(Pos.CENTER_RIGHT);
        hbGo.setPadding(new Insets(5,0,0,0));
        hbGo.getChildren().add(bQuery);

        belowCountries.add(hbGo, 1, 3);



        Label lCountries = new Label("Countries:");
        grid.add(lCountries, 0, 11);

        grid.add(belowCountries, 0, 14);

        cn = new CountryNode("Select a country");
        cn.setPadding(new Insets(2.5,0,2.5,0));

        countriesArray.add(cn);

        countriesPane = new GridPane();

        countriesPane.add(cn, 0, 0);
        grid.add(countriesPane, 0, 12);

        addCountry.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (!(cn.getCountries().getValue().equals("Select a country"))) {
                    CountryNode cbCountries = null;
                    cbCountries = new CountryNode(cn.getCountries().getValue());
                    cbCountries.setDisable(true);

                    Button bMinus = new Button("-");
                    bMinus.setId(Integer.toString((countriesArray.size() - 1)));
                    bMinus.getStyleClass().add("minus");

                    //allows for removal of country
                    bMinus.setOnMousePressed(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            int rowOfNode = countriesPane.getRowIndex(bMinus);
                            int colOfNode = countriesPane.getColumnIndex(bMinus) - 1;

                            Node result = null;
                            ObservableList<Node> childrens = countriesPane.getChildren();

                            for (Node node : childrens) {
                                if (countriesPane.getRowIndex(node) == rowOfNode && countriesPane.getColumnIndex(node) == colOfNode) {
                                    result = node;
                                    break;
                                }
                            }


                            countriesArray.remove(result);

                            countriesPane.getChildren().remove(bMinus);

                            countriesPane.getChildren().remove(result);

                        }
                    });

                    countriesArray.add(cbCountries);

                    if (cbCountries != null) {
                        cbCountries.setPadding(new Insets(2.5, 0, 2.5, 0));
                    }
                    countriesPane.add(cbCountries, 0, (countriesArray.size() - 1));
                    countriesPane.add(bMinus, 1, (countriesArray.size() - 1));
                    countriesPane.setMargin(cbCountries, new Insets(0, 4, 0, 0));
                    cn.getCountries().setValue("Select a country");

                }
            }

        });

        bQuery.setOnMousePressed(new QueryController(this));
        Label lMessage = new Label("Select Graph");
        setCenter(lMessage);
    }



    /**
     * Retrieval of the history
     * @return history cache
     */
    public History getHistory() {
        return history;
    }

    /**
     * Allows access for a list of indicator dropdown
     * @return The combobox containing list of indicator strings
     */
    public ComboBox<String> getIndicators() {
        return indicators;
    }

    /**
     * Retrieves the textfield which you specify year from
     * @return The textfield which you specify year from
     */
    public TextField getTfFrom() {
        return tfFrom;
    }

    /**
     * Retrieves the textfield where you specify year to
     * @return The textfield which you specify year to
     */
    public TextField getTfTo() {
        return tfTo;
    }


    /**
     * retrieves an array list of all the country names
     * @return an array of the names of the countries selected
     */
    public ArrayList<String> countrynames() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < countriesArray.size(); i++) {
            if (!countriesArray.get(i).getCountries().getValue().equals("Select a country")) {
                names.add(countriesArray.get(i).getCountries().getSelectionModel().getSelectedItem().toString());
            }
        }
        return names;
    }

    /**
     * @return an array of countries on the pane
     */
    public GridPane getCountriesPane(){
        return  countriesPane;
    }

    /**
     * sets the center of the borderpane to a given Line chart
     * @param lineChart the given Line chart
     */
    public void setCenterLineChart(LineChart<String,Number> lineChart){
        setCenter(lineChart);

    }
    /**
     * sets the center of the borderpane to a given Bar chart
     * @param lineChart the given Bar chart
     */
    public void setCenterLineChart(BarChart<String,Number> lineChart){
        setCenter(lineChart);
    }

    /**
     * @return the group of radiobuttons to choose between chart types
     */
    public  ToggleGroup getTgViewType(){
        return tgViewType;
    }
    /**
     * @return the radiobutton for Bar chart
     */
    public RadioButton getRbBar(){
        return rbBar;
    }

    /**
     * @return the radiobutton for Line chart
     */
    public RadioButton getRbLine(){
        return rbLine;
    }

    /**
     * sets margin of the page to a fixed amount
     */
    public void setMarginAround() {
        setPadding(new Insets(20, 0, 0, 300));
    }

    /**
     * sets margin of the graph with a fixed value
     */
    public void setMarginWithIn() {
        BorderPane.setMargin((Node) getCenter(), new Insets(60));
    }


}