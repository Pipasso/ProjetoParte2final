package pt.ulusofona.aed.deisimdb;

public class Ator {
    long idAtor;
    String nomeAtor;
    String sexoAtor;
    long idFilme;

    public Ator(String idAtor, String nomeAtor, String sexoAtor, String idFilme) {
        this.idAtor = Long.parseLong(idAtor.trim());
        this.nomeAtor = nomeAtor;
        this.sexoAtor = sexoAtor;
        this.idFilme = Integer.parseInt(idFilme.trim());

    }


    public Ator(int i, String nome, char sexo, int id){
        this.idAtor = i;
        this.nomeAtor = nome;
        this.sexoAtor = String.valueOf(sexo);
        this.idFilme = id;
    }

    public String gender(){
        if (sexoAtor == null){
            return "N/A";
        }
        return sexoAtor.equals("M") ? "Masculino" : "Feminino";

    }

    public String toString(){
        return idAtor + " | " + nomeAtor + " | " + gender() + " | " + idFilme;

    }



}
