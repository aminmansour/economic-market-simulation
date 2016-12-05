package view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    public CountryNode() throws IOException {
        String csvFile = "src/main/resources/storage/CountryCodesCore.csv";


        ArrayList<String> cnames = new CountryReader(csvFile).getCountrynames();

       countries = new ComboBox<String>();

        for (int i = 0; i < cnames.size(); ++i) {

            countries.getItems().add(i, cnames.get(i));

        }

        countries.getSelectionModel().selectFirst();

        countries.setMaxWidth(190);

        this.add(countries, 0, 0);

        GridPane grid2 = new GridPane();

        this.add(grid2, 1, 0);


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
