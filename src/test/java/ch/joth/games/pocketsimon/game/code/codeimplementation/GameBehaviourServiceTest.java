package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GameBehaviourServiceTest {
    GameBehaviourService gameBehaviourService;
    ServiceFactory serviceMock;
    ServiceFactory service;
    GameBehaviourService gameMock;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        gameBehaviourService = new GameBehaviourService();
        gameMock = spy(gameBehaviourService);

        service = new ServiceFactory();
        serviceMock = mock(service.getClass());
        when(serviceMock.ConfigService()).thenReturn(new ConfigService());
        when(serviceMock.LoggingService()).thenReturn(new LoggingService());
        Field factory = gameBehaviourService.getClass().getDeclaredField("service");
        factory.setAccessible(true);
        factory.set(gameBehaviourService, serviceMock);
    }

    @Test
    void startGameWithConstructor() throws NoSuchFieldException, IllegalAccessException {
        Field soundMode = gameMock.getClass().getDeclaredField("soundMode");
        soundMode.setAccessible(true);
        Field colorMode = gameMock.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);
        doNothing().when(gameMock).startGame();
        gameMock.startGame(eSoundMode.SOUND_OFF, eColorMode.COLOR_ON);

        assertEquals(eSoundMode.SOUND_OFF, soundMode.get(gameMock));
        assertEquals(eColorMode.COLOR_ON, colorMode.get(gameMock));

    }

    @Test
    void startGame() throws NoSuchFieldException, IllegalAccessException {
        Field soundMode = gameMock.getClass().getDeclaredField("soundMode");
        soundMode.setAccessible(true);
        Field colorMode = gameMock.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);
        doNothing().when(gameMock).startGame();

        gameMock.startGame();

        assertEquals(eSoundMode.SOUND_ON, soundMode.get(gameMock));
        assertNotEquals(eColorMode.COLOR_OFF, colorMode.get(gameMock));

    }


    @Test
    void mousePressed() {
        MouseEvent e = mock(MouseEvent.class);
        gameBehaviourService.createPattern = false;
        gameBehaviourService.gameOver = true;


        when(e.getX()).thenReturn(1);
        when(e.getY()).thenReturn(1);
        gameBehaviourService.mousePressed(e);

        verify(e, times(1)).getX();
        verify(e, times(1)).getY();
        assertFalse(gameBehaviourService.gameOver);


    }

    @Test
    void mousePressedGreenButton() {
        MouseEvent e = mock(MouseEvent.class);
        gameBehaviourService.createPattern = false;
        gameBehaviourService.gameOver = false;
        gameBehaviourService.pattern = new ArrayList<>(1);
        gameBehaviourService.flashed = 0;

        when(e.getX()).thenReturn(1);
        when(e.getY()).thenReturn(1);

        assertEquals(0, gameBehaviourService.flashed);
        assertEquals(0, gameBehaviourService.ticks);


    }


    @Test
    void paint() {
        Graphics2D g = mock(Graphics2D.class);
        doNothing().when(g).drawString(anyString(), anyInt(), anyInt());
        gameBehaviourService.paint(g);
    }


}