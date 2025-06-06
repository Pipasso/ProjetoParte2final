package pt.ulusofona.aed.deisimdb;

public class InputInvalido {
    String nomeFicheiro;
    int linhasCertas;
    int linhasErradas;
    int primeiraLinha;


    public InputInvalido(String nomeFicheiro ,String linhasCertas, String linhasErradas, String primeiraLinha){
        this.nomeFicheiro = nomeFicheiro;
        this.linhasCertas = Integer.parseInt(linhasCertas.trim());
        this.linhasErradas = Integer.parseInt(linhasErradas.trim());
        this.primeiraLinha = Integer.parseInt(primeiraLinha.trim());
    }



    public String toString(){
        return nomeFicheiro + " | " + linhasCertas + " | " + linhasErradas + " | " + primeiraLinha;
    }
}
