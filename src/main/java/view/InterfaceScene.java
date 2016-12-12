package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.util.Pair;
import model.DataFactory;
import model.History;
import model.IndicatorRetrieval;
import model.StockIndicators;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Amans on 26/11/2016.
 */
public class InterfaceScene extends Scene {

    private StackPane spGlobal;
    private StackPane spDisplay;
    private GridPane gpLocalIndicators;
    private ArrayList<Button> bNavButtons;
    private Text tTopBanner;
    private Stack<BorderPane> pageLoad;
    private History history;
    private NoteBoardPane cachedNoteBoard;
    private GlossaryPane cachedGlossary;
    public BorderPane bpSideNav;


    /**
     * Sets up side and top navigation with the defualt lander page set as the view.
     *
     * @param sCurrent The primary stage
     */
    public InterfaceScene(Stage sCurrent) {
        super(new StackPane(),sCurrent.getWidth(),sCurrent.getHeight());
        spGlobal = (StackPane)getRoot();
        setUpNaviagation();
        HomePane view = new HomePane();
        setView(view);
        spGlobal.setStyle("-fx-background-color: white");
        spGlobal.getStylesheets().add("css/interface-style.css");
        spGlobal.getStyleClass().add("banner");
        setUpIndicatorBoxes();


        view.setPickOnBounds(false);
        pageLoad = new Stack<BorderPane>();


    }

    //retrieves from storage and loads data to the indicators
    private void setUpIndicatorBoxes() {
        try {
            IndicatorRetrieval ir = new IndicatorRetrieval();
            int counter = 0;
            for (Map.Entry<Pair<String, String>, Integer> currentComment : ir.returnBox().entrySet()) {
                setIndicatorBox(counter, currentComment.getValue(), currentComment.getKey().getKey(), currentComment.getKey().getValue());
                counter++;
            }
        } catch (Exception E) {
            bpSideNav.setBottom(null);
        }
    }

