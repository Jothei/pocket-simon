package ch.joth.games.pocketsimon.game;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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



    }

