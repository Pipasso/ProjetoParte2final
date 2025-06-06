package pt.ulusofona.aed.deisimdb;

public class GenerosFilmes {
    int idGenero;
    long idFilme;

    public GenerosFilmes(String idGenero, String idFilme){
        this.idGenero = Integer.parseInt(idGenero.trim());
        this.idFilme = Long.parseLong(idFilme.trim());

    }
    public String toString(){
        return idGenero + " | " + idFilme;
    }
}
