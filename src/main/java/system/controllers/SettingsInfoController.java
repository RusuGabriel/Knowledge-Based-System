package system.controllers;

import system.App;
import system.repository.*;
import system.utils.*;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SettingsInfoController implements Initializable {
    private DeviceRepository deviceRepository = DeviceRepository.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void reloadData(ActionEvent actionEvent) {
        deviceRepository.reloadData();
    }

    @FXML
    public void goHome(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }

}
