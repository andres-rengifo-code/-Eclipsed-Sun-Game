package model;

/**
 * This class represent a word in the game
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class Palabra {
    private String contenidoPalabra;

    /**
     * This method sets the content of the word
     * @param contenidoPalabra the word content
     */
    public void  setContenidoPalabra(String contenidoPalabra){
        this.contenidoPalabra = contenidoPalabra.toUpperCase();
    }

    /**
     * This method gets the content of the word
     * @return String the word content
     */
    public String getContenidoPalabra (){
        return contenidoPalabra;
   }



    /**
     * This method gets the length of the word
     * @return int the word length
     */
   public int longitudPalabra(){
        return contenidoPalabra.length();
    }

    /**
     * This method validates if the word meets the game requirements
     * @return boolean true if the word is valid
     */
    public boolean validaPalabra(){
        return longitudPalabra() >= 6 && longitudPalabra() <= 12;
    }


}
