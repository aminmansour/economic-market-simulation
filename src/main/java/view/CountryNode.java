package view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.CountryReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hanitawil on 30/11/2016.
 */
public class CountryNode extends GridPane {

    private ComboBox<String> countries;
    private ImageView flag;

    public CountryNode(String defualtValue) {
        try {
            String csvFile = "src/main/resources/storage/CountryCodesCore.csv";
            CountryReader countryReader = new CountryReader(csvFile);
            ArrayList<String> cnames = countryReader.getCountrynames();
            countries = new ComboBox<String>();
            add(countries, 0, 0);
            GridPane grid2 = new GridPane();
            add(grid2, 1, 0);


            countries.setOnAction((event) -> {
            String name  =  countries.getSelectionModel().getSelectedItem();
             String code =  countryReader.getcode(name);
                 flag = new ImageView();
                grid2.getChildren().remove(flag);
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("src/main/resources/image/flags/"+ code.toLowerCase() + ".png");
                    flag = new ImageView(new Image(fis));
                    flag.setFitHeight(25);
                    grid2.add(flag, 1, 0);
                } catch (FileNotFoundException e) {
                    System.out.println("no flag");
                }

            });
            for (int i = 0; i < cnames.size(); ++i) {

                countries.getItems().add(i, cnames.get(i));


            }

            countries.setValue(defualtValue);
            countries.setMaxWidth(162);



        } catch (IOException e) {
            DialogPane jdError = new DialogPane();
            jdError.setContentText("The storage files have either been deleted or corrupted");


        }


    }

    public ComboBox<String> getCountries() {
        return countries;
    }

    public ImageView getMinus() {
        return flag;
    }
}
