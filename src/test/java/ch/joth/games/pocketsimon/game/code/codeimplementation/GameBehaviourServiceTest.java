package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameBehaviourServiceTest {
    GameBehaviourService gameBehaviourService;
    ServiceFactory serviceMock;
    ServiceFactory service;
    GameBehaviourService gameMock;
    ArrayList<Integer> pattern;
    Field createPatternField;
    Field flashedField;
    Field frameField;
    Field colorModeField;
    Field buttonCountField;
    Field timerField;
    Field ticksField;
    Field rendererField;
    Field darkField;
    Field indexPatternField;
    Field patternField;
    Method regularMouseEventMethod;

    @BeforeEach
    void setUp() throws NoSuchFieldException, NoSuchMethodException {

        gameBehaviourService = new GameBehaviourService();
        gameMock = spy(gameBehaviourService);
        service = new ServiceFactory();


        serviceMock = mock(service.getClass());
        when(serviceMock.ConfigService()).thenReturn(new ConfigService());
        when(serviceMock.LoggingService()).thenReturn(new LoggingService());

        setupFieldsAndMocks();

    }

    private void setupFieldsAndMocks() throws NoSuchFieldException, NoSuchMethodException {
        colorModeField = gameMock.getClass().getDeclaredField("colorMode");
        colorModeField.setAccessible(true);

        ticksField = gameBehaviourService.getClass().getDeclaredField("ticks");
        ticksField.setAccessible(true);

        flashedField = gameBehaviourService.getClass().getDeclaredField("flashed");
        flashedField.setAccessible(true);

        createPatternField = gameBehaviourService.getClass().getDeclaredField("createPattern");
        createPatternField.setAccessible(true);

        frameField = gameBehaviourService.getClass().getDeclaredField("gameFrame");
        frameField.setAccessible(true);

        buttonCountField = gameBehaviourService.getClass().getDeclaredField("buttonCount");
        buttonCountField.setAccessible(true);

        timerField = gameBehaviourService.getClass().getDeclaredField("timer");
        timerField.setAccessible(true);

        rendererField = gameMock.getClass().getDeclaredField("renderer");
        rendererField.setAccessible(true);

        darkField = gameBehaviourService.getClass().getDeclaredField("dark");
        darkField.setAccessible(true);

        indexPatternField = gameBehaviourService.getClass().getDeclaredField("indexPattern");
        indexPatternField.setAccessible(true);
        regularMouseEventMethod = gameBehaviourService.getClass().getDeclaredMethod("regularMousePressEvent", MouseEvent.class);
        regularMouseEventMethod.setAccessible(true);

        patternField = gameBehaviourService.getClass().getDeclaredField("pattern");
        patternField.setAccessible(true);


    }

    @Test
    void startGameWithConstructorTest() throws NoSuchFieldException, IllegalAccessException {
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
    void startGameWithConstructor2Test() throws NoSuchFieldException, IllegalAccessException {
        Field soundMode = gameMock.getClass().getDeclaredField("soundMode");
        soundMode.setAccessible(true);
        Field colorMode = gameMock.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);
        doNothing().when(gameMock).startGame();
        gameMock.startGame(eSoundMode.SOUND_ON, eColorMode.COLOR_OFF);
        assertEquals(eSoundMode.SOUND_ON, soundMode.get(gameMock));
        assertEquals(eColorMode.COLOR_OFF, colorMode.get(gameMock));
    }

    @ParameterizedTest
    @MethodSource("provideEnums")
    void startGameTest(eColorMode color, eSoundMode sound) throws NoSuchFieldException, IllegalAccessException {
        Field soundMode = gameMock.getClass().getDeclaredField("soundMode");
        soundMode.setAccessible(true);
        soundMode.set(gameMock, sound);
        Field colorMode = gameMock.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);
        colorMode.set(gameMock, color);
        doNothing().when(gameMock).actionPerformed(any());
        doNothing().when(gameMock).initGameVariables();

        gameMock.startGame();


        assertNotNull(GameBehaviourService.gameBehaviour);
        assertEquals(sound, soundMode.get(gameMock));
        assertEquals(color, colorMode.get(gameMock));

    }

    static Stream<Arguments> provideEnums() {
        return Arrays.stream(eColorMode.values()).flatMap(color -> Arrays.stream(eSoundMode.values()).map(sound -> Arguments.of(color, sound)));
    }

    @Test
    void mousePressedTest() {
        MouseEvent e = mock(MouseEvent.class);
        this.setPattern(false);
        gameBehaviourService.setGameOver(true);


        when(e.getX()).thenReturn(1);
        when(e.getY()).thenReturn(1);
        gameBehaviourService.mousePressed(e);

        verify(e, times(1)).getX();
        verify(e, times(1)).getY();
        assertFalse(gameBehaviourService.isGameOver());


    }

    @Test
    void mousePressedGreenButtonTest() throws IllegalAccessException {
        MouseEvent e = mock(MouseEvent.class);
        this.setPattern(false);
        gameBehaviourService.setGameOver(false);
        pattern = new ArrayList<>(1);
        this.setFlashed(0);
        when(e.getX()).thenReturn(1);
        when(e.getY()).thenReturn(1);

        assertEquals(0, getFlashed());
        assertEquals(0, (int) ticksField.get(gameBehaviourService));


    }

    @ParameterizedTest
    @EnumSource(eColorMode.class)
    void paintTest(eColorMode value) throws NoSuchFieldException, IllegalAccessException {
        Graphics2D g = mock(Graphics2D.class);
        Field colorMode = gameBehaviourService.getClass().getDeclaredField("colorMode");
        colorMode.setAccessible(true);
        colorMode.set(gameBehaviourService, value);
        Field buttonPanelField = gameBehaviourService.getClass().getDeclaredField("buttonPanel");
        buttonPanelField.setAccessible(true);

        buttonPanelField.set(gameBehaviourService, new JPanel());
        gameBehaviourService.paint(g);

        verify(g, times(1)).setRenderingHint(any(), any());
        verify(g, times(1)).drawString(anyString(), anyInt(), anyInt());

        if (eColorMode.COLOR_MULTI_BUTTONS == value) {
            verify(g, never()).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
        } else {
            verify(g, times(2)).drawOval(anyInt(), anyInt(), anyInt(), anyInt());
            verify(g, times(8)).setColor(any(Color.class));
            verify(g, times(4)).fillRect(anyInt(), anyInt(), anyInt(), anyInt());
            verify(g, times(2)).setStroke(any());
            verify(g, times(1)).setFont(any());
        }

    }


    private void setPattern(boolean value) {
        try {
            this.createPatternField.set(gameBehaviourService, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setFlashed(Integer value) {
        try {
            this.flashedField.set(gameBehaviourService, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer getFlashed() {
        try {
            return (Integer) this.flashedField.get(gameBehaviourService);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @EnumSource(eColorMode.class)
    void startGameSetsColorAndSoundModesTest(eColorMode colorMode) {
        eSoundMode soundMode = eSoundMode.SOUND_ON;
        Graphics2D gMock = mock(Graphics2D.class);
        doNothing().when(gameMock).startGame();
        doNothing().when(gameMock).startMultiButtonGame();
        doNothing().when(gameMock).addLayout(gMock);

        gameMock.startGame(soundMode, colorMode);

        assertEquals(colorMode, gameMock.getColorMode());
        assertEquals(soundMode, gameMock.getSoundMode());
    }

    @Test
    void startGameCallsCreateAndShowGUIWhenColorModeIsMultiButtonsTest() {
        eSoundMode soundMode = eSoundMode.SOUND_ON;
        eColorMode colorMode = eColorMode.COLOR_MULTI_BUTTONS;
        doNothing().when(gameMock).startMultiButtonGame();
        gameMock.startGame(soundMode, colorMode);
        verify(gameMock, times(1)).startMultiButtonGame();
    }

    @Test
    void startGameCallsStartGameWhenColorModeIsNotMultiButtonsTest() {
        eSoundMode soundMode = eSoundMode.SOUND_ON;
        eColorMode colorMode = eColorMode.COLOR_ON;
        doNothing().when(gameMock).startGame();
        gameMock.startGame(soundMode, colorMode);
        verify(gameMock, times(1)).startGame();
    }

    @Test
    void paintCallsRendererRepaintTest() throws IllegalAccessException, NoSuchFieldException {
        ActionEvent actionMock = mock(ActionEvent.class);
        FormRendererService rendererMock = mock(FormRendererService.class);
        rendererField = gameMock.getClass().getDeclaredField("renderer");
        rendererField.setAccessible(true);
        rendererField.set(gameMock, rendererMock);

        gameMock.actionPerformed(actionMock);

        verify(gameMock, times(1)).actionPerformed(actionMock);
        verify(rendererMock, times(1)).repaint();
    }

    @Test
    void setColorMultiButtonThrowsTest() throws IllegalAccessException {
        JButton buttonMock = mock(JButton.class);

        this.colorModeField.set(gameMock, eColorMode.COLOR_ON);

        assertThrows(IllegalArgumentException.class, () -> gameMock.setColor(buttonMock));
    }

    @Test
    void setColorMultiButtonDoesentThrowTest() throws IllegalAccessException {
        JButton buttonMock = spy(JButton.class);
        buttonMock.setText("1");

        this.flashedField.set(gameMock, 0);
        this.colorModeField.set(gameMock, eColorMode.COLOR_MULTI_BUTTONS);

        assertDoesNotThrow(() -> gameMock.setColor(buttonMock));
    }

    @SuppressWarnings("unchecked")
    @Test
    void setupGameLogicTestSimonColorModeTest() throws IllegalAccessException {

        GameBehaviourService gameBehaviourServiceSpy = spy(gameBehaviourService);
        ActionEvent actionMock = mock(ActionEvent.class);


        gameBehaviourServiceSpy.startGame(eSoundMode.SOUND_OFF, eColorMode.COLOR_ON);
        darkField.set(gameBehaviourServiceSpy, 0);
        gameBehaviourServiceSpy.actionPerformed(actionMock);


        verify(gameBehaviourServiceSpy, times(1)).startGame(eSoundMode.SOUND_OFF, eColorMode.COLOR_ON);
        verify(gameBehaviourServiceSpy, times(1)).startGame();
        ArrayList<Integer> patternRes = (ArrayList<Integer>) patternField.get(gameBehaviourServiceSpy);
        assertEquals(1, patternRes.size());
        assertEquals(4, this.buttonCountField.get(gameBehaviourServiceSpy));
        assertFalse((boolean) this.createPatternField.get(gameBehaviourServiceSpy));
    }

    @SuppressWarnings("unchecked")
    @Test
    void GameLogicTestSimonColorModeTest() throws IllegalAccessException, InvocationTargetException {
        GameBehaviourService gameBehaviourServiceSpy = spy(gameBehaviourService);
        ActionEvent actionMock = mock(ActionEvent.class);
        MouseEvent mouseEventMock = mock(MouseEvent.class);
        ArrayList<Integer> patternStub = new ArrayList<>();

        when(mouseEventMock.getX()).thenReturn(10);
        when(mouseEventMock.getY()).thenReturn(10);
        gameBehaviourServiceSpy.startGame(eSoundMode.SOUND_OFF, eColorMode.COLOR_ON);
        darkField.set(gameBehaviourServiceSpy, 0);
        gameBehaviourServiceSpy.actionPerformed(actionMock);
        flashedField.set(gameBehaviourServiceSpy, 1);

        pattern = (ArrayList<Integer>) patternField.get(gameBehaviourServiceSpy);
        patternStub.add(1);
        patternField.set(gameBehaviourServiceSpy, patternStub);
        regularMouseEventMethod.invoke(gameBehaviourServiceSpy, mouseEventMock);
        pattern = (ArrayList<Integer>) patternField.get(gameBehaviourServiceSpy);
        assertEquals(1, pattern.size());

        gameBehaviourServiceSpy.actionPerformed(actionMock);
        pattern = (ArrayList<Integer>) patternField.get(gameBehaviourServiceSpy);
        patternStub.add(1);
        patternField.set(gameBehaviourServiceSpy, patternStub);
        regularMouseEventMethod.invoke(gameBehaviourServiceSpy, mouseEventMock);

        pattern = (ArrayList<Integer>) patternField.get(gameBehaviourServiceSpy);
        assertEquals(2, pattern.size());


    }
}