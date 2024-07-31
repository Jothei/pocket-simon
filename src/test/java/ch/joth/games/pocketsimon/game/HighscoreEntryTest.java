package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighscoreEntryTest {

    private HighscoreEntry entry;

    @BeforeEach
    void setUp() {
        entry = new HighscoreEntry("Player1", 100);
    }

    @Test
    void constructor_setsNameAndScore() {
        assertEquals("Player1", entry.getName());
        assertEquals(100, entry.getScore());
    }

    @Test
    void constructor_setsCurrentDate() {
        assertNotNull(entry.getDate());
    }

    @Test
    void setName_updatesName() {
        entry.setName("Player2");
        assertEquals("Player2", entry.getName());
    }

    @Test
    void getName_returnsName() {
        assertEquals("Player1", entry.getName());
    }

    @Test
    void getScore_returnsScore() {
        assertEquals(100, entry.getScore());
    }

    @Test
    void getDate_returnsDate() {
        assertNotNull(entry.getDate());
    }

    @Test
    void defaultConstructor_initializesFields() {
        HighscoreEntry defaultEntry = new HighscoreEntry();
        assertNull(defaultEntry.getName());
        assertEquals(0, defaultEntry.getScore());
        assertNull(defaultEntry.getDate());
    }
}