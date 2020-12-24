package system.controllers;

import system.App;
import system.models.ElectronicDevice;
import system.utils.*;
import system.repository.*;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;

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

        knowledgeTable.getColumns().addAll(modelCol,brandCol, yearCol);
        knowledgeTable.getItems().addAll(FXCollections.observableArrayList(knowledgeRepository.getAll()));
        knowledgeTable.setEditable(false);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

}
