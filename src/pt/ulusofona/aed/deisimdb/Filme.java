package pt.ulusofona.aed.deisimdb;


import java.util.*;

public class Filme {
    public int idFilme;
    public String nomeFilme;
    public float duracaoFilme;
    public int budgetFilme;
    public String dataFilme;
    public List<Genero> generos;

    public int nrGeneros;
    public int nrRealizadores;
    public int atoresM;
    public int atoresF;

    public List<String> listaGeneros;
    public List<String> listaRealizadores;

    public Filme(String idFilme, String nomeFilme, String duracaoFilme, String budgetFilme, String dataFilme){
        this.idFilme = Integer.parseInt(idFilme.trim());
        this.nomeFilme= nomeFilme.trim();
        this.duracaoFilme = Float.parseFloat(duracaoFilme.trim());
        this.budgetFilme = Integer.parseInt(budgetFilme.trim());
        this.dataFilme = dataFilme.trim();
        this.generos = List.of();

        this.nrGeneros = 0;
        this.nrRealizadores = 0;
        this.atoresM = 0;
        this.atoresF = 0;
        this.listaGeneros = new ArrayList<>();
        this.listaRealizadores = new ArrayList<>();
    }




    public String toString(){
        String genero;
        String realizador;
        if (idFilme < 1000){
            genero = String.join(",",listaGeneros);
            realizador = String.join(",", listaRealizadores);

        }else{
            genero = String.valueOf(nrGeneros);
            realizador = String.valueOf(nrRealizadores);

        }
        return idFilme + " | " + nomeFilme + " | " + dataFilme + " | " + genero + " | " + realizador + " | " +
                atoresM + " | " + atoresF;
    }

    public static void associar(ArrayList<Filme> filmes, ArrayList<Ator> atores,
                                ArrayList<GenerosFilmes> generosFilmes, ArrayList<Genero> generos,
                                ArrayList<Realizador> realizadores){

        Map<Long, String> nomeGeneroPorId = new HashMap<>();
        for (Genero g : generos) {
            nomeGeneroPorId.put((long) g.idGenero, g.nomeGenero);
        }

        // 2) Agrupa nomes de gêneros por idFilme
        Map<Integer, Set<String>> generosPorFilme = new HashMap<>();
        for (GenerosFilmes gf : generosFilmes) {
            int fId = (int) gf.idFilme;
            Long idGeneroLong = (long) gf.idGenero;
            String nomeDoGenero = nomeGeneroPorId.getOrDefault(idGeneroLong, "");

            if (!nomeDoGenero.isEmpty()) {
                generosPorFilme
                        .computeIfAbsent(fId, k -> new HashSet<>())
                        .add(nomeDoGenero);
            }
        }

        // 3) Agrupa realizadores por idFilme
        Map<Integer, List<Realizador>> realizPorFilme = new HashMap<>();
        for (Realizador r : realizadores) {
            realizPorFilme
                    .computeIfAbsent(r.idFilme, k -> new ArrayList<>())
                    .add(r);
        }

        // 4) Agrupa atores por idFilme
        Map<Integer, List<Ator>> atoresPorFilme = new HashMap<>();
        for (Ator a : atores) {
            atoresPorFilme
                    .computeIfAbsent((int) a.idFilme, k -> new ArrayList<>())
                    .add(a);
        }

        // 5) Para cada filme, atribui diretamente aos campos de instância
        for (Filme f : filmes) {
            int filmeId = f.idFilme;


            // Obtém os nomes dos gêneros para este filme
            Set<String> nomesGeneros = generosPorFilme.getOrDefault(filmeId, Collections.emptySet());

            // Popula a listaGeneros com os nomes dos gêneros (remove strings vazias)
            f.listaGeneros = new ArrayList<>();
            for (String nomeGenero : nomesGeneros) {
                if (nomeGenero != null && !nomeGenero.trim().isEmpty()) {
                    f.listaGeneros.add(nomeGenero.trim());
                }
            }

            //organiza os generos por ordem alfabetica
            Collections.sort(f.listaGeneros);

            // Conta quantos gêneros esse filme tem e atribui a f.nrGeneros
            f.nrGeneros = f.listaGeneros.size();

            // Popula a listaRealizadores com os nomes dos realizadores
            List<Realizador> realizadoresDoFilme = realizPorFilme.getOrDefault(filmeId, Collections.emptyList());
            f.listaRealizadores = new ArrayList<>();
            for (Realizador r : realizadoresDoFilme) {
                if (r.nomeRealizador != null && !r.nomeRealizador.trim().isEmpty()) {
                    f.listaRealizadores.add(r.nomeRealizador.trim());
                }
            }

            Collections.sort(f.listaRealizadores);

            // Conta quantos realizadores e atribui a f.nrRealizadores
            f.nrRealizadores = realizadoresDoFilme.size();

            // Conta quantos atores Masculinos e Femininos e atribui a f.atoresM/f.atoresF
            int contM = 0, contF = 0;
            for (Ator a : atoresPorFilme.getOrDefault(filmeId, Collections.emptyList())) {
                if ("M".equalsIgnoreCase(a.sexoAtor)) {
                    contM++;
                } else if ("F".equalsIgnoreCase(a.sexoAtor)) {
                    contF++;
                }
            }
            f.atoresM = contM;
            f.atoresF = contF;
        }
    }


