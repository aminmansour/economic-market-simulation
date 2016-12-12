package view;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

/**
 * Created by Amans on 27/11/2016.
 */
public class Util {
    /**
     * makes a new view to display data
     * @return a boderpane that will house the display
     */
    public static BorderPane createViewScreen(){
        BorderPane bpNew = new BorderPane();
        bpNew.setPadding(new Insets(25,0,0,300));
        return  bpNew;
    }
}