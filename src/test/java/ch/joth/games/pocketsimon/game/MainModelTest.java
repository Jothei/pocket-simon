package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.codeimplementation.GameBehaviourService;
import ch.joth.games.pocketsimon.game.code.codeimplementation.LoggingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class MainModelTest {
    GameSettingsController gameSettingsControllerMock;
    @Mock
    ServiceFactory serviceFactoryMock;
    @Mock
    LoggingService loggingServiceMock;
    @Mock
    GameBehaviourService gameBehaviourServiceMock;

    @BeforeEach
    void setUp() {
        gameSettingsControllerMock = mock(GameSettingsController.class);
        serviceFactoryMock = mock(ServiceFactory.class);
        loggingServiceMock = mock(LoggingService.class);
        gameBehaviourServiceMock = mock(GameBehaviourService.class);
    }

    @Test
    void setMenuActionStartGame() {

        MainModel mainmodel = new MainModel(gameSettingsControllerMock );
        mainmodel.setServiceFactory(new ServiceFactory());

        mainmodel.setMenuAction("Start Game");

        verify(gameSettingsControllerMock, times(1)).startGUI();
    }


    @Test
    void setMenuActionDefault() {

        MainModel mainmodel = new MainModel(gameSettingsControllerMock);
        mainmodel.setServiceFactory(serviceFactoryMock);

        when(serviceFactoryMock.GameBehaviour()).thenReturn(gameBehaviourServiceMock);
        doNothing().when(loggingServiceMock).log(anyString(), any(), any());
        when(serviceFactoryMock.LoggingService()).thenReturn(this.loggingServiceMock);
        mainmodel.setMenuAction("Default");

        verify(gameBehaviourServiceMock, times(1)).exitGame();

    }

}