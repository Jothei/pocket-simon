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

public class HighscoreModel {

    private List<HighscoreEntry> entries;

    private static final String FILE_PATH = "leaderboard.json";

    private final ObjectMapper mapper;

    public HighscoreModel() {

        entries = new ArrayList<>();

        mapper = new ObjectMapper();

        loadFromFile();

    }

    public void addEntry(HighscoreEntry entry) {

        entries.add(entry);

        entries.sort(Comparator.comparingInt(HighscoreEntry::getScore).reversed());

        saveToFile();

    }

    public List<HighscoreEntry> getEntries() {

        return entries;

    }

    private void saveToFile() {

        try {

            mapper.writeValue(new File(FILE_PATH), entries);

        } catch (IOException e) {

            new ServiceFactory().LoggingService().log(e.getMessage(), Level.ERROR, HighscoreModel.class);

        }

    }

    private void loadFromFile() {

        try {

            File file = new File(FILE_PATH);

            if (file.exists()) {

                entries = mapper.readValue(file, new TypeReference<>() {

                });

            }

        } catch (IOException e) {
            new ServiceFactory().LoggingService().log(e.getMessage(), Level.ERROR, HighscoreModel.class);

        }

    }

}