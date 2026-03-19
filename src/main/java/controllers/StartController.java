package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utilitis.Paths;

import java.io.IOException;

/**
 * This class represent the controller for the start screen
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class StartController {

    /**
     * This method handles the click on the "Comenzar" button
     * @param event the action event
     */
    @FXML
    void clickComenzar(ActionEvent event) {
        App.app.setScene(Paths.INSERT);

    }

    /**
     * This method handles the click on the "Salir" button
     * @param event the action event
     */
    @FXML
    void clickSalir(ActionEvent event) {
        System.exit(0);

    }

    /**
     * This method handles the click on the "Reglas" button
     * @param event the action event
     */
    @FXML
    void clickReglas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reglas.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Reglas del Juego");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            // Centrar la ventana
            stage.setOnShown(e -> {
                stage.setX((stage.getOwner().getX() + stage.getOwner().getWidth() / 2) - stage.getWidth() / 2);
                stage.setY((stage.getOwner().getY() + stage.getOwner().getHeight() / 2) - stage.getHeight() / 2);
            });

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
