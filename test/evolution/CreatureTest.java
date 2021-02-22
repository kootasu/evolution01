package evolution;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @org.junit.jupiter.api.Test
    void canMate() {
        Creature creature = new Creature(new Genome(10,10,10,10,10));
        Creature possibleMate = new Creature(new Genome(10,10,10,10,10));
        assertTrue(creature.canMate(possibleMate));
    }
}