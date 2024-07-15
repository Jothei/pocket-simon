package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.codeimplementation.FormRendererService;
import ch.joth.games.pocketsimon.game.code.codeimplementation.GameBehaviourService;
import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameBehaviourTest {
    public GameBehaviourService gameBehaviour;

    public GameBehaviourService gameBehaviourMock;

    public FormRendererService formRenderer;

    @BeforeEach
    void setUp() {

        gameBehaviour = new GameBehaviourService();
        gameBehaviour.randomizer = new Random();
        gameBehaviourMock = mock(GameBehaviourService.class);
        formRenderer = new FormRendererService();
    }

    @ParameterizedTest
    @CsvSource({
            "SOUND_ON, COLOR_ON",
            "SOUND_OFF, COLOR_OFF",
            "SOUND_ON, COLOR_OFF",
            "SOUND_OFF, COLOR_ON"
    })
    void testStartGame(eSoundMode soundMode, eColorMode colorMode) {
        GameBehaviourService gameSpy = spy(gameBehaviour);
        doNothing().when(gameSpy).startGame();
        
        gameSpy.startGame(soundMode, colorMode);

        assertEquals(soundMode, gameSpy.getSoundMode());
        assertEquals(colorMode, gameSpy.getColorMode());


    }

    @Test
    void testInitGameVariables() throws NoSuchFieldException, IllegalAccessException {
        gameBehaviour.initGameVariables();
        Field indexPatternField = gameBehaviour.getClass().getDeclaredField("indexPattern");
        indexPatternField.setAccessible(true);
        assertEquals(0, indexPatternField.get(gameBehaviour));
        assertEquals(2, gameBehaviour.dark);
        assertEquals(0, gameBehaviour.flashed);
        assertEquals(0, gameBehaviour.ticks);
    }

    @Test
    void actionPerformedNoCreatePatternTest() {
        gameBehaviour.ticks = 20;
        gameBehaviour.flashed = 99;
        gameBehaviour.createPattern = false;
        gameBehaviour.dark = 10;


        assertEquals(20, gameBehaviour.ticks);
        assertEquals(99, gameBehaviour.flashed);
        assertEquals(10, gameBehaviour.dark);

    }


    @Test
    void actionPerformed_incrementsTicks() {
        gameBehaviour.ticks = 0;
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertEquals(1, gameBehaviour.ticks);
    }

    @Test
    void actionPerformed_decrementsDarkAfter20Ticks() {
        gameBehaviour.ticks = 19;
        gameBehaviour.dark = 2;
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertEquals(1, gameBehaviour.dark);
    }

    @Test
    void actionPerformed_createsPatternWhenDarkIsZero() {
        gameBehaviour.createPattern = true;
        gameBehaviour.dark = 0;
        gameBehaviour.pattern.clear();
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertFalse(gameBehaviour.pattern.isEmpty());
    }

    @Test
    void mousePressed_resetsGameWhenGameOver() {
        gameBehaviour.gameOver = true;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressed_doesNotResetGameWhenNotGameOver() {
        gameBehaviour.gameOver = false;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressed_correctPatternDoesNotEndGame() {
        gameBehaviour.pattern.add(1);
        gameBehaviour.flashed = 1;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressed_incorrectPatternEndsGame() {
        gameBehaviour.pattern.add(1);
        gameBehaviour.flashed = 2;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertNotEquals(gameBehaviour.pattern.get(0), gameBehaviour.flashed);
    }

    @Test
    void actionPerformedShouldCreatePatternWhenDarkIsZero() {
        gameBehaviour.createPattern = true;
        gameBehaviour.dark = 0;
        gameBehaviour.pattern.clear();
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertFalse(gameBehaviour.pattern.isEmpty());
    }

    @Test
    void actionPerformedShouldNotCreatePatternWhenDarkIsNotZero() {
        gameBehaviour.createPattern = true;
        gameBehaviour.dark = 1;
        gameBehaviour.pattern.clear();
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertTrue(gameBehaviour.pattern.isEmpty());
    }

    @Test
    void mousePressedShouldResetGameWhenGameOver() {
        gameBehaviour.gameOver = true;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressedShouldNotResetGameWhenNotGameOver() {
        gameBehaviour.gameOver = false;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressedShouldEndGameWhenPatternIsIncorrect() {
        gameBehaviour.pattern.add(1);
        gameBehaviour.flashed = 2;
        gameBehaviour.createPattern = false;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertTrue(gameBehaviour.gameOver);
    }

    @Test
    void mousePressedShouldNotEndGameWhenPatternIsCorrect() {
        gameBehaviour.pattern.add(1);
        gameBehaviour.flashed = 1;

        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }
}