package gfl.kp1.controllers.popups;

import gfl.kp1.controllers.country.UpdateCountryController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Popup;

import java.io.IOException;

public class CountryPopupFactory extends AbstractPopupFactory {
    @Override
    public Popup createUpdatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/country/updateCountry.fxml"));
        Popup popup = createPopup(loader.load());
        popup.setOnShowing((event)-> ((UpdateCountryController) loader.getController()).updateForm());
        return popup;
    }

    @Override
    public Popup createCreatePopup() throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractPopupFactory.class.getResource("/fxml/country/createCountry.fxml"));
        return createPopup(loader.load());
    }
}
