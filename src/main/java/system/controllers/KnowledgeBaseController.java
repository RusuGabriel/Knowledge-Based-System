package system.controllers;

import system.App;
import system.models.*;
import system.utils.*;
import system.repository.*;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class KnowledgeBaseController implements Initializable {
    private DeviceRepository deviceRepository = DeviceRepository.getInstance();

    @FXML
    private ChoiceBox brandChoice;

    @FXML
    private ChoiceBox typeChoice;

    @FXML
    private TableView<ElectronicDevice> table;

    @FXML
    private TextField startPrice;

    @FXML
    private TextField endPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        endPrice.setText("0");
        startPrice.setText("0");
        initializeChoices();
        initializeTable();
    }

    private void initializeTable() {
        TableColumn<ElectronicDevice, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, String>("Model"));
        modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        modelCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        modelCol.setEditable(false);

        TableColumn<ElectronicDevice, String> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, String>("Brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        brandCol.setEditable(false);

        TableColumn<ElectronicDevice, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, Integer>("Year"));
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yearCol.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        yearCol.setEditable(false);

        TableColumn<ElectronicDevice, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, Double>("Price"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceCol.prefWidthProperty().bind(table.widthProperty().multiply(0.245));
        priceCol.setEditable(false);

        table.getColumns().addAll(modelCol, brandCol, yearCol, priceCol);
        table.getItems().addAll(FXCollections.observableArrayList(deviceRepository.getAll()));
        table.setEditable(false);
    }

    private void initializeChoices() {
        brandChoice.setItems(FXCollections.observableArrayList(deviceRepository.getAllBrands()));
        brandChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String oldValue, String newValue) {
                var filteredItems = deviceRepository.getAll().stream()
                        .filter(device -> device.getBrand().equals(newValue) || newValue.equals("All"))
                        .collect(Collectors.toList());
                table.setItems(FXCollections.observableArrayList(filteredItems));
            }
        });

        typeChoice.setItems(FXCollections.observableArrayList(deviceRepository.getAllTypes()));
        typeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String oldType, String newType) {
                var index = brandChoice.getSelectionModel().getSelectedIndex() >= 0
                        ? brandChoice.getSelectionModel().getSelectedIndex()
                        : 0;
                brandChoice.getSelectionModel().selectLast();
                brandChoice.getSelectionModel().select(index);
                var filteredItems = table.getItems().stream().filter(device -> testType(newType, device))
                        .collect(Collectors.toList());
                table.setItems(FXCollections.observableArrayList(filteredItems));
            }

            private boolean testType(String newType, ElectronicDevice device) {
                switch (newType) {
                    case Constants.Type.PHONES:
                        return device instanceof Phone;
                    case Constants.Type.TABLETS:
                        return device instanceof Tablet;
                    case Constants.Type.WATCHES:
                        return device instanceof Watch;
                    default:
                        return true;
                }
            }
        });
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

    @FXML
    public void searchAll() {
        Double startingPrice = Double.parseDouble(startPrice.getText());
        Double end = Double.parseDouble(endPrice.getText());
        Double endingPrice = end < 0.01 ? 999999999999.9 : end;

        var filteredResults = table.getItems().stream()
                .filter(device -> device.getPrice() >= startingPrice && device.getPrice() <= endingPrice)
                .collect(Collectors.toList());
        table.setItems(FXCollections.observableArrayList(filteredResults));

    }

    @FXML
    public void resetAll() {
        brandChoice.getSelectionModel().select(0);
        typeChoice.getSelectionModel().select(0);
        endPrice.setText("0");
        startPrice.setText("0");
        table.setItems(FXCollections.observableArrayList(deviceRepository.getAll()));
    }

}
