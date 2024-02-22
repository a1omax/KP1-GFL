package gfl.kp1.controllers.souvenir;

import gfl.kp1.controllers.TableController;

import gfl.kp1.controllers.popups.SouvenirPopupFactory;
import gfl.kp1.data.Country;
import gfl.kp1.data.Manufacturer;
import gfl.kp1.data.Souvenir;
import gfl.kp1.data.SouvenirType;
import gfl.kp1.models.countryTable.CountryTableModel;
import gfl.kp1.models.manufacturerTable.ManufacturerTableModel;
import gfl.kp1.models.souvenirTypeTable.SouvenirTypeTableModel;
import gfl.kp1.repositories.SouvenirRepository;
import gfl.kp1.models.souvenirTable.SouvenirTableModel;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;


@Slf4j
public class SouvenirTableController extends TableController<Souvenir> {



    // Filters
    @FXML
    public AnchorPane filterPane;

    @FXML
    public Slider maxPriceSlider;

    @FXML
    public TextField maxPriceField;

    @FXML
    public TextField yearTextField;
    @FXML
    public CheckBox yearCheckBox;
    @FXML
    public CheckBox maxPriceCheckBox;

    @FXML
    public CheckBox manufacturerCheckBox;

    @FXML
    public ComboBox<Manufacturer> manufacturerComboBox;

    @FXML
    public CheckBox souvenirTypeCheckBox;
    @FXML
    public ComboBox<SouvenirType> souvenirTypeComboBox;

    @FXML
    public CheckBox countryCheckBox;
    @FXML
    public ComboBox<Country> countryComboBox;
    @FXML
    public Button loadWithFiltersButton;


    // Columns

    @FXML
    private TableColumn<Souvenir, String> nameColumn;
    @FXML
    private TableColumn<Souvenir, String> detailsColumn;
    @FXML
    private TableColumn<Souvenir, String> dateColumn;

    @FXML
    private TableColumn<Souvenir, Integer> priceColumn;
    @FXML
    public TableColumn<Souvenir, String> manufacturerColumn;
    @FXML
    public TableColumn<Souvenir, String> souvenirTypeColumn;
    @FXML
    public TableColumn<Souvenir, String> countryColumn;



    private final SouvenirTableModel souvenirTableModel = SouvenirTableModel.getInstance();



    @FXML
    public void onShowingManufacturerComboBox(Event event) {
        manufacturerComboBox.setItems(FXCollections.observableList(ManufacturerTableModel.getInstance().getAll()));
    }

    @FXML
    public void onShowingSouvenirTypeComboBox(Event event) {
        souvenirTypeComboBox.setItems(FXCollections.observableList(SouvenirTypeTableModel.getInstance().getAll()));
    }

    @FXML
    public void onShowingCountryComboBox(Event event) {
        countryComboBox.setItems(FXCollections.observableList(CountryTableModel.getInstance().getAll()));

    }





    @FXML
    public void onClickLoadWithFilterButton(MouseEvent mouseEvent) {
        List<Souvenir> souvenirList = souvenirTableModel.getAll();
        if (yearCheckBox.isSelected()) {
            int year = Integer.parseInt(yearTextField.getText());
            souvenirList = souvenirTableModel.filterByYear(souvenirList, year);
        }
        if (maxPriceCheckBox.isSelected()) {
            int maxPrice = Integer.parseInt(maxPriceField.getText());
            souvenirList = souvenirTableModel.filterByMaxPrice(souvenirList, maxPrice);
        }
        if (manufacturerCheckBox.isSelected()) {
            Manufacturer manufacturer = manufacturerComboBox.getValue();
            souvenirList = souvenirTableModel.filterByManufacturer(souvenirList, manufacturer.getId());
        }
        if (souvenirTypeCheckBox.isSelected()) {
            SouvenirType souvenirType = souvenirTypeComboBox.getValue();
            souvenirList = souvenirTableModel.filterBySouvenirType(souvenirList, souvenirType.getId());
        }
        if (countryCheckBox.isSelected()) {
            Country country = countryComboBox.getValue();
            souvenirList = souvenirTableModel.filterByCountry(souvenirList, country.getId());
        }

        tableViewLoad(souvenirList);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        initializeSlider();
    }

    @Override
    protected void initializePopupFactory() {
        popupFactory = new SouvenirPopupFactory();

    }

    @Override
    protected void initializeTableModel() {
        dataTableModel = SouvenirTableModel.getInstance();
    }


    protected void initializeTableColumns() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        manufacturerColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        souvenirTableModel.getManufacturer(data.getValue()).getName()
                ));
        souvenirTypeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        souvenirTableModel.getSouvenirType(data.getValue()).getName()
                ));

        countryColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        souvenirTableModel.getCountry(data.getValue()).getName()
                ));
        yearTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (!c.getControlNewText().matches("\\d*"))
                return null;
            else
                return c;
        }));

    }

    private void initializeSlider() {
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        numberFormat.setGroupingUsed(false);
        maxPriceField.textProperty().bindBidirectional(maxPriceSlider.valueProperty(), numberFormat);
    }


}
