package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.codeimplementation.FormRendererService;
import ch.joth.games.pocketsimon.game.code.codeimplementation.GameBehaviourService;
import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameBehaviourTest {
    public GameBehaviourService gameBehaviour;

    public GameBehaviourService gameBehaviourMock;

    public FormRendererService formRenderer;
    public ArrayList<Integer> pattern;
    public Field ticksField;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {

        gameBehaviour = new GameBehaviourService();

        Field secureRandom = gameBehaviour.getClass().getDeclaredField("randomizer");
        secureRandom.setAccessible(true);
        secureRandom.set(gameBehaviour, new SecureRandom());

        Field patternField = gameBehaviour.getClass().getDeclaredField("pattern");
        patternField.setAccessible(true);
        pattern = (ArrayList<Integer>) patternField.get(gameBehaviour);

        ticksField = gameBehaviour.getClass().getDeclaredField("ticks");
        ticksField.setAccessible(true);


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
        assertEquals(0, (Integer) ticksField.get(gameBehaviour));
    }


    @Test
    void actionPerformed_incrementsTicks() throws IllegalAccessException {

        ticksField.set(gameBehaviour, 0);
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertEquals(1, (Integer) ticksField.get(gameBehaviour));
    }

    @Test
    void actionPerformed_decrementsDarkAfter20Ticks() throws IllegalAccessException, NoSuchFieldException {
        ticksField.set(gameBehaviour, 19);

        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertEquals(20, (Integer) ticksField.get(gameBehaviour));
    }

    @Test
    void actionPerformed_createsPatternWhenDarkIsZero() {
        gameBehaviour.createPattern = true;
        gameBehaviour.dark = 0;
        pattern.clear();
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertFalse(pattern.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void mousePressed_resetsGameWhenGameOver(Boolean gameOver) {
        gameBehaviour.gameOver = gameOver;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        if (!gameOver) {
            assertFalse(gameBehaviour.gameOver);
        } else {
            assertFalse(gameBehaviour.gameOver);
        }
    }


    @Test
    void mousePressed_correctPatternDoesNotEndGame() {
        pattern.add(1);
        gameBehaviour.flashed = 1;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressed_incorrectPatternEndsGame() {
        pattern.add(1);
        gameBehaviour.flashed = 2;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertNotEquals(pattern.get(0), gameBehaviour.flashed);
    }


    @Test
    void actionPerformedShouldNotCreatePatternWhenDarkIsNotZero() {
        gameBehaviour.createPattern = true;
        gameBehaviour.dark = 1;
        pattern.clear();
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertTrue(pattern.isEmpty());
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
        pattern.add(1);
        gameBehaviour.flashed = 2;
        gameBehaviour.createPattern = false;
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertTrue(gameBehaviour.gameOver);
    }

    @Test
    void mousePressedShouldNotEndGameWhenPatternIsCorrect() {
        pattern.add(1);
        gameBehaviour.flashed = 1;

        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }
}