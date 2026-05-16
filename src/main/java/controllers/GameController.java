package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import model.GameData;
import model.Word;
import utilitis.Paths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.control.Label;


/**
 * This class represent the controller for the main game screen
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class GameController {


    @FXML
    private TextField slot10;

    @FXML
    private TextField slot11;

    @FXML
    private TextField slot12;

    @FXML
    private TextField slot2;

    @FXML
    private TextField slot4;

    @FXML
    private TextField slot5;

    @FXML
    private TextField slot6;

    @FXML
    private TextField slot7;

    @FXML
    private TextField slot8;

    @FXML
    private TextField slot9;

    @FXML
    private TextField slot1;

    @FXML
    private TextField slot3;

    @FXML
    private ImageView gameImageView;

    @FXML
    private Label helpLabel;

    @FXML
    private Label errorCounterLabel;


    private TextField[] slots;
    private  int failedAttempts =0;
    private  int helpCounter = 0;
    private  int randomIndex;



    /**
     * This method initializes the controller and sets up the game
     */
    @FXML
    void initialize(){
        Word word = App.app.getWord();
        slots = new TextField[]{slot1,slot2, slot3,slot4,slot5,slot6,slot7, slot8, slot9,slot10, slot11, slot12};


        disableExtraSlots(word);
        configureSlots(word);


    }



    /**
     * This method disables and hides extra text fields
     * @param palabra the secret word object
     */
    private void disableExtraSlots(Word word){
        int length = word.getLength();
            for (int i= length; i <12; i++) {
                slots[i].setDisable(true);
                slots[i].setVisible(false);
            }
    }

    /**
     * This method configures the behavior of each text field
     * @param word the secret word object
     */
    private void configureSlots(Word word){
        for(int i = 0; i<  word.getLength(); i++){
            int position =i;

            slots[i].setTextFormatter(new TextFormatter<>(change -> {
                String newText = change.getControlNewText();
                if (newText.length()>1) return null;
                if(!newText.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]*"))return null;
                return change;
            }));


            slots[i].setOnKeyTyped(event -> {
                String text = slots[position].getText();
                if (!text.isEmpty()) {
                    checkLetter(position);
                }
            } );

        }

   }
    /**
     * This method verifies if the entered letter matches the secret word
     * @param posicion the position of the letter in the word
     */
   private void checkLetter(int position){
       Word word = App.app.getWord();
       char[] wordLetters = word.getContent().toCharArray();

       String userText = slots[position].getText();
       char userLetter = userText.charAt(0);

       if (removeAccent(wordLetters[position]) == removeAccent(Character.toUpperCase(userLetter))){
           slots[position].setStyle("-fx-background-color: green; -fx-text-fill: white;");
           slots[position].setEditable(false);
       }
       else{
           slots[position].clear();
           failedAttempts++;
           errorCounterLabel.setText("ERRORS  " + (failedAttempts) + "/5");
           updateImage();

       }
       if (isGameCompleted()){
           GameData.playerWon = true;
           App.app.setScene(Paths.YOU_WIN);
       }
       if (failedAttempts >= 5){
           GameData.playerWon = false;
           App.app.setScene(Paths.YOU_WIN);
       }

   }

    /**
     * This method checks if the game has been completed
     * @return boolean true if all letters are correct
     */
   private boolean isGameCompleted(){
       Word word = App.app.getWord();
        for (int i = 0; i<word.getLength();i++){
            if (!slots[i].getStyle().contains("green")) return false;
        }
        return true;
   }

    /**
     * This method updates the sun image based on the number of errors
     */
   private void updateImage(){
        String imagePath = "/Images/failedAttemptImage" + failedAttempts +".png";
       Image newImage = new Image(getClass().getResourceAsStream(imagePath));
       gameImageView.setImage(newImage);


   }
    /**
     * This method removes accents from a letter
     * @param letter the letter to process
     * @return char the letter without accent
     */
    private char removeAccent(char letter){
        return switch (letter){
            case 'Á' -> 'A';
            case 'É' -> 'E';
            case 'Í' -> 'I';
            case 'Ó' -> 'O';
            case 'Ú' -> 'U';
            default -> letter;

        };
   }




    /**
     * This method provides help by revealing a random letter
     * @param event the action event
     */
   @FXML
    void onHelp(ActionEvent event) {
        if (helpCounter >= 3)
            return;


       Word word = App.app.getWord();
        do {
            randomIndex =(int) (Math.random()*word.getLength());
        }while (slots[randomIndex].getStyle().contains("green"));

       slots[randomIndex].setText(String.valueOf(word.getContent().charAt(randomIndex)));
       slots[randomIndex].setStyle("-fx-background-color: green; -fx-text-fill: white;");
        helpCounter++;
       helpLabel.setText("" + (3 - helpCounter));

    }

    /**
     * This method shows the rules window
     * @param event the action event
     */
    @FXML
    void onShowRules(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Rules.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Game Rules");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            // Centrar la ventana respecto a la ventana principal
            stage.initOwner(slots[0].getScene().getWindow());
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
