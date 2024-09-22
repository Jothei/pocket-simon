package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MainControllerTest {
    MainController controller;
    MainModel modelMock;
    MainView viewMock;
    ActionEvent eventMock;

    @BeforeEach
    void setUp() {
        modelMock = mock(MainModel.class);
        viewMock = mock(MainView.class);
        controller = new MainController(modelMock, viewMock);
        eventMock = mock(ActionEvent.class);
    }

    @Test
    void testConstructorWithParametersTest() throws NoSuchFieldException, IllegalAccessException {

        MainController mainController = new MainController(modelMock, viewMock);

        Field model = mainController.getClass().getDeclaredField("model");
        model.setAccessible(true);
        Field serviceFactory = mainController.getClass().getDeclaredField("serviceFactory");
        serviceFactory.setAccessible(true);

        verify(viewMock, times(2)).setButtonActionListener(any());
        assertNotNull(model.get(mainController));
        assertNotNull(serviceFactory.get(mainController));

    }

    @Test
    void actionPerformedSetsServiceFactoryAndMenuActionTest() {
        when(eventMock.getActionCommand()).thenReturn("command");

        controller.new MenuButtonListener().actionPerformed(eventMock);

        verify(modelMock).setServiceFactory(any(ServiceFactory.class));
        verify(modelMock).setMenuAction("command");
    }

    @Test
    void actionPerformedWithNullCommand_doesNotSetMenuActionTest() {
        when(eventMock.getActionCommand()).thenReturn(null);

        controller.new MenuButtonListener().actionPerformed(eventMock);

        verify(modelMock).setServiceFactory(any(ServiceFactory.class));
        verify(modelMock, never()).setMenuAction(anyString());
    }

}