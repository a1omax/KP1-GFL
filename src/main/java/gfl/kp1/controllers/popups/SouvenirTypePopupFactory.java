package gfl.kp1.controllers.popups;

import gfl.kp1.controllers.souvenirType.UpdateSouvenirTypeController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Popup;

import java.io.IOException;

public class SouvenirTypePopupFactory extends AbstractPopupFactory {

    @Override
    public Popup createUpdatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/souvenirType/updateSouvenirType.fxml"));
        Popup popup = createPopup(loader.load());
        popup.setOnShowing((event)-> ((UpdateSouvenirTypeController) loader.getController()).updateForm());
        return popup;
    }

    @Override
    public Popup createCreatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/souvenirType/createSouvenirType.fxml"));
        return createPopup(loader.load());
    }

}
