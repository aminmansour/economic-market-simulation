package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

/**
 * Created by Amans on 06/12/2016.
 */
public class NoteBoardPane extends BorderPane {
    private VBox vbNodeStack;

    public NoteBoardPane() {
        setPadding(new Insets(30, 0, 0, 306));
        GridPane gpComments = new GridPane();
        vbNodeStack = new VBox();
        Label lNoteBoard = new Label("Note Board");
        TextArea taCommentToAdd = new TextArea();
        Button bPost = new Button("Post Note");
        FlowPane flButtonContainer = new FlowPane(bPost);
        flButtonContainer.setAlignment(Pos.CENTER_RIGHT);
        vbNodeStack.setMargin(flButtonContainer, new Insets(0, 0, 20, 0));
        flButtonContainer.setMargin(bPost, new Insets(10, 20, 0, 0));
        vbNodeStack.setMargin(taCommentToAdd, new Insets(0, 30, 0, 30));

        vbNodeStack.getChildren().addAll(lNoteBoard, taCommentToAdd, flButtonContainer, createCommentTile("hello gus", "02:16 06/12/2016"));
        setCenter(vbNodeStack);

        BorderPane.setAlignment(vbNodeStack, Pos.CENTER);
    }


    private BorderPane createCommentTile(String comment, String date) {
        BorderPane bpComment = new BorderPane();
        Label lRemove = new Label("X");
        lRemove.setId("remove-button");
        Label lDate = new Label(date);
        HBox gpFlow = new HBox();
        FlowPane fpClose = new FlowPane();
        FlowPane fpDate = new FlowPane();
        fpDate.setStyle("-fx-background-color: #9AF261");
        fpClose.setAlignment(Pos.CENTER_LEFT);
        VBox.setVgrow(gpFlow, Priority.ALWAYS);
        gpFlow.setMaxSize(Double.MAX_VALUE, 100);
        HBox.setHgrow(fpClose, Priority.ALWAYS);
        HBox.setHgrow(fpDate, Priority.ALWAYS);
        gpFlow.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        gpFlow.getChildren().add(fpClose);
        BorderPane.setMargin(gpFlow, new Insets(3, 10, 0, 10));
        GridPane.setHalignment(fpClose, HPos.LEFT);
        gpFlow.getChildren().add(fpDate);
        GridPane.setHalignment(fpDate, HPos.RIGHT);
        fpDate.setAlignment(Pos.CENTER_RIGHT);
        fpClose.getChildren().add(lRemove);
        fpDate.getChildren().add(lDate);
        bpComment.setTop(gpFlow);

        Label lComment = new Label(comment);
        lComment.getStyleClass().add("comment-text");
        bpComment.setCenter(lComment);
        BorderPane.setAlignment(lComment, Pos.CENTER_LEFT);
        BorderPane.setAlignment(fpDate, Pos.CENTER_RIGHT);

        return bpComment;

    }
}
