package gfl.kp1.controllers.country;

import gfl.kp1.data.Country;
import gfl.kp1.models.countryTable.CountryTableModel;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class CreateCountryController {
    @FXML
    public TextField nameTextField;

    CountryTableModel countryTableModel = CountryTableModel.getInstance();


    public void onClickCreateButton(MouseEvent mouseEvent) {
        countryTableModel.add(nameTextField.getText());
    }
}