    /**
     * Loads the backend required to run the interface
     *
     * @param sCurrent  The primary stage
     * @param linechart The linechart which we preload
     * @param history   The cache store which stores data
     */
    public void loadStore(Stage sCurrent, LineChart<String, Number> linechart, History history) {
        loadTopIndicators(DataFactory.retrieveHeadlines(), 0);

        this.history = history;
        setButtonListeners(linechart);
        cachedGlossary = new GlossaryPane();
        cachedNoteBoard = new NoteBoardPane();
        sCurrent.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                cachedNoteBoard.getController().saveToFile();
                try {
                    FileOutputStream fos = new FileOutputStream("src/main/resources/storage/hashmap.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(history.getHistories());
                    oos.close();
                    fos.close();
                    System.out.printf("Serialized HashMap data is saved in hashmap.ser");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }

    private void setButtonListeners(LineChart<String, Number> lcChart) {
        bNavButtons.get(3).setOnMousePressed(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     if (!pageLoad.isEmpty() && !pageLoad.peek().getClass().toString().equals("class view.GlossaryPane")) {
                                                         Pair<Boolean, BorderPane> checkOccurence = checkForPageReoccurence("GlossaryPane");
                                                         if (checkOccurence.getKey() == true) {
                                                             pageLoad.remove(checkOccurence.getValue());
                                                             pageLoad.push(checkOccurence.getValue());
                                                             setView(checkOccurence.getValue());
                                                         } else {

                                                             pageLoad.push(cachedGlossary);
                                                             setView(cachedGlossary);
                                                         }
                                                     } else if (pageLoad.isEmpty()) {
                                                         pageLoad.push(cachedGlossary);
                                                         setView(cachedGlossary);
                                                     }

                                                 }
                                             }
        );
        bNavButtons.get(0).setOnMousePressed(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     System.out.println("hello");
                                                     if (!pageLoad.isEmpty() && !pageLoad.peek().getClass().toString().equals("class view.NoteBoardPane")) {
                                                         Pair<Boolean, BorderPane> checkOccurence = checkForPageReoccurence("NoteBoardPane");
                                                         if (checkOccurence.getKey() == true) {
                                                             pageLoad.remove(checkOccurence.getValue());
                                                             pageLoad.push(checkOccurence.getValue());
                                                             setView(checkOccurence.getValue());
                                                         } else {
                                                             try {
                                                             } catch (Exception e) {
                                                                 e.printStackTrace();
                                                             }

                                                             pageLoad.push(cachedNoteBoard);
                                                             setView(cachedNoteBoard);
                                                         }
                                                     } else if (pageLoad.isEmpty()) {
                                                         try {
                                                         } catch (Exception e) {
                                                             e.printStackTrace();
                                                         }
                                                         pageLoad.push(cachedNoteBoard);
                                                         setView(cachedNoteBoard);
                                                     }
                                                 }
                                             }
        );

        bNavButtons.get(1).setOnMousePressed(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     if (!pageLoad.isEmpty() && !pageLoad.peek().getClass().toString().equals("class view.ChartPane")) {
                                                         Pair<Boolean, BorderPane> checkOccurence = checkForPageReoccurence("ChartPane");
                                                         if (checkOccurence.getKey() == true) {
                                                             pageLoad.remove(checkOccurence.getValue());
                                                             pageLoad.push(checkOccurence.getValue());
                                                             setView(checkOccurence.getValue());
                                                         } else {
                                                             ChartPane icIndicator = null;

                                                             try {
                                                                 icIndicator = new ChartPane(lcChart, history);
                                                                 icIndicator.setMarginAround();
                                                             } catch (Exception e) {
                                                                 e.printStackTrace();
                                                             }

                                                             pageLoad.push(icIndicator);
                                                             setView(icIndicator);
                                                         }
                                                     } else if (pageLoad.isEmpty()) {
                                                         ChartPane icIndicator = null;
                                                         try {
                                                             icIndicator = new ChartPane(lcChart, history);
                                                             icIndicator.setMarginAround();
                                                         } catch (Exception e) {
                                                             e.printStackTrace();
                                                         }
                                                         pageLoad.push(icIndicator);
                                                         setView(icIndicator);
                                                     }
                                                 }
                                             }
        );

        bNavButtons.get(2).setOnMousePressed(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     if (!pageLoad.isEmpty() && !pageLoad.peek().getClass().toString().equals("class view.DualPane")) {
                                                         Pair<Boolean, BorderPane> checkOccurence = checkForPageReoccurence("DualPane");
                                                         if (checkOccurence.getKey() == true) {
                                                             pageLoad.remove(checkOccurence.getValue());
                                                             pageLoad.push(checkOccurence.getValue());
                                                             setView(checkOccurence.getValue());
                                                         } else {
                                                             DualPane dpCrossView = null;

                                                             try {
                                                                 dpCrossView = new DualPane(history);
                                                             } catch (Exception e) {
                                                                 e.printStackTrace();
                                                             }

                                                             pageLoad.push(dpCrossView);
                                                             setView(dpCrossView);
                                                         }
                                                     } else if (pageLoad.isEmpty()) {
                                                         DualPane dpCrossView = null;
                                                         try {
                                                             dpCrossView = new DualPane(history);
                                                         } catch (Exception e) {
                                                             e.printStackTrace();
                                                         }
                                                         pageLoad.push(dpCrossView);
                                                         setView(dpCrossView);
                                                     }
                                                 }
                                             }
        );

        bNavButtons.get(4).setOnMousePressed(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     if (!pageLoad.isEmpty() && !pageLoad.peek().getClass().toString().equals("class view.HistoryPane")) {
                                                         Pair<Boolean, BorderPane> checkOccurence = checkForPageReoccurence("HistoryPane");
                                                         if (checkOccurence.getKey() == true) {
                                                             pageLoad.remove(checkOccurence.getValue());
                                                             HistoryPane hpHistory = new HistoryPane(history);
                                                             pageLoad.push(hpHistory);
                                                             setView(hpHistory);
                                                         } else {
                                                             HistoryPane dpCrossView = null;

                                                             try {
                                                                 dpCrossView = new HistoryPane(history);
                                                             } catch (Exception e) {
                                                                 e.printStackTrace();
                                                             }

                                                             pageLoad.push(dpCrossView);
                                                             setView(dpCrossView);
                                                         }
                                                     } else if (pageLoad.isEmpty()) {
                                                         HistoryPane dpCrossView = null;
                                                         try {
                                                             dpCrossView = new HistoryPane(history);
                                                         } catch (Exception e) {
                                                             e.printStackTrace();
                                                         }
                                                         pageLoad.push(dpCrossView);
                                                         setView(dpCrossView);
                                                     }
                                                 }
                                             }
        );


        bNavButtons.get(5).setOnMousePressed(new EventHandler<MouseEvent>() {
                                                 @Override
                                                 public void handle(MouseEvent event) {
                                                     if (pageLoad.size() == 1) {
                                                         pageLoad.pop();
                                                         setView(new HomePane());
                                                     } else if (pageLoad.size() != 0) {
                                                         pageLoad.pop();
                                                         setView(pageLoad.peek());
                                                     }

                                                 }
                                             }
        );

    }

    private void setUpNaviagation(){
        spGlobal.setAlignment(Pos.TOP_LEFT);

        VBox vbStack = new VBox();
        createButtons(new String[]{"Note Board", "Indicator Explorer", "Compare Zone", "Word Bank", "History", "Back"}, new String[]{"note", "stock", "compare", "glossary", "clock", "back"}, vbStack);
        vbStack.setAlignment(Pos.TOP_CENTER);

        createTopBar();

        bpSideNav = createSideNav(vbStack);
        gpLocalIndicators = new GridPane();
        bpSideNav.setBottom(gpLocalIndicators);
        createIndicatorBoxes(new String[]{"World GDP", "Sub-Africa GDP", "Europe GDP", "East Asia GDP"}, gpLocalIndicators);
        gpLocalIndicators.setAlignment(Pos.CENTER);
        BorderPane.setMargin(gpLocalIndicators,new Insets(0,10,10,10));
        BorderPane.setAlignment(gpLocalIndicators,Pos.CENTER);
    }

    private Pair<Boolean, BorderPane> checkForPageReoccurence(String typeCheck) {
        for (BorderPane bpPages : pageLoad) {
            if (bpPages.getClass().toString().equals("class view." + typeCheck)) {
                return new Pair<Boolean, BorderPane>(true, bpPages);
            }
        }
        return new Pair<Boolean, BorderPane>(false, null);
    }

    private void createTopBar() {
        FlowPane flChangingStocks = new FlowPane();
        spGlobal.getChildren().add(flChangingStocks);
        flChangingStocks.setAlignment(Pos.CENTER);
        tTopBanner = new Text("Today's headlines");
        tTopBanner.setFill(Color.WHITE);
        tTopBanner.setId("top-banner");
        flChangingStocks.setMaxHeight(25);
        flChangingStocks.setPadding(new Insets(0,0,0,300));
        flChangingStocks.getStyleClass().add("top-banner");
        flChangingStocks.getChildren().add(tTopBanner);
    }

    private BorderPane createSideNav(VBox vbStack) {
        BorderPane bpSideNav = new BorderPane();
        bpSideNav.setCenter(vbStack);
        BorderPane.setAlignment(vbStack, Pos.CENTER);
        BorderPane.setMargin(vbStack,new Insets(30,30,40,30));
        bpSideNav.setMaxWidth(300);
        bpSideNav.getStyleClass().add("side-banner");
        spGlobal.getChildren().add(bpSideNav);
        return bpSideNav;
    }


    private void createIndicatorBoxes(String[] nameOfCountry,GridPane gpBoxes) {
        int counter = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                VBox vbIndicatorBox = new VBox();
                vbIndicatorBox.setId("box" + counter);
                vbIndicatorBox.getStyleClass().add("indicator-box");
                Text tName = new Text(nameOfCountry[counter]);
                counter++;

                    tName.getStyleClass().add("indicator-title");
                    Text tGDP = new Text("GOO2");
                    tGDP.getStyleClass().add("indicator-gdp");
                    Text tPercent = new Text("GOO");
                tPercent.getStyleClass().add("indicator-increase");
                vbIndicatorBox.getChildren().addAll(tName, tGDP, tPercent);
                gpBoxes.add(vbIndicatorBox, i, j);
            }
        }
    }

    private void createButtons(String[] listOfButtons, String[] icon, VBox vbStack) {
        bNavButtons = new ArrayList<Button>(listOfButtons.length);

        for(int i = 0; i<listOfButtons.length; i++){
            Image imageOk = new Image("icons/" + icon[i] + ".png");
            ImageView graphic = new ImageView(imageOk);
            graphic.setPreserveRatio(true);
            graphic.setFitWidth(35);
            Button button = new Button(listOfButtons[i], graphic);
            button.setId("nav-button" + i);
            button.getStyleClass().add("nav-buttons");
            vbStack.getChildren().add(button);
            bNavButtons.add(button);
            VBox.setVgrow(button,Priority.ALWAYS);
            button.setMaxSize(Double.MAX_VALUE,100);
            button.setPickOnBounds(false);
        }
    }

    /**
     * Sets a individual indicator box with a value by referencing its index in the grid. Also sets the color depending of the input specified.
     * @param boxLocation Index of box in grid
     * @param hasIncreased if 1 the box's text turns green, if -1 the box's text turns red and if 0 the text turns white
     * @param value The value which the box displays
     * @param percentage The subtext value of the box
     */
    public void setIndicatorBox(int boxLocation, int hasIncreased, String value, String percentage){
        VBox vbIndicatorBox = (VBox)gpLocalIndicators.getChildren().get(boxLocation);
        Text tGDP = (Text)vbIndicatorBox.getChildren().get(1);
        Text tPercentageIncrease = (Text)vbIndicatorBox.getChildren().get(2);
        tGDP.setText(value);
        tPercentageIncrease.setText(percentage);
        switch (hasIncreased){
            case 1:
                tGDP.setStyle("-fx-fill: #90e55e");
                tPercentageIncrease.setStyle("-fx-fill: #89de5e");
                break;
            case 0: tGDP.setStyle("-fx-fill: white"); tPercentageIncrease.setStyle("-fx-fill: white");
                break;
            case -1: tGDP.setStyle("-fx-fill: #dc1c29"); tPercentageIncrease.setStyle("-fx-fill: #ff0538");
                break;
        }


    }

    /**
     *  Recursively iterates through the array of headlines in an infinite loop
     * @param headlines The array of headlines to display
     * @param currentIndex Index of current headline displayed
     */
    public void loadTopIndicators(final ArrayList<String> headlines, final int currentIndex) {
        if (headlines != null && !headlines.isEmpty()) {
            Timeline ts = new Timeline(new KeyFrame(new Duration(6000), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    tTopBanner.setText(headlines.get(currentIndex));
                }
            }));
            ts.play();
            ts.setOnFinished(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    loadTopIndicators(headlines, (currentIndex + 1) % headlines.size());
                }
            });
            ts.play();

        }
        else{
            tTopBanner.setText("Macro Economics");
        }
    }

    /**
     * Alternates the page displayed on the interface depending on input pane.
     * @param view  The pane you want to set at the center
     */
    public void setView(Pane view){
        if(spGlobal.getChildren().size() >2 ) {
            spGlobal.getChildren().remove(2);
        }
        view.setPickOnBounds(false);
        spGlobal.getChildren().add(2,view);
    }


}