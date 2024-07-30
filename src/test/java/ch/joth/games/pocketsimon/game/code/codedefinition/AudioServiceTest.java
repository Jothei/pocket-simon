package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.codeimplementation.AudioService;
import ch.joth.games.pocketsimon.game.code.eSoundFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AudioServiceTest {
    private AudioService audioService;


    @BeforeEach
    void setUp() {
        this.audioService = new AudioService();
    }


    @Test
    void playSoundFileTest() {
        assertDoesNotThrow(() -> audioService.playSound(eSoundFile.GREEN));
        assertDoesNotThrow(() -> audioService.playSound(eSoundFile.YELLOW));
        assertDoesNotThrow(() -> audioService.playSound(eSoundFile.BLUE));
        assertDoesNotThrow(() -> audioService.playSound(eSoundFile.RED));

    }
}