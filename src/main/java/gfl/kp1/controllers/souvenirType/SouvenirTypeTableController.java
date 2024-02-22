package gfl.kp1.controllers.souvenirType;

import gfl.kp1.controllers.TableController;
import gfl.kp1.controllers.popups.SouvenirTypePopupFactory;
import gfl.kp1.data.SouvenirType;
import gfl.kp1.repositories.SouvenirTypeRepository;
import gfl.kp1.models.souvenirTypeTable.SouvenirTypeTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SouvenirTypeTableController extends TableController<SouvenirType> {

    private final SouvenirTypeTableModel souvenirTypeTableModel = SouvenirTypeTableModel.getInstance();

    //Columns
    @FXML
    public TableColumn<SouvenirType, String> nameColumn;


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
        popupFactory = new SouvenirTypePopupFactory();
    }


    @Override
    protected void initializeTableModel() {
        dataTableModel = souvenirTypeTableModel;
    }



}
