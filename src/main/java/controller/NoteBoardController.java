package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.CommentStore;
import view.NoteBoardPane;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 * Created by Amans on 06/12/2016.
 */
public class NoteBoardController implements EventHandler<MouseEvent> {

    private static CommentStore commentStrore;
    private TextArea taComment;
    private VBox vbCommentStack;
    private NoteBoardPane nbpCommentView;

    public NoteBoardController(NoteBoardPane view, VBox vbComments, TextArea comment) {
        commentStrore = new CommentStore();
        vbCommentStack = vbComments;
        taComment = comment;
        nbpCommentView = view;
        ArrayList<String> commentColors = commentStrore.getCommentColors();
        int counter = 0;
        for (Map.Entry<String, String> currentComment : commentStrore.getComments().entrySet()) {
            Pair<BorderPane, Label> commentSection = nbpCommentView.createCommentTile(currentComment.getKey(), currentComment.getValue(), commentColors.get(counter).toLowerCase());
            vbComments.getChildren().add(0, commentSection.getKey());
            commentSection.getValue().setOnMouseClicked(this);
            ++counter;
        }
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            if (taComment.getText().trim().length() > 0) {
                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm dd-MM-yyyy");//dd/MM/yyyy
                Date now = new Date();
                String strDate = sdfDate.format(now);
                System.out.println(strDate);
                Pair<String, String> commentOf = commentStrore.addToComments(taComment.getText().trim().replaceAll("\\s+", " "), strDate, nbpCommentView.getCurrentColor());
                vbCommentStack.getChildren().add(0, nbpCommentView.createCommentTile(commentOf.getKey(), commentOf.getValue(), nbpCommentView.getCurrentColor().toLowerCase()).getKey());
                taComment.clear();
            }
        } else {

            Label jRemove = (Label) event.getSource();
            System.out.println("hello");
            Label lCommentWritten = (Label) ((BorderPane) jRemove.getParent().getParent().getParent()).getCenter();
            commentStrore.removeToComments(lCommentWritten.getText());
            vbCommentStack.getChildren().remove(lCommentWritten.getParent());
        }
    }


    public void saveToFile() {
        commentStrore.saveToFile();
    }


}
