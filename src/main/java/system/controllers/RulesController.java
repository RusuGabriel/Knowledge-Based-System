package system.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import system.App;
import system.utils.Constants;

public class RulesController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    @FXML
    public void goBack() {
        App.display(Constants.Screens.HOME);
    }
    
}
