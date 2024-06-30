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
        assertDoesNotThrow( () -> audioService.playSound(eSoundFile.Green));
        assertDoesNotThrow( () -> audioService.playSound(eSoundFile.Yellow));
        assertDoesNotThrow( () -> audioService.playSound(eSoundFile.Blue));
        assertDoesNotThrow( () -> audioService.playSound(eSoundFile.Red));

    }
}