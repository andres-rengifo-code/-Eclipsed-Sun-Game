package model;

/**
 * This class represent a word in the game
 * @author Andres Felipe Rengifo
 * @author Alvaro Iván Ospina Capera
 * @version 1.0
 */
public class Word {
    private String content;

    /**
     * This method sets the content of the word
     * @param contenidoPalabra the word content
     */
    public void  setContent(String content){
        this.content = content.toUpperCase();
    }

    /**
     * This method gets the content of the word
     * @return String the word content
     */
    public String getContent (){
        return  content;
   }



    /**
     * This method gets the length of the word
     * @return int the word length
     */
   public int getLength(){
        return content.length();
    }

    /**
     * This method validates if the word meets the game requirements
     * @return boolean true if the word is valid
     */
    public boolean isValid(){
        return getLength() >= 6 && getLength() <= 12;
    }


}
