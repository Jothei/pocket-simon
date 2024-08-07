package ch.joth.games.pocketsimon.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HighscoreModelTest {
    HighscoreModel highscoreModel;
    Field mapperField;
    Method saveToFile;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        highscoreModel = new HighscoreModel();
        this.mapperField = highscoreModel.getClass().getDeclaredField("mapper");
        this.mapperField.setAccessible(true);
        this.saveToFile = highscoreModel.getClass().getDeclaredMethod("saveToFile");
        saveToFile.setAccessible(true);
        this.setMapper((ObjectMapper) mapperField.get(highscoreModel));
    }

    @Test
    void saveTest() {
        assertDoesNotThrow(() -> saveToFile.invoke(highscoreModel));
    }

    @Test
    void saveFailCaseTest() throws NoSuchFieldException, IllegalAccessException {
        Field filenameField = highscoreModel.getClass().getDeclaredField("filePath");
        filenameField.setAccessible(true);
        filenameField.set(highscoreModel, null);
        Field entries = highscoreModel.getClass().getDeclaredField("entries");
        entries.setAccessible(true);
        entries.set(highscoreModel, null);

        assertDoesNotThrow(() -> saveToFile.invoke(highscoreModel));

    }

    @Test
    void addEntryTest() {

        highscoreModel.addEntry(new HighscoreEntry("TestUser_JUNIT", 100));
        assertNotNull(highscoreModel.getEntries());
    }


    @Test
    void constructorTest() throws IllegalAccessException {


        assertNotNull(this.mapperField.get(highscoreModel));
        assertNotNull(highscoreModel.getEntries());

    }

    private void setMapper(ObjectMapper mapper) throws IllegalAccessException {
        this.mapperField.set(highscoreModel, mapper);
    }


    @AfterAll
    public static void tearDown() {
        File leaderboardFile = new File("leaderboard.json");
        if (leaderboardFile.exists()) {
            leaderboardFile.delete();
            System.out.println("Deleted leaderboard.json");
        }
    }
}