package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameData;
import utilitis.Paths;

public class finalScreenController {

    @FXML
    private ImageView ImagenFinDeJuego;



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





    @FXML
    void ReanudarJuegoJuegadorGano(ActionEvent event) {
        GameData.jugadorGano= false;
        App.app.setScene(Paths.START);

    }

}
