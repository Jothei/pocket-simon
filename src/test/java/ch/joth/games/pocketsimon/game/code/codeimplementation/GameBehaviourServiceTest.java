package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GameBehaviourServiceTest {
    GameBehaviourService gameBehaviourService;

    @BeforeEach
    void setUp() {
        gameBehaviourService = new GameBehaviourService();
    }

    @Test
    @Disabled
    @DisplayName("Test is Work in Progress")
    void startGameWithConstructor() throws NoSuchFieldException, IllegalAccessException {
        Field soundMode = gameBehaviourService.getClass().getDeclaredField("soundMode");
        soundMode.setAccessible(true);
        Field colorMode = gameBehaviourService.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);

        gameBehaviourService.startGame(eSoundMode.SOUND_ON, eColorMode.COLOR_ON);
        assertEquals(eSoundMode.SOUND_ON, soundMode.get(gameBehaviourService));
        assertEquals(eColorMode.COLOR_ON, colorMode.get(gameBehaviourService));

    }

    @Test
    @Disabled
    @DisplayName("Test is Work in Progress")
    void startGame() throws NoSuchFieldException, IllegalAccessException {
        Field soundMode = gameBehaviourService.getClass().getDeclaredField("soundMode");
        soundMode.setAccessible(true);
        Field colorMode = gameBehaviourService.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);

        gameBehaviourService.startGame(eSoundMode.SOUND_ON, eColorMode.COLOR_ON);
        assertEquals(eSoundMode.SOUND_ON, soundMode.get(gameBehaviourService));
        assertEquals(eColorMode.COLOR_ON, colorMode.get(gameBehaviourService));

    }

    @Test
    void exitGame() {
    }

    @Test
    void testStartGame() {
    }

    @Test
    void initGameVariables() {
    }

    @Test
    void actionPerformed() {
    }

    @Test
    void mousePressed() {
        MouseEvent e = mock(MouseEvent.class);
        gameBehaviourService.createPattern = false;
        gameBehaviourService.gameOver = true;

        gameBehaviourService.mousePressed(e);

        verify(e, times(1)).getX();
        verify(e, times(1)).getY();
        assertFalse(gameBehaviourService.gameOver);


    }

    @Test
    @Disabled
    @DisplayName("Test is Work in Progress")
    void mousePressedGreenButton() {
        MouseEvent e = mock(MouseEvent.class);
        gameBehaviourService.createPattern = false;
        gameBehaviourService.gameOver = false;

        when(e.getX()).thenReturn(1);
        when(e.getY()).thenReturn(1);

        gameBehaviourService.mousePressed(e);
        assertEquals(1, gameBehaviourService.flashed);
        assertEquals(1, gameBehaviourService.ticks);


    }


    @Test
    void paint() {
        Graphics2D g = mock(Graphics2D.class);
        doNothing().when(g).drawString(anyString(), anyInt(), anyInt());
        gameBehaviourService.paint(g);
    }


}