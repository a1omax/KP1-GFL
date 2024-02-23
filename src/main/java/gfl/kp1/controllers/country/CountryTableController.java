package gfl.kp1.controllers.country;

import gfl.kp1.controllers.TableController;
import gfl.kp1.controllers.popups.CountryPopupFactory;
import gfl.kp1.data.Country;
import gfl.kp1.models.countryTable.CountryTableModel;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CountryTableController extends TableController<Country> {

    private final CountryTableModel countryTableModel = CountryTableModel.getInstance();

    //Columns
    @FXML
    public TableColumn<Country, String> nameColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @Override
    protected void initializeTableColumns(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    protected void initializePopupFactory() {
        popupFactory = new CountryPopupFactory();
    }

    @Override
    protected void initializeTableModel() {
        dataTableModel = CountryTableModel.getInstance();
    }



}
