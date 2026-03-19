package controllers;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import model.Palabra;
import model.GameData;
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

    @FXML
    private Label cAyudas;

    @FXML
    private Label labelContadorErores;


    private TextField[] casillas;
    private  int intentosFallidos =0;
    private  int contadorAyudas = 0;
    private  int indice;



    /**
     * This method initializes the controller and sets up the game
     */
    @FXML
    void initialize(){
        Palabra palabra = App.app.getPalabra();
        casillas = new TextField[]{casillaN1,casillaN2,casillaN3,casillaN4,casillaN5,casillaN6,casillaN7,casillaN8,casillaN9,casillaN10,casillaN11,casillaN12};


        bloquearCasillasExtras(palabra);
        configurarCasillas(palabra);


    }



    /**
     * This method disables and hides extra text fields
     * @param palabra the secret word object
     */
    private void bloquearCasillasExtras(Palabra palabra){
       int longitud = palabra.longitudPalabra();
            for (int i= longitud; i <12; i++) {
                casillas[i].setDisable(true);
                casillas[i].setVisible(false);
            }
    }

    /**
     * This method configures the behavior of each text field
     * @param palabra the secret word object
     */
    private void configurarCasillas(Palabra palabra){
        for(int i = 0; i< palabra.longitudPalabra(); i++){
            int posicion =i;

            casillas[i].setTextFormatter(new TextFormatter<>(change -> {
                String confirmarLetraCasilla = change.getControlNewText();
                if (confirmarLetraCasilla.length()>1) return null;
                if(!confirmarLetraCasilla.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]*"))return null;
                return change;
            }));


            casillas[i].setOnKeyTyped(event -> {
                String textoActual = casillas[posicion].getText();
                if (!textoActual.isEmpty()) {
                    verificarLetra(posicion);
                }
            } );

        }

   }
    /**
     * This method verifies if the entered letter matches the secret word
     * @param position the position of the letter in the word
     */
   private void verificarLetra(int posicion){
       Palabra palabra = App.app.getPalabra();
       char[] letrasPalabraAdivinar = palabra.getContenidoPalabra().toCharArray();

       String textoIntroducidoUsuario =casillas[posicion].getText();
       char letraUsiarioIntrroducio = textoIntroducidoUsuario.charAt(0);

       if (quitarTilde(letrasPalabraAdivinar[posicion])==quitarTilde(Character.toUpperCase(letraUsiarioIntrroducio))){
           casillas[posicion].setStyle("-fx-background-color: green; -fx-text-fill: white;");
           casillas[posicion].setEditable(false);
       }
       else{
           casillas[posicion].clear();
           intentosFallidos++;
           labelContadorErores.setText("ERRORES " + (intentosFallidos) + "/5");
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

    /**
     * This method checks if the game has been completed
     * @return boolean true if all letters are correct
     */
   private boolean juegoCompletado(){
        Palabra palabra = App.app.getPalabra();
        for (int i = 0; i<palabra.longitudPalabra();i++){
            if (!casillas[i].getStyle().contains("green")) return false;
        }
        return true;
   }

    /**
     * This method updates the sun image based on the number of errors
     */
   private void actualizarImagen(){
        String rutaImagenfallos = "/Imagenes/imagenIntentosFallidosNumero" + intentosFallidos +".png";
       Image nuevaImagen = new Image(getClass().getResourceAsStream(rutaImagenfallos));
       imagenJuegoVariable.setImage(nuevaImagen);


   }
    /**
     * This method removes accents from a letter
     * @param letter the letter to process
     * @return char the letter without accent
     */
    private char quitarTilde(char letra){
        return switch (letra){
            case 'Á' -> 'A';
            case 'É' -> 'E';
            case 'Í' -> 'I';
            case 'Ó' -> 'O';
            case 'Ú' -> 'U';
            default -> letra;

        };
   }




    /**
     * This method provides help by revealing a random letter
     * @param event the action event
     */
   @FXML
    void ayudarJugador(ActionEvent event) {
        if (contadorAyudas >= 3)
            return;


        Palabra palabra = App.app.getPalabra();
        do {
            indice =(int) (Math.random()*palabra.longitudPalabra());
        }while (casillas[indice].getStyle().contains("green"));

        casillas[indice].setText(String.valueOf(palabra.getContenidoPalabra().charAt(indice)));
        casillas[indice].setStyle("-fx-background-color: green; -fx-text-fill: white;");
        contadorAyudas++;
        cAyudas.setText("" + (3 - contadorAyudas));

    }

    /**
     * This method shows the rules window
     * @param event the action event
     */
    @FXML
    void mostrarReglas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Reglas.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Reglas del Juego");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            // Centrar la ventana respecto a la ventana principal
            stage.initOwner(casillas[0].getScene().getWindow());
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
