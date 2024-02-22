package gfl.kp1.controllers.popups;

import gfl.kp1.controllers.manufacturer.UpdateManufacturerController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Popup;

import java.io.IOException;

public class ManufacturerPopupFactory extends AbstractPopupFactory{

    @Override
    public Popup createUpdatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/manufacturer/updateManufacturer.fxml"));
        Popup popup = createPopup(loader.load());
        popup.setOnShowing((event)-> ((UpdateManufacturerController) loader.getController()).updateForm());
        return popup;
    }

    @Override
    public Popup createCreatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/manufacturer/createManufacturer.fxml"));
        return createPopup(loader.load());
    }
}
