package gfl.kp1.controllers;

import gfl.kp1.controllers.popups.AbstractPopupFactory;
import gfl.kp1.data.Data;

import gfl.kp1.models.AbstractTableModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public abstract class TableController<T extends Data> implements Initializable {

    protected AbstractPopupFactory popupFactory;
    protected AbstractTableModel<T> dataTableModel;

    //Table
    @FXML
    public TableView<T> tableView;


    @FXML
    public Button createItemButton;
    @FXML
    public Button updateItemButton;

    @FXML
    public Button deleteItemButton;

    protected Popup createPopup;
    protected Popup updatePopup;



    @FXML
    private void onClickLoadAllButton() {
        tableViewLoadAll();
    }

    @FXML
    public void onClickDeleteItemButton(MouseEvent mouseEvent) {
        dataTableModel.deleteItemById(dataTableModel.getSelectedTableItem().getId());
        tableViewLoadAll();
    }

    @FXML
    public void showUpdateItemPopup(MouseEvent mouseEvent) {
        if (!updatePopup.isShowing()) {
            updatePopup.show(updateItemButton.getScene().getWindow());
        } else {
            updatePopup.hide();
        }
    }

    @FXML
    public void showCreateItemPopup(MouseEvent mouseEvent) {
        if (!createPopup.isShowing()) {
            createPopup.show(createItemButton.getScene().getWindow());
        } else {
            createPopup.hide();
        }
    }

    @FXML
    public void handleTableViewClicked(MouseEvent mouseEvent) {
        Optional<T> optionalItem = getSelected();
        if (optionalItem.isEmpty()) {
            return;
        }
        dataTableModel.setSelectedTableItem(optionalItem.get());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTableModel();
        initializeTableColumns();
        initializePopupFactory();
        initializePopups();

        updatePopup.setOnHiding((e)->tableViewLoadAll());
        createPopup.setOnHiding((e)->tableViewLoadAll());

        tableViewLoadAll();

    }

    abstract protected void initializeTableColumns();

    abstract protected void initializePopupFactory();



    abstract protected void initializeTableModel();

    protected void initializePopups() {
        try {
            createPopup = popupFactory.createCreatePopup();
            updatePopup = popupFactory.createUpdatePopup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Optional<T> getSelected() {
        return Optional.ofNullable(tableView.getSelectionModel().getSelectedItem());
    }

    protected void tableViewLoad(List<T> item) {
        tableView.setItems(FXCollections.observableList(item));
        tableView.refresh();
    }

    protected void tableViewLoadAll() {
        tableViewLoad(dataTableModel.getAll());
    }

}
