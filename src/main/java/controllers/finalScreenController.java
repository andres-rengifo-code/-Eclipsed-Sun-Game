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
public class finalScreenController {

    @FXML
    private ImageView ImagenFinDeJuego;



    /**
     * This method initializes the controller and sets the appropriate image
     */
    @FXML
    public void initialize(){
        if(GameData.jugadorGano){
            ImagenFinDeJuego.setImage(new Image(getClass().getResourceAsStream("/Imagenes/ECE_YOU_WIN.jpg"))
            );
        }else {
            ImagenFinDeJuego.setImage(new Image(getClass().getResourceAsStream("/Imagenes/ECE_GAME_OVER.jpg"))
            );
        }

    }




    /**
     * This method handles the click on the "VOLVER A JUGAR" button
     * @param event the action event
     */
    @FXML
    void ReanudarJuegoJuegadorGano(ActionEvent event) {
        GameData.jugadorGano= false;
        App.app.setScene(Paths.START);

    }

}
