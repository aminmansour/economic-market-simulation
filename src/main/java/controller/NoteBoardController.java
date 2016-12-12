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
import java.util.Date;
import java.util.Map;


/**
 * Created by Amans on 06/12/2016.
 * Retrieves the comment from storage and loads it on screen and sets up the action listener for the post button in the note
 */
public class NoteBoardController implements EventHandler<MouseEvent> {

    private static CommentStore commentStrore;
    private TextArea taComment;
    private VBox vbCommentStack;
    private NoteBoardPane nbpCommentView;


    /**
     * Retrieves from the model the comments which are pulled from storage and creates a tile for each so it can be viewed on screen.
     *
     * @param view       The note board pane that is manipulated.
     * @param vbComments The stack where every comment will be placed on top of.
     * @param comment    The textbox where the user will input there comment before posting it.
     */
    public NoteBoardController(NoteBoardPane view, VBox vbComments, TextArea comment) {
        commentStrore = new CommentStore();
        vbCommentStack = vbComments;
        taComment = comment;
        nbpCommentView = view;
        for (Map.Entry<String, Pair<String, String>> currentComment : commentStrore.getComments().entrySet()) {
            Pair<BorderPane, Label> commentSection = nbpCommentView.createCommentTile(currentComment.getKey(), currentComment.getValue().getKey(), currentComment.getValue().getValue().toLowerCase());
            vbComments.getChildren().add(0, commentSection.getKey());
            commentSection.getValue().setOnMouseClicked(this);
        }
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getSource() instanceof Button) {
            if (taComment.getText().trim().length() > 0) {
                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm dd-MM-yyyy");//dd/MM/yyyy
                Date now = new Date();
                String strDate = sdfDate.format(now);
                Pair<String, Pair<String, String>> commentOf = commentStrore.addToComments(taComment.getText().trim().replaceAll("\\s+", " "), strDate, nbpCommentView.getCurrentColor());
                vbCommentStack.getChildren().add(0, nbpCommentView.createCommentTile(commentOf.getKey(), commentOf.getValue().getKey(), commentOf.getValue().getValue().toLowerCase()).getKey());
                taComment.clear();
            }
        } else {

            Label jRemove = (Label) event.getSource();
            Label lCommentWritten = (Label) ((BorderPane) jRemove.getParent().getParent().getParent()).getCenter();
            commentStrore.removeToComments(lCommentWritten.getText());
            vbCommentStack.getChildren().remove(lCommentWritten.getParent());
        }
    }

    /**
     * A intermediate method to access the model and save to local storage the comments that have recently been added
     */
    public void saveToFile() {
        commentStrore.saveToFile();
    }




}
