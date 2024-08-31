package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameSettingsController class is responsible for handling the actions performed on the GameSettingsView.
 * It uses the ServiceFactory to access various services and the GameSettingsModel to handle the game settings logic.
 */
public class GameSettingsController {
    private final GameSettingsView view;

    /**
     * Default constructor for GameSettingsController.
     * Initializes the model and view, and sets the action listener for the view.
     */
    public GameSettingsController() {
        GameSettingsModel model = new GameSettingsModel();
        this.view = new GameSettingsView();
        this.view.setButtonActionListener(new SettingsButtonListener(this.view, this.view.buttonGroupColorMode, this.view.buttonGroupSoundMode, model));
    }

    /**
     * Overloaded constructor for GameSettingsController.
     * Initializes the view with the provided instance.
     *
     * @param view an instance of GameSettingsView
     */
    public GameSettingsController(GameSettingsView view) {
        this.view = view;
    }

    /**
     * Makes the view visible.
     */
    void startGUI() {
        view.setVisible(true);
    }

    /**
     * SettingsButtonListener class implements ActionListener.
     * It handles the actions performed on the settings buttons.
     */
    class SettingsButtonListener implements ActionListener {
        private final ServiceFactory service;
        private final ButtonGroup soundModeGroup;
        private final ButtonGroup colorModeGroup;
        private final GameSettingsModel model;

        /**
         * Constructor for SettingsButtonListener.
         * Initializes the service, soundModeGroup, colorModeGroup and model with the provided instances.
         *
         * @param view           an instance of GameSettingsView
         * @param colorModeGroup a ButtonGroup instance for color mode
         * @param soundModeGroup a ButtonGroup instance for sound mode
         * @param model          an instance of GameSettingsModel
         */
        public SettingsButtonListener(GameSettingsView view, ButtonGroup colorModeGroup, ButtonGroup soundModeGroup, GameSettingsModel model) {
            this.colorModeGroup = colorModeGroup;
            this.soundModeGroup = soundModeGroup;
            this.service = view.serviceFactory;
            this.model = model;
        }

        /**
         * actionPerformed method is called when a settings button is clicked.
         * It calls the model's setMenuAction method with the service, soundModeGroup, colorModeGroup and view.
         *
         * @param e an instance of ActionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            this.model.setMenuAction(service, soundModeGroup, colorModeGroup, view, e.getSource());
        }
    }
}