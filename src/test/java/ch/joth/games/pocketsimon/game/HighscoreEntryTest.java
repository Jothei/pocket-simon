package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.eColorMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighscoreEntryTest {

    private HighscoreEntry entry;

    @BeforeEach
    void setUp() {
        entry = new HighscoreEntry("Player1", 100, eColorMode.COLOR_ON);
    }

    @Test
    void constructorSetsNameAndScoreTest() {
        assertEquals("Player1", entry.getName());
        assertEquals(100, entry.getScore());
    }

    @Test
    void constructorSetsCurrentDateTest() {
        assertNotNull(entry.getDate());
    }

    @Test
    void setNameUpdatesNameTest() {
        entry.setName("Player2");
        assertEquals("Player2", entry.getName());
    }

    @Test
    void getNameReturnsNameTest() {
        assertEquals("Player1", entry.getName());
    }

    @Test
    void getScoreReturnsScoreTest() {
        assertEquals(100, entry.getScore());
    }

    @Test
    void getDateReturnsDateTest() {
        assertNotNull(entry.getDate());
    }

    @Test
    void defaultConstructorInitializesFieldsTest() {
        HighscoreEntry defaultEntry = new HighscoreEntry();
        assertNull(defaultEntry.getName());
        assertEquals(0, defaultEntry.getScore());
        assertNull(defaultEntry.getDate());
    }
}