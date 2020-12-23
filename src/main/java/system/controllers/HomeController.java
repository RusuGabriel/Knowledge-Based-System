package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import system.App;
import system.utils.*;

public class HomeController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @FXML
    public void goToRules(ActionEvent actionEvent) {
        App.display(Constants.Screens.KNOWLEDGE_BASE);
    }

    @FXML
    public void goToKnowledgeBase(ActionEvent actionEvent){
        App.display(Constants.Screens.KNOWLEDGE_BASE);
    }

}
