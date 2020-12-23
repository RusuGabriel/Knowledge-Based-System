package system;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.utils.Constants;
import javafx.fxml.FXMLLoader;
import java.util.logging.Logger;

/**
 * Main class of the system
 */
public class App extends Application {
    private static Parent root;
    private static Stage stage;
    private static App instance;
    private static Logger logger;

    @Override
    public void start(Stage stage) {
        initialize(this,stage);
        display(Constants.Screens.HOME);
    }

    /**
     * Method to initialize the needed internal instance variables,
     * after running the app has all the internal references initialized
     *
     * @param stage - the primary stage of the app
     */
    private static void initialize( App currentApp, Stage stage) {
        App.stage = stage;
        App.instance = currentApp;
        App.logger = Logger.getLogger(App.class.getName());
    }

    
    /**
     * This method changes the current screen of the app based 
     * on the <b>screen</b> parameter. This method enables the 
     * navigation within the App.
     * 
     * @param screen - the screen name that will be displayed
     */
    public static void display(String screen) {
        try {
            root = FXMLLoader.load(instance.getClass().getResource(screen));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Electronics Knowledge-Base");
            stage.show();
        } catch (Exception e) {
            System.out.println("ERROR - The error message is: ");
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
