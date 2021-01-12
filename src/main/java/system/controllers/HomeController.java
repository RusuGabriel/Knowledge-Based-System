package system.controllers;

import system.App;
import system.models.*;
import system.repository.*;
import system.utils.*;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class HomeController implements Initializable {

    @FXML
    PieChart chart;
    private ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadChartData();
        chart.getData().addAll(chartData);
    }

    private void loadChartData() {
        Map<String, Integer> map = new TreeMap<>();
        for (ElectronicDevice device : DeviceRepository.getInstance().getAll()) {
            if (device instanceof Phone) {
                Integer freq = map.get(Constants.Type.PHONE);
                map.put(Constants.Type.PHONE, freq == null ? 1 : freq + 1);
            } else if (device instanceof Tablet) {
                Integer freq = map.get(Constants.Type.TABLET);
                map.put(Constants.Type.TABLET, freq == null ? 1 : freq + 1);
            } else if (device instanceof Watch) {
                Integer freq = map.get(Constants.Type.WATCH);
                map.put(Constants.Type.WATCH, freq == null ? 1 : freq + 1);
            }
        }

        chartData.addAll(new PieChart.Data(Constants.Type.PHONE, map.get(Constants.Type.PHONE)),
                new PieChart.Data(Constants.Type.TABLET, map.get(Constants.Type.TABLET)),
                new PieChart.Data(Constants.Type.WATCH, map.get(Constants.Type.WATCH)));
    }

    @FXML
    public void goToKnowledgeBase(ActionEvent actionEvent) {
        App.display(Constants.Screens.KNOWLEDGE_BASE);
    }

    @FXML
    public void goToInfo() {
        App.display(Constants.Screens.INFO);
    }
}
