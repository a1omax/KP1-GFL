package gfl.kp1.controllers.popups;

import gfl.kp1.controllers.souvenir.UpdateSouvenirController;
import gfl.kp1.controllers.manufacturer.UpdateManufacturerController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Popup;

import java.io.IOException;

public class SouvenirPopupFactory extends AbstractPopupFactory{

    @Override
    public Popup createUpdatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/souvenir/updateSouvenir.fxml"));
        Popup popup = createPopup(loader.load());
        popup.setOnShowing((event)-> ((UpdateSouvenirController) loader.getController()).updateForm()); // todo
        return popup;
    }

    @Override
    public Popup createCreatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/souvenir/createSouvenir.fxml"));
        return createPopup(loader.load());
    }
}
