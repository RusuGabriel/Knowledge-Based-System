package system;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Main class of the system
 */
public class App extends Application {
    private static final File RESOURCES_FOLDER = new File("src/main/resources/templates");
    private static Parent root;
    private static Stage guiStage;
    private static App instance;
    private static ArrayList<String> templatesPaths = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        guiStage = stage;
        instance = this;
        display();

    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void display() {
        try {
            root = FXMLLoader.load(instance.getClass().getResource("/templates/HomeScreen.fxml"));
            System.out.println(root);
            System.out.println("WTF is happening??!");
            Scene scene = new Scene(root);
            guiStage.setScene(scene);
            guiStage.show();
        } catch (Exception e) {
            System.out.println("The error message is:");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }
    }

}
