package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import model.Palabra;
import utilitis.Paths;

public class InsertController {

    @FXML
    private TextField palabraJuego;

    @FXML
    void initialize(){
        palabraJuego.setTextFormatter(new TextFormatter<>(change -> {
            String palabraAdivinar = change.getControlNewText();
            if (palabraAdivinar.length()>12) return null;
            if(!palabraAdivinar.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]*"))return null;
            return change;
        }));
    }


    @FXML
    void empezarJuego(ActionEvent event) {
        Palabra palabra = App.app.getPalabra();
        palabra.setContenidoPalabra(palabraJuego.getText());


        if(!palabra.validaPalabra()) {
            palabraJuego.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            palabraJuego.clear();
            palabraJuego.setPromptText("Palabra entre 6 y 12 letras");
            return;
        }
        System.out.println(palabra);
        App.app.setScene(Paths.JUEGO);
    }

}
