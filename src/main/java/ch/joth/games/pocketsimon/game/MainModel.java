package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import org.apache.logging.log4j.Level;

/**
 * MainModel class is responsible for handling the game settings and actions.
 * It uses the ServiceFactory to access various services like logging and game behavior.
 */
public class MainModel {
    private ServiceFactory serviceFactory;
    private GameSettingsController gameSettingsController;

    /**
     * Default constructor for MainModel.
     * Initializes the gameSettingsController.
     */
    public MainModel() {
        gameSettingsController = new GameSettingsController();
    }

    /**
     * Overloaded constructor for MainModel.
     * Initializes the gameSettingsController with the provided instance.
     *
     * @param gameSettingsController an instance of GameSettingsController
     */
    public MainModel(GameSettingsController gameSettingsController) {
        this.gameSettingsController = gameSettingsController;
    }

    /**
     * Sets the ServiceFactory instance.
     *
     * @param serviceFactory an instance of ServiceFactory
     */
    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    /**
     * Handles the menu actions based on the provided game mode.
     * Logs the action and starts or exits the game accordingly.
     *
     * @param gameMode a string representing the game mode
     */
    public void setMenuAction(String gameMode) {

        if (gameMode.equals("Start Game")) {
            serviceFactory.LoggingService().log("Game started Button clicked", Level.INFO, GameSettingsModel.class);
            this.gameSettingsController.startGUI();
        } else {
            serviceFactory.LoggingService().log("Exit Game", Level.WARN, GameSettingsModel.class, gameMode);
            serviceFactory.GameBehaviour().exitGame();
        }

    }

}