package system.controllers;

import system.App;
import system.models.ElectronicDevice;
import system.models.Phone;
import system.models.Tablet;
import system.models.Watch;
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
import javafx.util.converter.IntegerStringConverter;

public class KnowledgeBaseController implements Initializable {
    private DeviceRepository deviceRepository = DeviceRepository.getInstance();

    @FXML
    private ChoiceBox brandChoice;

    @FXML
    private ChoiceBox typeChoice;

    @FXML
    private TableView<ElectronicDevice> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceRepository.loadKnowledge();
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
                var filteredItems = table.getItems().stream().filter(device -> testType(newType, device))
                        .collect(Collectors.toList());
                table.setItems(FXCollections.observableArrayList(filteredItems));
            }

            private boolean testType(String newType, ElectronicDevice device) {
                switch (newType) {
                    case "Phones":
                        return device instanceof Phone;
                    case "Tablets":
                        return device instanceof Tablet;
                    case "Watches":
                        return device instanceof Watch;
                    default:
                        return true;
                }
            }
        });

        TableColumn<ElectronicDevice, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, String>("Model"));
        modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        modelCol.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        modelCol.setEditable(false);

        TableColumn<ElectronicDevice, String> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, String>("Brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        brandCol.setEditable(false);

        TableColumn<ElectronicDevice, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, Integer>("Year"));
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yearCol.prefWidthProperty().bind(table.widthProperty().multiply(0.33));
        yearCol.setEditable(false);

        table.getColumns().addAll(modelCol, brandCol, yearCol);
        table.getItems().addAll(FXCollections.observableArrayList(deviceRepository.getAll()));
        table.setEditable(false);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

}
