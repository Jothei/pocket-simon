package ch.joth.games.pocketsimon.game;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GameSettingsControllerTest {
    @Mock
    GameSettingsView viewMock;


    @BeforeEach
    void setUp() {
        viewMock = mock(GameSettingsView.class);

    }

    @Test
    void startGUI() {
        GameSettingsController controller = new GameSettingsController(viewMock);
        controller.startGUI();
        verify(viewMock, times(1)).setVisible(true);
    }

    @Test
    void testConstructorWithParameter() throws NoSuchFieldException, IllegalAccessException {
        GameSettingsController controller = new GameSettingsController(viewMock);

        Field viewField = GameSettingsController.class.getDeclaredField("view");
        viewField.setAccessible(true);
        GameSettingsView view = (GameSettingsView) viewField.get(controller);

        assertNotNull(view);
    }

    @Test
    void testActionPerformed() {
        GameSettingsController controller = new GameSettingsController(viewMock);
        GameSettingsModel modelMock = mock(GameSettingsModel.class);
        GameSettingsController.SettingsButtonListener listener = controller.new SettingsButtonListener(viewMock, viewMock.buttonGroupColorMode, viewMock.buttonGroupSoundMode, modelMock);
        GameSettingsController.SettingsButtonListener listenerSpy = spy(listener);
        ActionEvent actionEvent = new ActionEvent(viewMock, 0, "test");
        listenerSpy.actionPerformed(actionEvent);

        verify(modelMock, times(1)).setMenuAction(any(), any(), any(), any(), any(Object.class));

    }
}

