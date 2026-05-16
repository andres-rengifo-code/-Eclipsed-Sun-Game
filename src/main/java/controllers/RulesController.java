package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class represent the controller for the rules screen
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class RulesController {

    @FXML
    private Button closeButton;

    /**
     * This method closes the rules window
     */
    @FXML
    private void onCloseWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}