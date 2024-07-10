package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameSettingsModelTest {
    @Mock
    ServiceFactory serviceFactoryMock;
    @Mock
    ButtonGroup soundModeGroupMock;
    @Mock
    ButtonGroup colorModeGroupMock;
    @Mock
    GameSettingsView viewMock;
    @Mock
    GameSettingsModel modelMock;
    GameSettingsModel model;

    Method getSelectedButtonTextMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        serviceFactoryMock = mock(ServiceFactory.class);

        colorModeGroupMock = mock(ButtonGroup.class);
        soundModeGroupMock = mock(ButtonGroup.class);
        viewMock = mock(GameSettingsView.class);
        modelMock = mock(GameSettingsModel.class);
        model = new GameSettingsModel();
        getSelectedButtonTextMethod = GameSettingsModel.class.getDeclaredMethod("getSelectedButtonText", ButtonGroup.class);
        getSelectedButtonTextMethod.setAccessible(true);
    }

    @Test
    void testGetSelectedButtonTextNull() throws InvocationTargetException, IllegalAccessException {

        Object res = getSelectedButtonTextMethod.invoke(modelMock, soundModeGroupMock);

        assertNull(res);
    }

    @Test
    void testGetSelectedButtonText() throws InvocationTargetException, IllegalAccessException {

        ButtonGroup soundGroup = new ButtonGroup();
        soundGroup.add(new JRadioButton("Sound enabled", true));
        soundGroup.add(new JRadioButton("Sound disabled"));

        String res = (String) getSelectedButtonTextMethod.invoke(modelMock, soundGroup);

        assertNotNull(res);
        assertEquals("Sound enabled", res);
    }



}