package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.eConfigValues;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * IConfigService interface provides methods for handling configuration files.
 * It allows to get a BufferedReader for the configuration file and to get a value from the configuration file.
 */
public interface IConfigService {

    /**
     * Returns a BufferedReader for the configuration file.
     *
     * @return a BufferedReader for the configuration file
     * @throws IOException if an I/O error occurs
     */
    BufferedReader getConfigFileBuffer() throws IOException;

    /**
     * Returns the value associated with the provided key in the configuration file.
     *
     * @param key an instance of eConfigValues representing the key
     * @return a string representing the value associated with the key
     * @throws IOException if an I/O error occurs
     */
    String getValue(eConfigValues key ) throws IOException;

}