    /*public static void associar(ArrayList<Filme> filmes, ArrayList<Ator> atores,
                                ArrayList<GenerosFilmes> generosFilmes, ArrayList<Genero> generos,
                                ArrayList<Realizador> realizadores){

        Map<Long, String> nomeGeneroPorId = new HashMap<>();
        for (Genero g : generos) {
            nomeGeneroPorId.put((long) g.idGenero, g.nomeGenero);
        }

        // Debug: mostrar alguns mapeamentos
        System.out.println("[DEBUG] Primeiros 5 gêneros mapeados:");
        int count = 0;
        for (Map.Entry<Long, String> entry : nomeGeneroPorId.entrySet()) {
            if (count < 5) {
                System.out.println("  ID " + entry.getKey() + " -> " + entry.getValue());
                count++;
            }
        }

        // 2) Agrupa nomes de gêneros por idFilme
        Map<Integer, Set<String>> generosPorFilme = new HashMap<>();
        for (GenerosFilmes gf : generosFilmes) {
            int fId = (int) gf.idFilme;
            // Garantir que ambos os IDs são comparados como Long
            Long idGeneroLong = (long) gf.idGenero;
            String nomeDoGenero = nomeGeneroPorId.getOrDefault(idGeneroLong, "");

            // Debug: mostrar se encontrou o gênero
            if (fId == 680) { // Filme Pulp Fiction como exemplo
                System.out.println("[DEBUG] Filme 680 procura gênero ID " + idGeneroLong +
                        " -> encontrou: '" + nomeDoGenero + "'");
                System.out.println("[DEBUG] Tipo do ID procurado: " + idGeneroLong.getClass().getSimpleName());
                System.out.println("[DEBUG] Verificando se existe na tabela de gêneros...");
                for (Map.Entry<Long, String> entry : nomeGeneroPorId.entrySet()) {
                    if (entry.getKey().equals(idGeneroLong)) {
                        System.out.println("[DEBUG] ENCONTRADO! " + entry.getKey() + " = " + entry.getValue());
                        break;
                    }
                }
            }

            if (!nomeDoGenero.isEmpty()) {
                generosPorFilme
                        .computeIfAbsent(fId, k -> new HashSet<>())
                        .add(nomeDoGenero);
            }
        }

        // 3) Agrupa realizadores por idFilme
        Map<Integer, List<Realizador>> realizPorFilme = new HashMap<>();
        for (Realizador r : realizadores) {
            realizPorFilme
                    .computeIfAbsent(r.idFilme, k -> new ArrayList<>())
                    .add(r);
        }

        // 4) Agrupa atores por idFilme
        Map<Integer, List<Ator>> atoresPorFilme = new HashMap<>();
        for (Ator a : atores) {
            atoresPorFilme
                    .computeIfAbsent((int) a.idFilme, k -> new ArrayList<>())
                    .add(a);
        }

        // 5) Para cada filme, atribui diretamente aos campos de instância
        for (Filme f : filmes) {
            int filmeId = f.idFilme;

            // Obtém os nomes dos gêneros para este filme
            Set<String> nomesGeneros = generosPorFilme.getOrDefault(filmeId, Collections.emptySet());

            // Popula a listaGeneros com os nomes dos gêneros (remove strings vazias)
            f.listaGeneros = new ArrayList<>();
            for (String nomeGenero : nomesGeneros) {
                if (nomeGenero != null && !nomeGenero.trim().isEmpty()) {
                    f.listaGeneros.add(nomeGenero.trim());
                }
            }

            // Debug: imprimir para verificar se está funcionando
            if (filmeId < 1000 && !f.listaGeneros.isEmpty()) {
                System.out.println("Filme ID " + filmeId + " tem gêneros: " + f.listaGeneros);
            }

            // Conta quantos gêneros esse filme tem e atribui a f.nrGeneros
            f.nrGeneros = f.listaGeneros.size();

            // Popula a listaRealizadores com os nomes dos realizadores
            List<Realizador> realizadoresDoFilme = realizPorFilme.getOrDefault(filmeId, Collections.emptyList());
            f.listaRealizadores = new ArrayList<>();
            for (Realizador r : realizadoresDoFilme) {
                if (r.nomeRealizador != null && !r.nomeRealizador.trim().isEmpty()) {
                    f.listaRealizadores.add(r.nomeRealizador.trim());
                }
            }

            // Debug: imprimir para verificar se está funcionando
            if (filmeId < 1000 && !f.listaRealizadores.isEmpty()) {
                System.out.println("Filme ID " + filmeId + " tem realizadores: " + f.listaRealizadores);
            }

            // Conta quantos realizadores e atribui a f.nrRealizadores
            f.nrRealizadores = realizadoresDoFilme.size();

            // Conta quantos atores Masculinos e Femininos e atribui a f.atoresM/f.atoresF
            int contM = 0, contF = 0;
            for (Ator a : atoresPorFilme.getOrDefault(filmeId, Collections.emptyList())) {
                if ("M".equalsIgnoreCase(a.sexoAtor)) {
                    contM++;
                } else if ("F".equalsIgnoreCase(a.sexoAtor)) {
                    contF++;
                }
            }
            f.atoresM = contM;
            f.atoresF = contF;
        }
    }*/

}




