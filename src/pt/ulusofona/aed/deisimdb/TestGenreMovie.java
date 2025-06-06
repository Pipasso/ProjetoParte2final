package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestGenreMovie {

    @Test
    void testGenerosFilmesToString_1() {
        GenerosFilmes generosFilmes = new GenerosFilmes("1", "100");
        Assertions.assertEquals("1 | 100", generosFilmes.toString());
    }

    @Test
    void testGenerosFilmesToString_2() {
        GenerosFilmes generosFilmes = new GenerosFilmes("2", "200");
        Assertions.assertEquals("2 | 200", generosFilmes.toString());
    }

    @Test
    void testGenerosFilmesToString_3() {
        GenerosFilmes generosFilmes = new GenerosFilmes("10", "999999");
        Assertions.assertEquals("10 | 999999", generosFilmes.toString());
    }

    @Test
    void testGenerosFilmesToString_4() {
        GenerosFilmes generosFilmes = new GenerosFilmes("5", "500");
        Assertions.assertEquals("5 | 500", generosFilmes.toString());
    }

    @Test
    void testGenerosFilmesToString_5() {
        GenerosFilmes generosFilmes = new GenerosFilmes("100", "10000");
        Assertions.assertEquals("100 | 10000", generosFilmes.toString());
    }
}
