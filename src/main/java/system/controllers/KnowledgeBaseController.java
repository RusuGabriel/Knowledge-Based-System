package system.controllers;

import system.App;
import system.models.ElectronicDevice;
import system.utils.*;
import system.repository.*;

import java.net.URL;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;
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
    private KnowledgeRepository knowledgeRepository = KnowledgeRepository.getInstance();

    @FXML
    private ChoiceBox brandChoice;

    @FXML
    private ChoiceBox typeChoice;

    @FXML
    private TableView<ElectronicDevice> knowledgeTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        knowledgeRepository.loadKnowledge();
        brandChoice.setItems(FXCollections.observableArrayList(knowledgeRepository.getAllBrands()));
        brandChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String oldValue, String newValue) {
                var filteredItems = knowledgeRepository.getAll().stream().filter(device -> device.getBrand().equals(newValue)).collect(Collectors.toList());
                knowledgeTable.setItems(FXCollections.observableArrayList(filteredItems));
            }
        });

        TableColumn<ElectronicDevice, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, String>("Model"));
        modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        modelCol.prefWidthProperty().bind(knowledgeTable.widthProperty().multiply(0.33));
        modelCol.setEditable(false);

        TableColumn<ElectronicDevice, String> brandCol = new TableColumn<>("Brand");
        brandCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, String>("Brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.prefWidthProperty().bind(knowledgeTable.widthProperty().multiply(0.33));
        brandCol.setEditable(false);

        TableColumn<ElectronicDevice, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<ElectronicDevice, Integer>("Year"));
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        yearCol.prefWidthProperty().bind(knowledgeTable.widthProperty().multiply(0.33));
        yearCol.setEditable(false);

        knowledgeTable.getColumns().addAll(modelCol, brandCol, yearCol);
        knowledgeTable.getItems().addAll(FXCollections.observableArrayList(knowledgeRepository.getAll()));
        knowledgeTable.setEditable(false);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

}
