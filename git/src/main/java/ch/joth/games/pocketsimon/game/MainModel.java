package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import org.apache.logging.log4j.Level;

public class MainModel {
    private ServiceFactory serviceFactory;
    private GameSettingsController gameSettingsController;

    public MainModel() {
        gameSettingsController = new GameSettingsController();
     }

    public MainModel(GameSettingsController gameSettingsController) {
        this.gameSettingsController = gameSettingsController;

    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }


    public void setMenuAction(String gameMode) {

        switch (gameMode) {
            case "Start Game":
                serviceFactory.LoggingService().log("Game started Button clicked", Level.INFO, GameSettingsModel.class);
                this.gameSettingsController.startGUI();
                break;

            default:
                serviceFactory.LoggingService().log("Exit Game", Level.WARN, GameSettingsModel.class, gameMode);
                serviceFactory.GameBehaviour().exitGame();

                break;
        }

    }

}
