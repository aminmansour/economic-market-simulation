package view;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.CountryCodeDictionary;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hanitawil on 30/11/2016.
 * A stack which contains a comboxBox to query the country and also contains all the countries currently currently qurried. It also provides the ability to remove a particular country that is queried.
 */
public class CountryNode extends GridPane {

    private ComboBox<String> countries;
    private ImageView flag;

    /**
     * creates a stack containing a combobox with a list of countries which the user can select from.
     * @param defualtValue the default string of the combobox
     */
    public CountryNode(String defualtValue) {
        try {
            String csvFile = "src/main/resources/storage/CountryCodesCore.csv";

            ArrayList<String> cNames = new CountryCodeDictionary(csvFile).getCountrynames();
            countries = new ComboBox<String>();
            for (int i = 0; i < cNames.size(); ++i) {
                countries.getItems().add(i, cNames.get(i));
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

    }


    /**
     * Allows open access to the list of countries in a combobox
     * @return The combobox with particular countries
     */

    public ComboBox<String> getCountries() {
        return countries;
    }


}
