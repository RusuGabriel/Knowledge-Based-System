package system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.xml.sax.SAXException;
import system.App;
import system.models.KnowledgeStore;
import system.utils.*;

import javax.xml.parsers.ParserConfigurationException;

public class KnowledgeBaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        KnowledgeStore knowledgeStore = KnowledgeStore.getInstance();
        try {
            knowledgeStore.loadKnowledge();
        }catch (ParserConfigurationException |SAXException |IOException exc) {
            System.out.println("----:KNOWLEDGE BASE MISSING:----");
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        App.display(Constants.Screens.HOME);
    }
    
}
