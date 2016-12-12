package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Created by denissaidov on 28/11/2016.
 * Sets up the home view when the program is intially loaded
 */
public class HomePane extends BorderPane {

    /**
     * a home splash screen to welcome the viewer. The main lander page.
     */
    public HomePane() {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

        setPadding(new Insets(25,0,0,300));
        addLogoAndTitle();

    }

    private void addLogoAndTitle() {
        GridPane gpLogoStore = new GridPane();
        gpLogoStore.setAlignment(Pos.CENTER);
        ImageView logoIconIV = createIcon();
        Label lWelcomeMsg = new Label("Macro Economics");
        lWelcomeMsg.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        GridPane.setHalignment(lWelcomeMsg, HPos.CENTER);
        GridPane.setHalignment(logoIconIV, HPos.CENTER);
//        lWelcomeMsg.setStyle("-fx-background-color: #0D1B1E");
//        lWelcomeMsg.getStyleClass().add("welcomeMsg");
        setCenter(gpLogoStore);
        gpLogoStore.add(logoIconIV, 0, 0);
        gpLogoStore.add(lWelcomeMsg,0,1);
        gpLogoStore.setVgap(10);
    }

    private ImageView createIcon() {
        Label lLogoIcon = new Label("");
        ImageView logoIconIV = new ImageView(new Image(getClass().getResourceAsStream("/image/money.gif")));
        logoIconIV.setFitWidth(600);
        logoIconIV.setPreserveRatio(true);
        lLogoIcon.setGraphic(logoIconIV);
        return logoIconIV;
    }

}