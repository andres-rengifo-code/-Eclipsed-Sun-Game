package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import utilitis.Paths;

public class StartController {

    @FXML
    void clickComenzar(ActionEvent event) {
        App.app.setScene(Paths.INSERT);

    }

    @FXML
    void clickSalir(ActionEvent event) {
        System.exit(0);

    }

}
