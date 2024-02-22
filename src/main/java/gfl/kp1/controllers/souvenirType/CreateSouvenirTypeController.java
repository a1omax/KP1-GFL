package gfl.kp1.controllers.souvenirType;

import gfl.kp1.models.souvenirTypeTable.SouvenirTypeTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class CreateSouvenirTypeController {
    @FXML
    public TextField nameTextField;

    SouvenirTypeTableModel souvenirTypeTableModel = SouvenirTypeTableModel.getInstance();


    public void onClickCreateButton(MouseEvent mouseEvent) {
        souvenirTypeTableModel.add(nameTextField.getText());
    }
}