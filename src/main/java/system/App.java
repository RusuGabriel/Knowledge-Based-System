package system;


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * Main class of the system
 */
public class App extends Application {
    private static Parent root;
    private static Stage stage;
    private static App instance;

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
    }

    

    public static void display(String screen) {
        try {
            root = FXMLLoader.load(instance.getClass().getResource(screen));
            Scene scene = new Scene(root);
            stage.setScene(scene);
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
