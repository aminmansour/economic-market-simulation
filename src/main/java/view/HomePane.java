package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


/**
 * Created by denissaidov on 28/11/2016.
 */
public class HomePane extends BorderPane {
    public HomePane(LineChart<String,Number> linechart) {
        //<div>Icons made by <a href="http://www.flaticon.com/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="http://www.flaticon.com" title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>

        setPadding(new Insets(25,0,0,300));
        addLogoAndTitle(linechart);

    }

    private void addLogoAndTitle(LineChart<String,Number> linechart) {
        GridPane gpLogoStore = new GridPane();
        gpLogoStore.setAlignment(Pos.CENTER);
        ImageView logoIconIV = createIcon();
        Label lWelcomeMsg = new Label( "Macro Economics");
        lWelcomeMsg.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        GridPane.setHalignment(lWelcomeMsg, HPos.CENTER);
        GridPane.setHalignment(logoIconIV, HPos.CENTER);
        lWelcomeMsg.getStyleClass().add("welcomeMsg");
        setCenter(gpLogoStore);
        gpLogoStore.add(logoIconIV, 0, 0);
        gpLogoStore.add(lWelcomeMsg,0,1);
    }

    private ImageView createIcon() {
        Label lLogoIcon = new Label("");
        ImageView logoIconIV = new ImageView(new Image(getClass().getResourceAsStream("/image/logo-icon.png")));
        logoIconIV.setFitWidth(150);
        logoIconIV.setPreserveRatio(true);
        lLogoIcon.setGraphic(logoIconIV);
        return logoIconIV;
    }

}