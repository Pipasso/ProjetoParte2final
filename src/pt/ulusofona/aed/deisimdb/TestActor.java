package pt.ulusofona.aed.deisimdb;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TestActor {

    @Test
    void testActorToString_1() {
        Ator actor = new Ator(1, "Micheal Jackson", 'M', 1000);
        Assertions.assertEquals("1 | Micheal Jackson | Masculino | 1000", actor.toString());
    }

    @Test
    void testActorToString_2() {
        Ator actor = new Ator(2, "Alberto Caeiro", 'M', 200);
        Assertions.assertEquals("2 | Alberto Caeiro | Masculino | 200", actor.toString());
    }

    @Test
    void testActorToString_3() {
        Ator actor = new Ator(3, "Robert De Niro", 'M', 350);
        Assertions.assertEquals("3 | Robert De Niro | Masculino | 350", actor.toString());
    }

    @Test
    void testActorToString_4() {
        Ator actor = new Ator(9999, "Ator com id Grande", 'M', 150);
        Assertions.assertEquals("9999 | Ator com id Grande | Masculino | 150", actor.toString());
    }

    @Test
    void testActorToString_5() {
        Ator actor = new Ator(5, "Ator com nome grande", 'F', 250);
        Assertions.assertEquals("5 | Ator com nome grande | Feminino | 250", actor.toString());
    }
}