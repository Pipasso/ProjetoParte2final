package pt.ulusofona.aed.deisimdb;

public enum TipoEntidade{
    ATOR,
    REALIZADOR,
    GENERO_CINEMATOGRAFICO,
    FILME,
    INPUT_INVALIDO;

    @Override
    public String toString(){
        switch (this){
            case ATOR:
                return "Ator";
            case REALIZADOR:
                return "Realizador";
            case GENERO_CINEMATOGRAFICO:
                return "Gerero Cinematografico";
            case FILME:
                return "Filme";
            case INPUT_INVALIDO:
                return "Input Inválido";
            default:
                return this.name();

        }
    }
}
