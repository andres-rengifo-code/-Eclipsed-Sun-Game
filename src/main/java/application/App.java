package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Palabra;
import utilitis.Paths;

import java.io.IOException;

public class App extends Application {

    public static App app;
    private Stage stageWindows;
    private Palabra palabra = new Palabra();

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start (Stage stage){
        app = this;
        stageWindows = stage;
        setScene(Paths.START);

    }

    public Palabra getPalabra() {
        return palabra;
    }

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
