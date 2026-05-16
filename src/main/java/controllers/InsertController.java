package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import model.Word;
import utilitis.Paths;

/**
 * This class represent the controller for the word insertion screen
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class InsertController {

    /** Text field for entering the secret word */
    @FXML
    private TextField wordTextField;

    /**
     * This method initializes the controller and configures the text field
     */
    @FXML
    void initialize(){
        wordTextField.setTextFormatter(new TextFormatter<>(change -> {
            String wordToGuess = change.getControlNewText();
            if (wordToGuess.length()>12) return null;
            if(!wordToGuess.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]*"))return null;
            return change;
        }));
    }


    /**
     * This method handles the click on the "JUGAR" button
     * @param event the action event
     */

    @FXML
    void onStartGame(ActionEvent event) {
        Word word = App.app.getWord();
        word.setContent(wordTextField.getText());


        if(!word.isValid()) {
            wordTextField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            wordTextField.clear();
            wordTextField.setPromptText("Word between 6 and 12 letters");
            return;
        }
        System.out.println(word);
        App.app.setScene(Paths.GAME);
    }

}
