package system.controllers;


import system.App;
import system.utils.*;
import system.repository.*;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


public class KnowledgeBaseController implements Initializable {
    private KnowledgeRepository knowledgeStore = KnowledgeRepository.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        knowledgeStore.loadKnowledge();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

}
