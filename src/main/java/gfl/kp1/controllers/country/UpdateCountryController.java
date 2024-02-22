package gfl.kp1.controllers.country;

import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.models.countryTable.CountryTableModel;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class UpdateCountryController {
    @FXML
    public TextField nameTextField;

    CountryTableModel countryTableModel = CountryTableModel.getInstance();
    private int id;

    public void updateForm() {
        Country country = countryTableModel.getSelectedTableItem();

        id = country.getId();
        nameTextField.setText(country.getName());
    }

    public void onClickUpdateButton(MouseEvent mouseEvent) {
        countryTableModel.updateItem(id, nameTextField.getText());
    }
}
