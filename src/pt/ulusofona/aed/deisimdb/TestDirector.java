package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestDirector {

    @Test
    void testDirectorToString_1() {
        Realizador director = new Realizador(1, "Steven Spielberg", 100);
        Assertions.assertEquals("1 | Steven Spielberg | 100", director.toString());
    }

    @Test
    void testDirectorToString_2() {
        Realizador director = new Realizador(2, "Francis Ford Coppola", 200);
        Assertions.assertEquals("2 | Francis Ford Coppola | 200", director.toString());
    }

    @Test
    void testDirectorToString_3() {
        Realizador director = new Realizador(9999, "Big ID Director", 300);
        Assertions.assertEquals("9999 | Big ID Director | 300", director.toString());
    }

    @Test
    void testDirectorToString_4() {
        Realizador director = new Realizador(4, "Long Name Director", 400);
        Assertions.assertEquals("4 | Long Name Director | 400", director.toString());
    }

    @Test
    void testDirectorToString_5() {
        Realizador director = new Realizador(500, "Same ID Director", 500);
        Assertions.assertEquals("500 | Same ID Director | 500", director.toString());
    }
}