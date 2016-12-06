package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Created by Amans on 06/12/2016.
 */
public class NoteBoardPane extends BorderPane {
    public NoteBoardPane() {
        setPadding(new Insets(30, 0, 0, 306));
        GridPane gpComments = new GridPane();
        VBox vbNodeStack = new VBox();
        Label lNoteBoard = new Label("Note Board");
        TextArea taCommentToAdd = new TextArea();
        Button bPost = new Button("Post Note");
        vbNodeStack.getChildren().addAll(lNoteBoard, taCommentToAdd, bPost, createCommentTile("hello gus", "02:16 06/12/2016"));
        setCenter(vbNodeStack);
        BorderPane.setAlignment(vbNodeStack, Pos.CENTER);
    }


    private BorderPane createCommentTile(String comment, String date) {
        BorderPane bpComment = new BorderPane();
        Label lRemove = new Label("X");
        Label lDate = new Label(date);
        GridPane gpFlow = new GridPane();
        FlowPane fpClose = new FlowPane();
        FlowPane fpDate = new FlowPane();
        fpClose.setAlignment(Pos.CENTER_LEFT);
        gpFlow.add(fpClose, 0, 0);
        GridPane.setHalignment(fpClose, HPos.LEFT);
        gpFlow.add(fpDate, 1, 0);
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
