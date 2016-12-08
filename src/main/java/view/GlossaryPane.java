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

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;


/**
 * Created by Amans on 28/11/2016.
 */
public class GlossaryPane extends BorderPane {
    public GlossaryPane() {
        setPadding(new Insets(30, 0, 0, 306));
        Text tPageTitle = new Text("Word Bank");

        tPageTitle.setStyle(" -fx-font-size: 40px; -fx-font-family: arial; -fx-font-weight: 300; -fx-text-fill: #3c3c3c; ");
        setTop(tPageTitle);
        BorderPane.setAlignment(tPageTitle, Pos.CENTER);
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
        setUpRandom(gpFactStack);
    }

    private void setUpRandom(GridPane gp) {
        Pair<ArrayList<String>, ArrayList<String>> wordBank = DataFactory.getWordsFromFiles();
        for (int i = 0; i < wordBank.getKey().size(); i++) {
            Text t = new Text(wordBank.getKey().get(i));
            t.getStyleClass().add("word-text");
            gp.add(t, 0, i);
            GridPane.setMargin(t, new Insets(30, 15, 40, 22));
            TextArea d = new TextArea(wordBank.getValue().get(i));

            d.setWrapText(true);
            d.setMaxHeight(90);
            d.setDisable(true);
            gp.add(d, 1, i);
            GridPane.setMargin(d, new Insets(30, 40, 40, 0));
            GridPane.setHalignment(gp, HPos.CENTER);
        }
    }
}
