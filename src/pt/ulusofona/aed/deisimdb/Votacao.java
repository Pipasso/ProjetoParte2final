package pt.ulusofona.aed.deisimdb;

public class Votacao {
    int idVotos;
    double votacaoFilme;
    int contagemVotacao;

    public Votacao(String idVotos, String votacaoFilme, String contagemVotacao){
        this.idVotos = Integer.parseInt(idVotos.trim());
        this.votacaoFilme = Double.parseDouble(votacaoFilme.trim());
        this.contagemVotacao = Integer.parseInt(contagemVotacao.trim());
    }

    public String toString(){
        return idVotos + "," + votacaoFilme + "," + contagemVotacao;
    }

}
