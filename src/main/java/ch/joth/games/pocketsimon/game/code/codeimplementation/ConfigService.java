package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.codedefinition.IConfigService;
import ch.joth.games.pocketsimon.game.code.eConfigValues;
import org.apache.logging.log4j.Level;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * This class provides services related to configuration.
 * It implements the IConfigService interface.
 */
public class ConfigService implements IConfigService {

    /**
     * Default constructor for the ConfigService class.
     * It is used to create an instance of the ConfigService class.
     */
    public ConfigService() {
    }
    /**
     * This method returns a BufferedReader for the config.json file located in the resources folder.
     * If the file is not found, it logs an error and throws an IllegalArgumentException.
     *
     * @return A BufferedReader for the config.json file.
     */
    public BufferedReader getConfigFileBuffer() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json");
        if (inputStream == null) {
            new ServiceFactory().LoggingService().log("File not found in resources: config.json", Level.ERROR, this.getClass());
            throw new IllegalArgumentException("File not found in resources: config.json");
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * This method returns the value associated with the given key from the config.json file.
     * If an error occurs while reading the file, it logs the error and returns an empty string.
     *
     * @param key The key for which to return the associated value.
     * @return The value associated with the given key, or an empty string if an error occurs.
     */
    public String getValue(eConfigValues key) {
        String jsonStr = "";
        try {
            String json = new ServiceFactory().ConfigService().getConfigFileBuffer().lines().collect(Collectors.joining());
            jsonStr = new JSONObject(json).getString(key.name());
            return jsonStr;
        } catch (Exception e) {
            new ServiceFactory().LoggingService().log("Error reading config file Key: " + key.name(), Level.ERROR, this.getClass());
        }
        return jsonStr;
    }
}