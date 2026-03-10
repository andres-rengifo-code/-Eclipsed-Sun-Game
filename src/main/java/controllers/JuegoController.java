package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import model.Palabra;
import utilitis.GameData;
import utilitis.Paths;

import javafx.scene.image.Image;
import java.awt.*;

public class JuegoController {


    @FXML
    private TextField casillaN10;

    @FXML
    private TextField casillaN11;

    @FXML
    private TextField casillaN12;

    @FXML
    private TextField casillaN2;

    @FXML
    private TextField casillaN4;

    @FXML
    private TextField casillaN5;

    @FXML
    private TextField casillaN6;

    @FXML
    private TextField casillaN7;

    @FXML
    private TextField casillaN8;

    @FXML
    private TextField casillaN9;

    @FXML
    private TextField casillaN1;

    @FXML
    private TextField casillaN3;

    @FXML
    private ImageView imagenJuegoVariable;

    private TextField[] casillas;
    private  int intentosFallidos =0;


    @FXML
    void initialize(){
        Palabra palabra = App.app.getPalabra();
        casillas = new TextField[]{casillaN1,casillaN2,casillaN3,casillaN4,casillaN5,casillaN6,casillaN7,casillaN8,casillaN9,casillaN10,casillaN11,casillaN12};


        bloquearCasillasExtras(palabra);
        configurarCasillas(palabra);


    }



   private void bloquearCasillasExtras(Palabra palabra){
       int longitud = palabra.longitudPalabra();
            for (int i= longitud; i <12; i++) {
                casillas[i].setDisable(true);
                casillas[i].setVisible(false);
            }
    }

   private void configurarCasillas(Palabra palabra){
        for(int i = 0; i< palabra.longitudPalabra(); i++){
            int posicion =i;

            casillas[i].setTextFormatter(new TextFormatter<>(change -> {
                String confirmarLetraCasilla = change.getControlNewText();
                if (confirmarLetraCasilla.length()>1) return null;
                if(!confirmarLetraCasilla.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]*"))return null;
                return change;
            }));


            casillas[i].setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    verificarLetra(posicion);
                }
            } );

        }

   }
   private void verificarLetra(int posicion){
       Palabra palabra = App.app.getPalabra();
       char[] letrasPalabraAdivinar = palabra.getContenidoPalabra().toCharArray();

       String textoIntroducidoUsuario =casillas[posicion].getText();
       char letraUsiarioIntrroducio = textoIntroducidoUsuario.charAt(0);

       if (letrasPalabraAdivinar[posicion]== Character.toUpperCase(letraUsiarioIntrroducio)){
           casillas[posicion].setStyle("-fx-background-color: green; -fx-text-fill: white;");
           casillas[posicion].setEditable(false);
       }
       else{
           casillas[posicion].clear();
           intentosFallidos++;
           System.out.println("Intentos fallidos: " + intentosFallidos + "/" + 5);
           actualizarImagen();

       }
       if (juegoCompletado()){
           GameData.jugadorGano = true;
           App.app.setScene(Paths.YOU_WIN);
       }
       if (intentosFallidos >= 5){
           GameData.jugadorGano = false;
           App.app.setScene(Paths.YOU_WIN);
       }

   }

   private boolean juegoCompletado(){
        Palabra palabra = App.app.getPalabra();
        for (int i = 0; i<palabra.longitudPalabra();i++){
            if (!casillas[i].getStyle().contains("green")) return false;
        }
        return true;
   }

   private void actualizarImagen(){
        String rutaImagenfallos = "/Imagenes/imagenIntentosFallidosNumero" + intentosFallidos +".png";
       Image nuevaImagen = new Image(getClass().getResourceAsStream(rutaImagenfallos));
       imagenJuegoVariable.setImage(nuevaImagen);


   }




    @FXML
    void ayudarJugador(ActionEvent event) {

    }


}
