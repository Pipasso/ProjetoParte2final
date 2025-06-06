package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestGenre {

    @Test
    void testGenreToString_1() {
        Genero genre = new Genero(1, "Action");
        Assertions.assertEquals("1 | Action", genre.toString());
    }

    @Test
    void testGenreToString_2() {
        Genero genre = new Genero(2, "Science Fiction");
        Assertions.assertEquals("2 | Science Fiction", genre.toString());
    }

    @Test
    void testGenreToString_3() {
        Genero genre = new Genero(9999, "Mystery");
        Assertions.assertEquals("9999 | Mystery", genre.toString());
    }

    @Test
    void testGenreToString_4() {
        Genero genre = new Genero(4, "Long Genre Name");
        Assertions.assertEquals("4 | Long Genre Name", genre.toString());
    }

    @Test
    void testGenreToString_5() {
        Genero genre = new Genero(5, "SF");
        Assertions.assertEquals("5 | SF", genre.toString());
    }
}