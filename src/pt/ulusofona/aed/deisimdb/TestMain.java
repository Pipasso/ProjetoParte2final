package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.util.ArrayList;

class TestFilme {

    @Test
    void testFilmeToString_1() {
        Filme filme = new Filme("1", "Matrix", "136.5", "63000000", "1999-03-31");
        Assertions.assertEquals("1 | Matrix | 1999-03-31 | 0", filme.toString());
    }

    @Test
    void testFilmeToString_2() {
        Filme filme = new Filme("2", "Inception", "148", "160000000", "2010-07-16");
        Assertions.assertEquals("2 | Inception | 2010-07-16 | 0", filme.toString());
    }

    @Test
    void testFilmeToString_3() {
        Filme filme = new Filme("9999", "Avatar", "162", "237000000", "2009-12-18");
        Assertions.assertEquals("9999 | Avatar | 2009-12-18", filme.toString());
    }

    @Test
    void testFilmeToString_4() {
        Main.atores = new ArrayList<>();
        Filme filme = new Filme("5", "Oppenheimer", "180", "100000000", "2023-07-21");
        Assertions.assertEquals("5 | Oppenheimer | 2023-07-21 | 0", filme.toString());
    }

    @Test
    void testFilmeToString_5() {
        Filme filme = new Filme("100", "Interestelar", "169", "165000000", "2014-11-07");
        Assertions.assertEquals("100 | Interestelar | 2014-11-07 | 0", filme.toString());
    }
}

class TestInputInvalidos {
    @Test
    void testInputInvalidoToString() {
        InputInvalido input = new InputInvalido("file.txt", "10", "2", "5");
        Assertions.assertEquals("file.txt | 10 | 2 | 5", input.toString());
    }
}

class TestGenerosFilmes {
    @Test
    void testGenerosFilmesToString() {
        GenerosFilmes generoFilme = new GenerosFilmes("3", "101");
        Assertions.assertEquals("3 | 101", generoFilme.toString());
    }
}

class TestGeneroToString {

    @BeforeEach
    void setUp() {
        Main.generos = new ArrayList<>();
    }

    @Test
    void testGeneroToStringBasico() {
        Genero genero = new Genero("1", "Ação");
        Assertions.assertEquals("1 | Ação", genero.toString());
    }

    @Test
    void testGeneroToStringComEspacos() {
        Genero genero = new Genero("2", "Ficção Científica");
        Assertions.assertEquals("2 | Ficção Científica", genero.toString());
    }
}

class TestGetObjectsGenero {

    @BeforeEach
    void setUp() {
        Main.generos = new ArrayList<>();
    }

    @Test
    void testGetObjectsGeneroDoisElementos() {
        Main.generos.add(new Genero("1", "Ação"));
        Main.generos.add(new Genero("2", "Comédia"));
        ArrayList<Object> resultado = Main.getObjects(TipoEntidade.GENERO_CINEMATOGRAFICO);
        Assertions.assertEquals(2, resultado.size(), "Deve retornar 2 gêneros");
    }
}

class TestParseFilesGenero {

    @Test
    void testParseFilesGeneroSemErros() {
        boolean resultado = Main.parseFiles(new File("test-files"));
        Assertions.assertTrue(resultado, "ParseFiles deve retornar true para arquivos sem erros");
    }
}






