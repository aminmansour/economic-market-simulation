package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Amans on 26/11/2016.
 */
public class InterfaceScene extends Scene {

    private StackPane spGlobal;
    private StackPane spDisplay;
    private GridPane gpLocalIndicators;
    private ArrayList<Button> bNavButtons;
    private Text tTopBanner;

    public InterfaceScene(Stage sCurrent){
        super(new StackPane(),sCurrent.getWidth(),sCurrent.getHeight());
        spGlobal = (StackPane)getRoot();
        spGlobal.setStyle("-fx-background-color: white");
        spGlobal.getStylesheets().add("css/interface-style.css");
        spGlobal.getStyleClass().add("banner");
        setUpNaviagation();
        setIndicatorBox(1,0,"=2.3","0.0%");
        setIndicatorBox(2,-1,"-2.3","-0.5%");
        loadTopIndicators(new String[]{"Hello","Bye"},0);
    }

    private void setUpNaviagation(){
        spGlobal.setAlignment(Pos.TOP_LEFT);

        VBox vbStack = new VBox();
        createButtons(new String[]{"Home","Display type","Global Forecast","News Stand","Back"},vbStack);
        vbStack.setAlignment(Pos.TOP_CENTER);

        createTopBar();

        BorderPane bpSideNav = createSideNav(vbStack);
        gpLocalIndicators = new GridPane();
        bpSideNav.setBottom(gpLocalIndicators);
        createIndicatorBoxes(new String[]{"UK","USA","EU","ASIA"},gpLocalIndicators);
        gpLocalIndicators.setAlignment(Pos.CENTER);
        BorderPane.setMargin(gpLocalIndicators,new Insets(0,10,10,10));
        BorderPane.setAlignment(gpLocalIndicators,Pos.CENTER);
    }

    private void createTopBar() {
        FlowPane flChangingStocks = new FlowPane();
        spGlobal.getChildren().add(flChangingStocks);
        flChangingStocks.setAlignment(Pos.CENTER);
        tTopBanner = new Text("hello");
        tTopBanner.setFill(Color.WHITE);
        flChangingStocks.setMaxSize(3000,25);
        flChangingStocks.setStyle("-fx-translate-x: 100");
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


    private void createIndicatorBoxes(String[] nameOfCountry,GridPane gpBoxes){
        int counter = 0;
        for(int i = 0 ; i< 2;i++){
            for(int j = 0 ; j<2; j++) {
                VBox vbIndicatorBox = new VBox();
                vbIndicatorBox.setId("box"+counter);
                vbIndicatorBox.getStyleClass().add("indicator-box");
                Text tName = new Text(nameOfCountry[counter]);
                counter++;
                tName.getStyleClass().add("indicator-title");
                Text tGDP = new Text("+0.43");
                tGDP.getStyleClass().add("indicator-gdp");
                Text tPercent = new Text("0.43%");
                tPercent.getStyleClass().add("indicator-increase");
                vbIndicatorBox.getChildren().addAll(tName, tGDP, tPercent);
                gpBoxes.add(vbIndicatorBox,i,j);
            }
        }
    }

    private void createButtons(String[] listOfButtons,VBox vbStack){
        bNavButtons = new ArrayList<Button>(listOfButtons.length);
        for(int i = 0; i<listOfButtons.length;i++){
            Button button = new Button(listOfButtons[i]);
            button.getStyleClass().add("nav-buttons");
            vbStack.getChildren().add(button);
            bNavButtons.add(button);
            VBox.setVgrow(button,Priority.ALWAYS);
            button.setMaxSize(Double.MAX_VALUE,100);
        }
    }

    public void setIndicatorBox(int boxLocation,int hasIncreased,String value,String percentage){
        VBox vbIndicatorBox = (VBox)gpLocalIndicators.getChildren().get(boxLocation);
        Text tGDP = (Text)vbIndicatorBox.getChildren().get(1);
        Text tPercentageIncrease = (Text)vbIndicatorBox.getChildren().get(2);
        tGDP.setText(value);
        tPercentageIncrease.setText(percentage);
        switch (hasIncreased){
            case 1: tGDP.setStyle("-fx-fill: #9AF261"); tPercentageIncrease.setStyle("-fx-fill: #9AF261");
            break;
            case 0: tGDP.setStyle("-fx-fill: white"); tPercentageIncrease.setStyle("-fx-fill: white");
            break;
            case -1: tGDP.setStyle("-fx-fill: #dc1c29"); tPercentageIncrease.setStyle("-fx-fill: #ff0538");
            break;
        }


    }

    public void loadTopIndicators(final String[] listOfValues,final int currentIndex){

            Timeline ts = new Timeline(new KeyFrame(new Duration(3000), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    tTopBanner.setText(listOfValues[currentIndex]);
                }
            }));
        ts.play();
            ts.setOnFinished(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    loadTopIndicators(listOfValues,(currentIndex+1)%listOfValues.length);
                }
            });
            ts.play();

        }



}
