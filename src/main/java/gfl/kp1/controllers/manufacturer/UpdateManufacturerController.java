package gfl.kp1.controllers.manufacturer;

import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.models.countryTable.CountryTableModel;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class UpdateManufacturerController {
    public ComboBox<Country> countryComboBox;
    public TextField manufacturerNameTextField;

    ManufacturerTableModel manufacturerTableModel = ManufacturerTableModel.getInstance();
    CountryTableModel countryTableModel = CountryTableModel.getInstance();
    private int id;

    public void updateForm() {
        Manufacturer manufacturer = manufacturerTableModel.getSelectedTableItem();

        id = manufacturer.getId();
        manufacturerNameTextField.setText(manufacturer.getName());
        Optional<Country> c = countryTableModel.getById(manufacturer.getCountryId());
        c.ifPresent(country -> countryComboBox.setValue(country));


    }

    public void onShowingCountryComboBox(Event event) {
        countryComboBox.setItems(FXCollections.observableList(countryTableModel.getAll()));
    }
    public void onClickUpdateButton(MouseEvent mouseEvent) {
        manufacturerTableModel.updateItem(id, manufacturerNameTextField.getText(), countryComboBox.getValue().getId());
    }
}
