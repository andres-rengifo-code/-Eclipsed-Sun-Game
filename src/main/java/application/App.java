package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Palabra;
import utilitis.Paths;

import java.io.IOException;

/**
 * This class represent the main application of the game
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */

public class App extends Application {

    public static App app;
    private Stage stageWindows;
    private Palabra palabra = new Palabra();

    /**
     * This method executes the launch of the JavaFX application
     * @param args command line arguments
     */
    public static void main(String[] args){
        launch();
    }

    /**
     * This method starts the JavaFX application and sets the initial scene
     * @param stage the primary stage for this application
     */
    @Override
    public void start (Stage stage){
        app = this;
        stageWindows = stage;
        setScene(Paths.START);

    }

    /**
     * This method gets the secret word object
     * @return Palabra the secret word
     */
    public Palabra getPalabra() {
        return palabra;
    }

    /**
     * This method changes the current scene of the application
     * @param paths the path to the FXML file
     */
    public void setScene(String paths){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(paths));
        try {
            Parent pane =loader.load();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(
                    getClass().getResource("/estilos.css").toExternalForm());
            stageWindows.setScene(scene);
            stageWindows.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
