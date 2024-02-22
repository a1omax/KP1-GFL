package gfl.kp1.controllers.manufacturer;

import gfl.kp1.controllers.TableController;
import gfl.kp1.controllers.popups.ManufacturerPopupFactory;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.repositories.ManufacturerRepository;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ManufacturerTableController extends TableController<Manufacturer> {

    private final ManufacturerTableModel manufacturerTableModel = ManufacturerTableModel.getInstance();

    //Columns
    @FXML
    public TableColumn<Manufacturer, String> nameColumn;
    @FXML
    public TableColumn<Manufacturer, String> countryColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    protected void initializeTableColumns(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(data -> new SimpleStringProperty(
                manufacturerTableModel.getCountry(data.getValue()).getName()
        ));
    }

    @Override
    protected void initializePopupFactory() {
        popupFactory = new ManufacturerPopupFactory();
    }

    @Override
    protected void initializeTableModel() {
        dataTableModel = ManufacturerTableModel.getInstance();
    }



}
