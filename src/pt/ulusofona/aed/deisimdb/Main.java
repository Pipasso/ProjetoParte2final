package pt.ulusofona.aed.deisimdb;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    static ArrayList<Ator>  atores = new ArrayList<>();
    static ArrayList<Filme> filmes = new ArrayList<>();
    static ArrayList<GenerosFilmes> generosFilmes = new ArrayList<>();
    static ArrayList<Realizador> realizadores = new ArrayList<>();
    static ArrayList<InputInvalido> inputInvalidos = new ArrayList<>();
    static ArrayList<Genero> generos = new ArrayList<>();
    static ArrayList<Votacao> votos = new ArrayList<>();



    static Map<Integer,Integer> nomeGeneroPorId = new HashMap<>();
    static Map<Integer,Integer> filmToNumRealizadores = new HashMap<>();
    static Map<Integer,Integer> filmToNumAtorM   = new HashMap<>();
    static Map<Integer, Integer> filmToNumAtorF = new HashMap<>();
    static Map<Integer, Integer> filmToTotalAtores = new HashMap<>();





    public static boolean parseFiles(File folder) {
        generos.clear();
        atores.clear();
        filmes.clear();
        realizadores.clear();
        generosFilmes.clear();
        inputInvalidos.clear();


        // Verifica se a pasta é nula
        if (folder == null) {
            return false;
        }

        // Verifica se a pasta existe e é um diretório
        if (!folder.exists() || !folder.isDirectory()) {
            return false;
        }
        while(inputInvalidos.size() < 6) {
            inputInvalidos.add(new InputInvalido("movies.csv", "0", "0", "-1"));
            inputInvalidos.add(new InputInvalido("actors.csv", "0", "0","-1"));
            inputInvalidos.add(new InputInvalido("directors.csv", "0", "0", "-1"));
            inputInvalidos.add(new InputInvalido("genres.csv", "0", "0","-1"));
            inputInvalidos.add(new InputInvalido("genres_movies.csv", "0", "0", "-1"));
            inputInvalidos.add(new InputInvalido("movie_votes.csv", "0", "0", "-1"));
        }


        // Lista os arquivos na pasta
        File[] files = folder.listFiles();

        // Verifica se a lista de arquivos é nula ou vazia
        if (files == null || files.length == 0) {
            return false;
        }

        // Variável para rastrear se encontrou arquivos CSV
        boolean encontrouCSV = false;

        // Itera sobre todos os arquivos
        for (File file : files) {
            // Pula diretórios
            if (file.isDirectory()) {
                continue;
            }
            // Verifica se o arquivo é um CSV (case-insensitive)
            if (file.getName().toLowerCase().endsWith(".csv")) {
                encontrouCSV = true;

                // Tenta ler o conteúdo do arquivo
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    // Verifica se o arquivo não está vazio
                    if (reader.readLine() == null) {
                        continue; // Pula arquivos vazios
                    }
                    Scanner scanner = new Scanner(file);
                    if (scanner.hasNextLine()){
                        scanner.nextLine();

                        switch (file.getName()){
                            case "movies.csv":
                                parseMovies(scanner);
                                break;
                            case "actors.csv":
                                parseActors(scanner);
                                break;
                            case "directors.csv":
                                parseDirectors(scanner);
                                break;
                            case "genres_movies.csv":
                                parseMovieGenres(scanner);
                                break;
                            case "movie_votes.csv":
                                parseMovieVotes(scanner);
                                break;
                            case "genres.csv":
                                parseGenres(scanner);
                                break;


                        }


                        scanner.close();
                    }



                } catch (IOException e) {
                    // Em caso de erro de leitura, continua para o próximo arquivo



                }
            }
        }
        if (encontrouCSV){
            Filme.associar(filmes,
                    atores,
                    generosFilmes,
                    generos,
                    realizadores
            );

        }

        // Retorna true se encontrou pelo menos um arquivo CSV não vazio
        return encontrouCSV;
    }




    static void parseMovies(Scanner scanner) {
        parsingMethod(scanner, 0, 5, partes -> {
            int idFilme = Integer.parseInt(partes[0].trim());

            // Verifica se já existe um filme com este ID
            boolean idExiste = false;
            for (Filme filme : filmes) {
                if (filme.idFilme == idFilme) {
                    idExiste = true;
                    break;
                }
            }

            // Só adiciona se não existir um filme com este ID
            if (!idExiste) {
                filmes.add(new Filme(partes[0], partes[1], partes[2], partes[3], inverteData(partes[4])));
            }
        });
    }

    static void parseActors(Scanner scanner) {
        parsingMethod(scanner, 1, 4, partes -> {
            atores.add(new Ator(partes[0], partes[1], partes[2], partes[3]));
        });
    }

    static void parseDirectors(Scanner scanner) {
        parsingMethod(scanner, 2, 3, partes -> {
            realizadores.add(new Realizador(partes[0], partes[1], partes[2]));
        });
    }

    static void parseMovieGenres(Scanner scanner) {
        parsingMethod(scanner, 4, 2, partes -> {

            generosFilmes.add(new GenerosFilmes(partes[0].trim(), partes[1].trim()));
        });
    }

    static void parseMovieVotes(Scanner scanner) {
        parsingMethod(scanner, 5, 3, partes -> {
            votos.add(new Votacao(partes[0], partes[1], partes[2]));
        });
    }


    static void parseGenres(Scanner scanner) {
        parsingMethod(scanner, 3, 2, partes -> {
            generos.add(new Genero(partes[0], partes[1]));
        });
    }

    static void parsingMethod(Scanner scanner, int index, int expectedLength, java.util.function.Consumer<String[]> consumer) {
        int indexLine = 0;

        // Obter o objeto de input inválido correspondente
        InputInvalido input = inputInvalidos.get(index);
        input.linhasCertas = 0;
        input.linhasErradas = 0;
        input.primeiraLinha = -1;

        while (scanner.hasNextLine()) {
            indexLine++;
            String linha = scanner.nextLine().trim();

            // Ignorar linhas vazias
            if (linha.isEmpty()) {
                continue;
            }

            // Dividir a linha e remover espaços em branco manualmente
            String[] partes = linha.split(",");
            for (int i = 0; i < partes.length; i++) {
                partes[i] = partes[i].trim();
            }

            // Validar tamanho da linha
            if (partes.length == expectedLength) {
                consumer.accept(partes);
                input.linhasCertas++;
            } else {
                input.linhasErradas++;
                if (input.primeiraLinha == -1) {
                    input.primeiraLinha = indexLine;
                }
            }
        }
    }

    public static ArrayList<Object> getObjects(TipoEntidade tipo) {


        switch (tipo){
            case ATOR:
                if(!atores.isEmpty()){
                    return new ArrayList<>(atores);
                }
                break;
            case FILME:
                if(!filmes.isEmpty()){
                    Filme.associar(
                            filmes,
                            atores,
                            generosFilmes,
                            generos,
                            realizadores
                    );
                    return new ArrayList<>(filmes);
                }
                break;
            case GENERO_CINEMATOGRAFICO:
                if (!generos.isEmpty()){
                    return new ArrayList<>(generos);

                }
                break;
            case REALIZADOR:
                if(!realizadores.isEmpty()){
                    return new ArrayList<>(realizadores);

                }
                break;
            case INPUT_INVALIDO:
                if(!inputInvalidos.isEmpty()){
                    return new ArrayList<>(inputInvalidos);

                }
                break;


        }
        return new ArrayList<>();

    }
    public static Result execute(String command) {
        Result result = new Result();

        String[] params = command.split("");

        if (filmes == null || filmes.isEmpty()) {
            result.success = true;
            result.result = "Nenhum filme disponivel";
            return result;
        }
        switch (params[0].toUpperCase()) {  //  params[0] como base do comando
            case "HELP":
                result.success = true;
                result.result = "Comandos disponíveis:\n" +
                        "- COUNT_MOVIES_MONTH_YEAR <month> <year>\n" +
                        "- TOP_RATED_MOVIE_VOTES_YEAR <year>\n" +
                        "- TOP_MONTH_MOVIE_COUNT <month>\n" +
                        "- COUNT_ACTORS_IN_2_YEARS <year-1> <year-2>\n" +
                        "- GET_MOVIES_WITH_GENDER_BIAS \n" +
                        "- COUNT_ACTORS_BY_NAME <name>\n" +
                        "- INSERT_ACTOR <id>;<name>;<gender>;<movie-id>\n" +
                        "- INSERT_DIRECTOR <id>;<name>;<movie-id>\n" +
                        "- SHOW_MOVIE_LIST\n" +
                        "- HELP\n" +
                        "- QUIT\n";
                break;

            case "COUNT_MOVIES_MONTH_YEAR":
                if (params.length != 3) {
                    return new Result(true, null, "Erro");
                }

                try {
                    int mes = Integer.parseInt(params[1]);
                    int ano = Integer.parseInt(params[2]);

                    if (mes < 1 || mes > 12) {
                        return new Result(true, null, "Erro");
                    };

                    int count = 0;
                    for (Filme filme : filmes) {
                        if (filme.dataFilme == null || filme.dataFilme.equals("0000-00-00")) {
                            continue;
                        }
                        String[] data = filme.dataFilme.split("-");
                        if (Integer.parseInt(data[0]) == ano && Integer.parseInt(data[1]) == mes) {
                            count++;
                        }
                    }

                    return new Result(true, null, "Número de filmes lançados em " + String.format("%02d", mes) + "/" + ano + ": " + count);

                } catch (NumberFormatException e) {
                    return new Result(true, null, "Erro");
                }

        }




        return result;

    }



    static String inverteData(String data) {
        String[] partes = data.split("-");
        if (partes.length == 3) {
            return partes[2] + "-" + partes[1] + "-" + partes[0];
        }
        return data;
    }


    public static void main(String[] args) {

        System.out.println("Bem-vindo ao deisIMDB");
        long start = System.currentTimeMillis();
        boolean parseOk = parseFiles(new File("test-files"));
        if(!parseOk){
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }
        long end = System.currentTimeMillis();
        System.out.println("Ficheiro lido com sucesso em " + (end - start) + "ms");


        Result result = execute("HELP");
        System.out.println(result.result);

        Scanner in = new Scanner(System.in);
        String line;

        do{
            System.out.print("> ");
            line = in.nextLine();

            if(line != null && !line.equals("QUIT")){
                start = System.currentTimeMillis();
                result = execute(line);
                end = System.currentTimeMillis();

                if (!result.success){
                    System.out.println("Erro: " + result.error);
                } else {
                    System.out.println(result.result);
                    System.out.println("(demorou " + (end - start) + ")");
                }
            }
        }while (line != null && !line.equals("QUIT"));

        ArrayList<Object> inputsInvalidos = getObjects(TipoEntidade.INPUT_INVALIDO);
        System.out.println(inputsInvalidos.get(0));
        System.out.println(inputsInvalidos.get(1));
        System.out.println(inputsInvalidos.get(2));
        System.out.println(inputsInvalidos.get(3));
        System.out.println(inputsInvalidos.get(4));
        System.out.println(inputsInvalidos.get(5));

        System.out.println();
        System.out.println("Filmes:");

        ArrayList<Object> filmes = getObjects(TipoEntidade.FILME);
        for(Object filme : filmes){
            System.out.println(filme);
        }
    }

}