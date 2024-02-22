package gfl.kp1.controllers.souvenir;

import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.Souvenir;
import gfl.kp1.data.SouvenirType;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import gfl.kp1.models.souvenirTable.SouvenirTableModel;
import gfl.kp1.models.souvenirTypeTable.SouvenirTypeTableModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;



@Slf4j
public class UpdateSouvenirController{

    @FXML
    public TextField updateSouvenirNameTextField;
    @FXML
    public TextField updateSouvenirDetailsTextField;
    @FXML
    public DatePicker updateSouvenirDateDatePicker;
    @FXML
    public TextField updateSouvenirPriceTextField;
    @FXML
    public ComboBox<Manufacturer> updateSouvenirManufacturerComboBox;
    @FXML
    public ComboBox<SouvenirType> updateSouvenirSouvenirTypeComboBox;

    private final SouvenirTableModel souvenirTableModel = SouvenirTableModel.getInstance();


    // Hidden
    private int id;



    @FXML
    public void getManufacturers(Event event) {
        updateSouvenirManufacturerComboBox.setItems(FXCollections.observableList(ManufacturerTableModel.getInstance().getAll()));
    }
    @FXML
    public void getSouvenirTypes(Event event) {
        updateSouvenirSouvenirTypeComboBox.setItems(FXCollections.observableList(SouvenirTypeTableModel.getInstance().getAll()));
    }

    @FXML
    public void onClickUpdateButton(ActionEvent actionEvent) {
        int id = this.id;
        String name = updateSouvenirNameTextField.getText();
        String details = updateSouvenirDetailsTextField.getText();
        String date = updateSouvenirDateDatePicker.getValue().format(DateTimeFormatter.ISO_DATE);
        int price = Integer.parseInt(updateSouvenirPriceTextField.getText());
        int souvenirTypeId = updateSouvenirSouvenirTypeComboBox.getValue().getId();
        int manufacturerId = updateSouvenirManufacturerComboBox.getValue().getId();

        souvenirTableModel.updateItem(id, name, details, date, price, souvenirTypeId, manufacturerId);
    }

    public void updateForm() {
        SouvenirTableModel souvenirTableModel = SouvenirTableModel.getInstance();
        Souvenir s = souvenirTableModel.getSelectedTableItem();

        if (s != null) {

            id = s.getId();
            updateSouvenirNameTextField.setText(s.getName());
            updateSouvenirDetailsTextField.setText(s.getDetails());
            updateSouvenirDateDatePicker.setValue(s.getISODate());
            updateSouvenirPriceTextField.setText(String.valueOf(s.getPrice()));

            Optional<Manufacturer> souvenirToUpdateManufacturer = ManufacturerTableModel.getInstance().getById(s.getManufacturerId());
            Optional<SouvenirType> souvenirToUpdateSouvenirType = SouvenirTypeTableModel.getInstance().getById(s.getSouvenirTypeId());

            souvenirToUpdateManufacturer.ifPresent((manufacturer) -> updateSouvenirManufacturerComboBox.setValue(manufacturer));
            souvenirToUpdateSouvenirType.ifPresent((souvenirType) -> updateSouvenirSouvenirTypeComboBox.setValue(souvenirType));
        }
    }
}
