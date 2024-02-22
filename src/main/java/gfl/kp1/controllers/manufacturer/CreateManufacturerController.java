package gfl.kp1.controllers.manufacturer;

import gfl.kp1.data.Country;
import gfl.kp1.models.countryTable.CountryTableModel;

import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class CreateManufacturerController {
    public ComboBox<Country> countryComboBox;
    public TextField manufacturerNameTextField;

    CountryTableModel countryTableModel = CountryTableModel.getInstance();

    ManufacturerTableModel manufacturerTableModel = ManufacturerTableModel.getInstance();


    public void onShowingCountryComboBox(Event event) {
        countryComboBox.setItems(FXCollections.observableList(countryTableModel.getAll()));
    }

    public void onClickCreateButton(MouseEvent mouseEvent) {
        manufacturerTableModel.add(manufacturerNameTextField.getText(), countryComboBox.getValue().getId());
    }
}