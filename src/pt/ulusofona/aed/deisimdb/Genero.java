package pt.ulusofona.aed.deisimdb;

public class Genero {
    int idGenero;
    String nomeGenero;

    public Genero(String idGenero, String nomeGenero){
        this.idGenero = Integer.parseInt(idGenero.trim());
        this.nomeGenero = nomeGenero;

    }
    public Genero(int i, String action) {
        this.idGenero = i;
        this.nomeGenero = action;

    }

    public String toString(){
        return idGenero + " | " + nomeGenero;
    }
}
