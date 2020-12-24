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
        brandChoice.setItems(FXCollections.observableArrayList( knowledgeRepository.getAllBrands()));
        
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

}
