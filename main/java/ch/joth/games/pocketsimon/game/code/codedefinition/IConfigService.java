package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.eConfigValues;

import java.io.BufferedReader;
import java.io.IOException;

public interface IConfigService {
    BufferedReader getConfigFileBuffer() throws IOException;
    String getValue(eConfigValues key ) throws IOException;

}
