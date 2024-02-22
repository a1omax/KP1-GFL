package gfl.kp1.controllers.souvenir;

import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.SouvenirType;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import gfl.kp1.models.souvenirTable.SouvenirTableModel;
import gfl.kp1.models.souvenirTypeTable.SouvenirTypeTableModel;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


@Slf4j
public class CreateSouvenirController implements Initializable {
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField detailsTextField;
    @FXML
    public DatePicker dateDatePicker;
    @FXML
    public TextField priceTextField;
    @FXML
    public ComboBox<Manufacturer> manufacturerComboBox;
    @FXML
    public ComboBox<SouvenirType> souvenirTypeComboBox;

    SouvenirTableModel souvenirTableModel = SouvenirTableModel.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*"))
                return null;
            else
                return c;
        }));
    }


    @FXML
    public void onClickCreateButton() {
        String name = nameTextField.getText();
        String details = detailsTextField.getText();
        String date = dateDatePicker.getValue().format(DateTimeFormatter.ISO_DATE);
        int price = Integer.parseInt(priceTextField.getText());
        int souvenirTypeId = souvenirTypeComboBox.getValue().getId();
        int manufacturerId = manufacturerComboBox.getValue().getId();

        souvenirTableModel.add(name, details, date, price, souvenirTypeId, manufacturerId);

        // todo threads

    }

    @FXML
    public void getManufacturers(Event event) {
        manufacturerComboBox.setItems(FXCollections.observableList(ManufacturerTableModel.getInstance().getAll()));
    }
    @FXML
    public void getSouvenirTypes(Event event) {
        souvenirTypeComboBox.setItems(FXCollections.observableList(SouvenirTypeTableModel.getInstance().getAll()));
    }

}
