package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.codedefinition.IConfigService;
import ch.joth.games.pocketsimon.game.code.eConfigValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ConfigServiceTest {
    private ConfigService configService;
    private IConfigService mockConfigService;

    @BeforeEach
    void setUp() {
        ServiceFactory serviceFactory = new ServiceFactory();
        configService = serviceFactory.ConfigService();
        mockConfigService = Mockito.mock(IConfigService.class);
    }

    @Test
    void getConfigFileBuffer_returnsBufferedReaderForValidFile() {
        BufferedReader reader = configService.getConfigFileBuffer();
        assertEquals(BufferedReader.class, reader.getClass());
    }

    @Test
    void testGetFileBuffer_returnsBufferedReaderForValidFile() {
        assertDoesNotThrow(() -> configService.getConfigFileBuffer());

    }


    @Test
    void getValue_returnsValueForValidKey() throws IOException {
        String json = "{\"GAME_TITLE\":\"Pocket Simon\"}";
        BufferedReader reader = new BufferedReader(new StringReader(json));
        when(mockConfigService.getConfigFileBuffer()).thenReturn(reader);
        assertEquals("Pocket Simon", configService.getValue(eConfigValues.GAME_TITLE));
    }


}