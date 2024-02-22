package gfl.kp1.controllers.popups;

import javafx.scene.Node;
import javafx.stage.Popup;

import java.io.IOException;

public abstract class AbstractPopupFactory {
    public Popup createPopup(Node node) {
        Popup popup = new Popup();
        popup.getContent().setAll(node);
        popup.setAutoHide(true);
        return popup;
    }
    public abstract Popup createUpdatePopup() throws IOException;
    public abstract Popup createCreatePopup() throws IOException;
}
