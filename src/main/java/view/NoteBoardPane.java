package view;

import com.sun.prism.paint.Color;
import controller.NoteBoardController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Created by Amans on 06/12/2016.
 * defines the view when  the note board button is clicked
 */
public class NoteBoardPane extends BorderPane {
    private ComboBox<String> cbColor;
    private NoteBoardController nbcController;
    private VBox vbNodeStack;

    /**
     * The constructor sets up the note board layout and adds action listeners to the buttons. It also loads the comments from file to the vbox stack.
     */
    public NoteBoardPane() {
        setPadding(new Insets(30, 0, 0, 306));
        getStylesheets().add("css/notePane-style.css");
        GridPane gpComments = new GridPane();
        vbNodeStack = new VBox();
        Label lNoteBoard = new Label("Note Board");
        lNoteBoard.setId("title");
        TextArea taCommentToAdd = new TextArea();
        taCommentToAdd.setMinHeight(190);
//        taCommentToAdd.setMinHeight(400);
        Button bPost = new Button("Post Note");
        FlowPane flButtonContainer = new FlowPane(bPost);
        flButtonContainer.setAlignment(Pos.CENTER_RIGHT);
//        bPost.setMinSize(80,20);

        vbNodeStack.setMargin(flButtonContainer, new Insets(0, 0, 20, 0));
        flButtonContainer.setMargin(bPost, new Insets(10, 30, 0, 0));

        cbColor = new ComboBox<String>(FXCollections.observableArrayList("Default", "Red", "Blue", "Green"));
        cbColor.setId("color-combo");
        cbColor.getSelectionModel().selectFirst();
        cbColor.setMaxSize(100, 30);
        FlowPane flColorContainer = new FlowPane(cbColor);
        flColorContainer.setAlignment(Pos.CENTER_RIGHT);

        vbNodeStack.setMargin(flColorContainer, new Insets(0, 0, 10, 0));
        flColorContainer.setMargin(cbColor, new Insets(0, 30, 0, 0));
        vbNodeStack.setMargin(lNoteBoard, new Insets(20, 0, 0, 30));
        vbNodeStack.setMargin(taCommentToAdd, new Insets(0, 30, 0, 30));
        VBox vbCommentStack = new VBox();
        ScrollPane spScroll = new ScrollPane(vbCommentStack);
        spScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        vbCommentStack.minWidthProperty().bind(Bindings.createDoubleBinding(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return spScroll.getViewportBounds().getWidth();
            }
        }, spScroll.viewportBoundsProperty()));
        spScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        nbcController = new NoteBoardController(this, vbCommentStack, taCommentToAdd);
        bPost.setOnMouseClicked(nbcController);
        vbNodeStack.getChildren().addAll(lNoteBoard, flColorContainer, taCommentToAdd, flButtonContainer, spScroll);
        setCenter(vbNodeStack);
        bPost.setId("post-button");
        BorderPane.setAlignment(vbNodeStack, Pos.CENTER);

    }

    /**
     * Creates the comment tile to display and puts on the comment stack
     *
     * @param comment The primary comment message
     * @param date    The date of that paritcular message
     * @param color   The color of the message note
     * @return
     */
    public Pair<BorderPane, Label> createCommentTile(String comment, String date, String color) {
        BorderPane bpComment = new BorderPane();
        vbNodeStack.setMargin(bpComment, new Insets(2, 0, 0, 0));
        bpComment.getStyleClass().add("comment-section");
        bpComment.setId(color);
        HBox gpFlow = new HBox();
        Label lRemove = createRemoveButton(gpFlow);
        VBox.setVgrow(gpFlow, Priority.ALWAYS);
        Label lDate = new Label(date);
        FlowPane fpDate = new FlowPane();
        HBox.setHgrow(fpDate, Priority.ALWAYS);
        fpDate.setAlignment(Pos.CENTER_RIGHT);
        fpDate.getChildren().add(lDate);
        GridPane.setHalignment(fpDate, HPos.RIGHT);
        gpFlow.getChildren().add(fpDate);
        gpFlow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        BorderPane.setMargin(gpFlow, new Insets(3, 10, 0, 10));
        bpComment.setTop(gpFlow);
        Label lComment = new Label(comment);
        BorderPane.setMargin(lComment, new Insets(5, 0, 20, 30));
        lComment.getStyleClass().add("comment-text");
        lComment.setWrapText(true);
        bpComment.setCenter(lComment);
        BorderPane.setAlignment(lComment, Pos.CENTER_LEFT);
        BorderPane.setAlignment(fpDate, Pos.CENTER_RIGHT);
        setLabelBinding(bpComment, lComment);
        bpComment.prefWidthProperty().bind(vbNodeStack.widthProperty());
        lDate.setPadding(new Insets(0, 20, 0, 0));
        lComment.setMinWidth(20);
        return new Pair<BorderPane, Label>(bpComment, lRemove);

    }

    private Label createRemoveButton(HBox gpFlow) {
        Label lRemove = new Label("Remove");
        lRemove.setOnMouseClicked(nbcController);
        lRemove.setId("remove-button");
        FlowPane fpClose = new FlowPane();
        fpClose.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(fpClose, Priority.ALWAYS);
        GridPane.setHalignment(fpClose, HPos.LEFT);
        gpFlow.setMaxSize(Double.MAX_VALUE, 100);
        gpFlow.getChildren().add(fpClose);
        fpClose.getChildren().add(lRemove);
        return lRemove;
    }

    private void setLabelBinding(BorderPane bpComment, Label lComment) {
        lComment.prefWidthProperty().bind(bpComment.widthProperty());
        lComment.prefWidthProperty().bind(bpComment.prefWidthProperty());
        lComment.setPadding(new Insets(0, 20, 0, 0));
        lComment.prefHeightProperty().bind(bpComment.heightProperty());
        lComment.prefHeightProperty().bind(bpComment.prefHeightProperty());
    }

    /**
     *  Retrieves the color specified by the user before posting a comment.
     * @return the current color selected in the color comboBox
     */
    public String getCurrentColor() {
        return cbColor.getValue();
    }

    protected NoteBoardController getController() {
        return nbcController;
    }
}
