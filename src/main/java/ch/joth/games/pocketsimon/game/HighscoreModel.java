package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * HighscoreModel is a class that manages the highscore entries for the Pocket Simon game.
 * It provides methods to add, retrieve, save, and load highscore entries.
 */
public class HighscoreModel {

    /**
     * List of highscore entries.
     */
    private List<HighscoreEntry> entries;
    /**
     * Filename of the highscore JSON File.
     */
    private static String filePath = "leaderboard.json";
    /**
     * ObjectMapper to serialize and deserialize the highscore entries.
     */
    private final ObjectMapper mapper;

    /**
     * Constructs a new HighscoreModel.
     * Initializes the entries list and ObjectMapper, and loads the highscores from the file.
     */
    public HighscoreModel() {
        entries = new ArrayList<>();
        mapper = new ObjectMapper();
        loadFromFile();
    }

    /**
     * Adds a new highscore entry to the list and saves the updated list to the file.
     *
     * @param entry the HighscoreEntry to be added
     */
    public void addEntry(HighscoreEntry entry) {
        entries.add(entry);
        entries.sort(Comparator.comparingInt(HighscoreEntry::getScore).reversed());
        saveToFile();
    }

    /**
     * Retrieves the list of highscore entries.
     *
     * @return a list of HighscoreEntry objects
     */
    public List<HighscoreEntry> getEntries() {
        return entries;
    }

    /**
     * Saves the list of highscore entries to the file.
     * Logs an error message if an IOException occurs.
     */
    private void saveToFile() {
        try {
            mapper.writeValue(new File(filePath), entries);
        } catch (IOException | NullPointerException e) {
            new ServiceFactory().LoggingService().log(e.getMessage(), Level.ERROR, HighscoreModel.class);
        }
    }

    /**
     * Loads the list of highscore entries from the file.
     * Logs an error message if an IOException occurs.
     */
    private void loadFromFile() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                entries = mapper.readValue(file, new TypeReference<>() {
                });
            }
        } catch (IOException e) {
            new ServiceFactory().LoggingService().log(e.getMessage(), Level.ERROR, HighscoreModel.class);
        }
    }
}