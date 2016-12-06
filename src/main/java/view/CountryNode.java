package view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import model.CountryReader;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hanitawil on 30/11/2016.
 */
public class CountryNode extends GridPane {

    private ComboBox<String> countries;
    private Button minus;

    public CountryNode(String defualtValue) {
        try {
            String csvFile = "src/main/resources/storage/CountryCodesCore.csv";

            ArrayList<String> cnames = new CountryReader(csvFile).getCountrynames();
            countries = new ComboBox<String>();
            for (int i = 0; i < cnames.size(); ++i) {

                countries.getItems().add(i, cnames.get(i));

            }

            countries.setValue(defualtValue);
            countries.setMaxWidth(162);


            add(countries, 0, 0);
            GridPane grid2 = new GridPane();
            add(grid2, 1, 0);
        } catch (IOException e) {
            DialogPane jdError = new DialogPane();
            jdError.setContentText("The storage files have either been deleted or corrupted");

        }

//        minus = new Button("-");
//
//        grid2.add(minus, 1, 0);
    }

    public ComboBox<String> getCountries() {
        return countries;
    }

    public Button getMinus() {
        return minus;
    }
}
