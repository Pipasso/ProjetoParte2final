package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestMovies {

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

    /*@Test
    void testFilmeToString_4() {
        Filme filme = new Filme("5", "Oppenheimer", "180", "100000000", "2023-07-21");
        Assertions.assertEquals("5 | Oppenheimer | 2023-07-21 | 0", filme.toString());
    }*/

    @Test
    void testFilmeToString_5() {
        Filme filme = new Filme("100", "Interestelar", "169", "165000000", "2014-11-07");
        Assertions.assertEquals("100 | Interestelar | 2014-11-07 | 0", filme.toString());
    }
}
