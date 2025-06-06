package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestInputInvalido {

    @Test
    void testInputInvalidoToString_1() {
        InputInvalido input = new InputInvalido("ficheiro1.txt", "10", "2", "5");
        Assertions.assertEquals("ficheiro1.txt | 10 | 2 | 5", input.toString());
    }

    @Test
    void testInputInvalidoToString_2() {
        InputInvalido input = new InputInvalido("dados.csv", "20", "4", "3");
        Assertions.assertEquals("dados.csv | 20 | 4 | 3", input.toString());
    }

    @Test
    void testInputInvalidoToString_3() {
        InputInvalido input = new InputInvalido("log.txt", "0", "1", "1");
        Assertions.assertEquals("log.txt | 0 | 1 | 1", input.toString());
    }

    @Test
    void testInputInvalidoToString_4() {
        InputInvalido input = new InputInvalido("empty.txt", "0", "0", "0");
        Assertions.assertEquals("empty.txt | 0 | 0 | 0", input.toString());
    }

    @Test
    void testInputInvalidoToString_5() {
        InputInvalido input = new InputInvalido("largefile.dat", "1000", "50", "10");
        Assertions.assertEquals("largefile.dat | 1000 | 50 | 10", input.toString());
    }
}
