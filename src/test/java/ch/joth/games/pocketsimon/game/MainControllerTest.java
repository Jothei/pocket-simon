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
    MainModel model;
    MainView view;
    private MainController controller;
    private MainModel modelMock;
    private MainView viewMock;

    @BeforeEach
    void setUp() {
        model = new MainModel();
        modelMock = mock(MainModel.class);
        view = new MainView();
        viewMock = mock(MainView.class);
        controller = new MainController(modelMock, viewMock);
    }

    @Test
    void testConstructorWithParameters() throws NoSuchFieldException, IllegalAccessException  {

        MainController mainController = new MainController(modelMock, viewMock);

        Field model = mainController.getClass().getDeclaredField("model");
        model.setAccessible(true);
        Field serviceFactory = mainController.getClass().getDeclaredField("serviceFactory");
        serviceFactory.setAccessible(true);

        verify(viewMock, atLeastOnce()).setButtonActionListener(any());
        assertNotNull(model.get(mainController));
        assertNotNull(serviceFactory.get(mainController));

    }

    @Test
    void actionPerformed_setsServiceFactoryAndMenuAction() {
        ActionEvent eventMock = mock(ActionEvent.class);
        when(eventMock.getActionCommand()).thenReturn("command");

        controller.new MenuButtonListener().actionPerformed(eventMock);

        verify(modelMock).setServiceFactory(any(ServiceFactory.class));
        verify(modelMock).setMenuAction("command");
    }

    @Test
    void actionPerformed_withNullCommand_doesNotSetMenuAction() {
        ActionEvent eventMock = mock(ActionEvent.class);
        when(eventMock.getActionCommand()).thenReturn(null);

        controller.new MenuButtonListener().actionPerformed(eventMock);

        verify(modelMock).setServiceFactory(any(ServiceFactory.class));
        verify(modelMock, never()).setMenuAction(anyString());
    }

}