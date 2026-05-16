package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameData;
import utilitis.Paths;

/**
 * This class represent the controller for the final screen
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class FinalScreenController {

    @FXML
    private ImageView finalGameImageView;



    /**
     * This method initializes the controller and sets the appropriate image
     */
    @FXML
    public void initialize(){
        if(GameData.playerWon){
            finalGameImageView.setImage(new Image(getClass().getResourceAsStream("/Images/ECE_YOU_WIN.jpg"))
            );
        }else {
            finalGameImageView.setImage(new Image(getClass().getResourceAsStream("/Images/ECE_GAME_OVER.jpg"))
            );
        }

    }




    /**
     * This method handles the click on the "VOLVER A JUGAR" button
     * @param event the action event
     */
    @FXML
    void onPlayAgain(ActionEvent event) {
        GameData.playerWon = false;
        App.app.setScene(Paths.START);

    }

}
