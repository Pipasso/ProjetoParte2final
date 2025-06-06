package pt.ulusofona.aed.deisimdb;

public class Realizador {
    int idRealizador;
    String nomeRealizador;
    int idFilme;

    public Realizador( String idRealizador ,String nomeRealizador, String idFilme){
        this.idRealizador = Integer.parseInt(idRealizador.trim());
        this.nomeRealizador = nomeRealizador;
        this.idFilme = Integer.parseInt(idFilme.trim());
    }

    public Realizador(int i, String nome, int i1){
        this.idRealizador = i;
        this.nomeRealizador = nome;
        this.idFilme = i1;
    }

    public String toString(){
        return idRealizador + " | " + nomeRealizador + " | " + idFilme;
    }


}
