package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsController {
    private  final GameSettingsView view;


    public GameSettingsController() {
        GameSettingsModel model = new GameSettingsModel();
        this.view = new GameSettingsView();
        this.view.setButtonActionListener(new SettingsButtonListener(this.view, this.view.buttonGroupColorMode, this.view.buttonGroupSoundMode, model));
    }

    public GameSettingsController(GameSettingsView view) {
        this.view = view;
    }

    void startGUI() {
        view.setVisible(true);
    }


      class SettingsButtonListener implements ActionListener {
        private final ServiceFactory service;
        private final ButtonGroup soundModeGroup;
        private final ButtonGroup colorModeGroup;
        private final GameSettingsModel model;

        public SettingsButtonListener(GameSettingsView view, ButtonGroup colorModeGroup, ButtonGroup soundModeGroup, GameSettingsModel model) {
            this.colorModeGroup = colorModeGroup;
            this.soundModeGroup = soundModeGroup;
            this.service = view.serviceFactory;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.model.setMenuAction(service, soundModeGroup, colorModeGroup, view);

        }
    }
}

