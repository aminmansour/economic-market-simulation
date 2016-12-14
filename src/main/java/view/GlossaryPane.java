package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;
import model.DataFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;


/**
 * Created by Amans on 28/11/2016.
 * Sets up the glossary view all the words with their associated definitions
 */
public class GlossaryPane extends BorderPane {
    /**
     * Sets up the title and the view of  the pane and displays all the facts in a vertical stack
     */
    public GlossaryPane() {
        setPadding(new Insets(30, 0, 0, 306));
        setTitleOfPage();
        GridPane gpFactStack = setUpView();
        displayFacts(gpFactStack);
    }

    //sets the general view of the pane
    private GridPane setUpView() {
        GridPane gpFactStack = new GridPane();

        final ScrollPane spScroll = new ScrollPane(gpFactStack);
        getStylesheets().add("css/interface-style.css");
        spScroll.setStyle("-fx-background-color: transparent; -fx-control-inner-background: transparent;");
        setCenter(spScroll);
        gpFactStack.setAlignment(Pos.CENTER);
        gpFactStack.setStyle("-fx-background-color: white");
        spScroll.setStyle("-fx-background-color: white");
        gpFactStack.minWidthProperty().bind(Bindings.createDoubleBinding(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return spScroll.getViewportBounds().getWidth();
            }
        }, spScroll.viewportBoundsProperty()));
        BorderPane.setAlignment(spScroll, Pos.CENTER);
        return gpFactStack;
    }

    private void setTitleOfPage() {
        Text tPageTitle = new Text("Word Bank");
        tPageTitle.setStyle(" -fx-font-size: 40px; -fx-font-family: arial; -fx-font-weight: 300; -fx-text-fill: #3c3c3c; ");
        setTop(tPageTitle);
        BorderPane.setAlignment(tPageTitle, Pos.CENTER);
    }

    private void displayFacts(GridPane gp) {
        Pair<ArrayList<String>, ArrayList<String>> wordBank = DataFactory.getWordsFromFiles();
        for (int i = 0; i < wordBank.getKey().size(); i++) {
            Text tWord = new Text(wordBank.getKey().get(i));
            tWord.getStyleClass().add("word-text");
            gp.add(tWord, 0, i);
            GridPane.setMargin(tWord, new Insets(30, 35, 40, 22));

            TextArea taDefinition = new TextArea(wordBank.getValue().get(i));
            taDefinition.setWrapText(true);
            taDefinition.setMaxHeight(90);
            taDefinition.setDisable(true);
            gp.add(taDefinition, 1, i);
            GridPane.setMargin(taDefinition, new Insets(30, 40, 40, 0));

            GridPane.setHalignment(gp, HPos.CENTER);
            gp.setStyle("-fx-background-color: white");
        }
    }
}
