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
    public Field darkField;
    public Field createPatternField;
    public Field flashedField;

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

        darkField = gameBehaviour.getClass().getDeclaredField("dark");
        darkField.setAccessible(true);

        createPatternField = gameBehaviour.getClass().getDeclaredField("createPattern");
        createPatternField.setAccessible(true);

        flashedField = gameBehaviour.getClass().getDeclaredField("flashed");
        flashedField.setAccessible(true);

        gameBehaviourMock = mock(GameBehaviourService.class);
        formRenderer = new FormRendererService();
    }

    @ParameterizedTest
    @CsvSource({"SOUND_ON, COLOR_ON", "SOUND_OFF, COLOR_OFF", "SOUND_ON, COLOR_OFF", "SOUND_OFF, COLOR_ON"})
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

        assertEquals(0, getFlashed());
        assertEquals(0, (Integer) ticksField.get(gameBehaviour));
    }


    @Test
    void actionPerformed_incrementsTicks() throws IllegalAccessException {

        ticksField.set(gameBehaviour, 0);
        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertEquals(1, (Integer) ticksField.get(gameBehaviour));
    }

    @Test
    void actionPerformed_decrementsDarkAfter20Ticks() throws IllegalAccessException {
        ticksField.set(gameBehaviour, 19);

        gameBehaviour.actionPerformed(new ActionEvent(this, 0, null));
        assertEquals(20, (Integer) ticksField.get(gameBehaviour));
    }

    @Test
    void actionPerformed_createsPatternWhenDarkIsZero() {
        this.setPattern(true);
        this.setDark(0);
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

        setFlashed(1);
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertFalse(gameBehaviour.gameOver);
    }

    @Test
    void mousePressed_incorrectPatternEndsGame() {
        pattern.add(1);
        setFlashed(2);
        gameBehaviour.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertNotEquals(pattern.get(0), getFlashed());
    }


    @Test
    void actionPerformedShouldNotCreatePatternWhenDarkIsNotZero() {
        this.setPattern(true);
        this.setDark(1);
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
        setFlashed(2);
        this.setPattern(false);
        GameBehaviourService gameSpy = spy(gameBehaviour);
        doNothing().when(gameSpy).initHighscoreInsertDialog(any(), anyInt());
        gameSpy.mousePressed(new MouseEvent(new Component() {
        }, 0, 0, 0, 0, 0, 0, false));
        assertTrue(gameSpy.gameOver);
    }

    private void setDark(Integer value) {
        darkField.setAccessible(true);
        try {
            darkField.set(gameBehaviour, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setPattern(boolean value) {
        try {
            this.createPatternField.set(gameBehaviour, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setFlashed(Integer value) {
        try {
            this.flashedField.set(gameBehaviour, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getFlashed() {
        try {
            return (Integer) this.flashedField.get(gameBehaviour);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}