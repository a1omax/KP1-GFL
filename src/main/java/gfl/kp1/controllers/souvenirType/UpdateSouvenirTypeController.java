package gfl.kp1.controllers.souvenirType;

import gfl.kp1.data.SouvenirType;
import gfl.kp1.models.souvenirTypeTable.SouvenirTypeTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UpdateSouvenirTypeController {
    @FXML
    public TextField nameTextField;

    SouvenirTypeTableModel souvenirTypeTableModel = SouvenirTypeTableModel.getInstance();
    private int id;

    public void updateForm() {
        SouvenirType souvenirType = souvenirTypeTableModel.getSelectedTableItem();

        id = souvenirType.getId();
        nameTextField.setText(souvenirType.getName());
    }

    public void onClickUpdateButton(MouseEvent mouseEvent) {
        souvenirTypeTableModel.updateItem(id, nameTextField.getText());
    }
}
