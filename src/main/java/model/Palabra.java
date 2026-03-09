package model;

public class Palabra {
    private String contenidoPalabra;

    public void  setContenidoPalabra(String contenidoPalabra){
        this.contenidoPalabra = contenidoPalabra.toUpperCase();
    }

   public String getContenidoPalabra (){
        return contenidoPalabra;
   }



    public int longitudPalabra(){
        return contenidoPalabra.length();
    }

    public boolean validaPalabra(){
        return longitudPalabra() >= 6 && longitudPalabra() <= 12;
    }



}
