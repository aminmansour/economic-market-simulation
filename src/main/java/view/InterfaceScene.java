package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.RssReader;

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

        loadTopIndicators(RssReader.retrieveHeadlines(),0);


        BorderPane bp = Util.createViewScreen();
        bp.setCenter(new Button("hek"));
        setView(new HomePane());
    }

    private void setUpNaviagation(){
        spGlobal.setAlignment(Pos.TOP_LEFT);

        VBox vbStack = new VBox();
        createButtons(new String[]{"Home","Display Type","Global Forecast","News Feed","Back"},vbStack);
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

    public void loadTopIndicators(final ArrayList<String> headlines,final int currentIndex) {
        if (headlines != null) {
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

//    private void recalculateTransition() {
//        transition.setToX(node.getBoundsInLocal().getMaxX() * -1 - 100);
//        transition.setFromX(parentPane.widthProperty().get() + 100);
//
//        double distance = parentPane.widthProperty().get() + 2 * node.getBoundsInLocal().getMaxX();
//        transition.setDuration(new Duration(distance / SPEED_FACTOR));
//    }
//
//    private void rerunAnimation() {
//        transition.stop();
//        // if needed set different text on "node"
//        recalculateTransition();
//        transition.playFromStart();
//    }
//
//
//    TranslateTransition transition = TranslateTransitionBuilder.create()
//            .duration(new Duration(10))
//            .node(node)
//            .interpolator(Interpolator.LINEAR)
//            .cycleCount(1)
//            .build();
//
//    transition.setOnFinished(new EventHandler<ActionEvent>() {
//
//        @Override
//        public void handle(ActionEvent actionEvent) {
//            rerunAnimation();
//        }
//    });

    public void setView(Pane view){
        if(spGlobal.getChildren().size() >2 ) {
            spGlobal.getChildren().remove(2);
        }
            spGlobal.getChildren().add(2,view);
        }


}
