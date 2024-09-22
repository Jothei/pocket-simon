package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;

import javax.swing.*;
import java.util.Enumeration;
import java.util.Objects;

/**
 * GameSettingsModel class is responsible for handling the game settings.
 * It uses the ServiceFactory to access various services and enums for sound and color modes.
 */
public class GameSettingsModel {

    /**
     * Default constructor for GameSettingsModel.
     */
    public GameSettingsModel() {
        // To prevent JavaDoc Issues
    }

    private String selectedColorMode;
    private String selectedSoundMode;
    private GameSettingsView gameView;
    private eSoundMode soundMode;
    private eColorMode colorMode;
    private ServiceFactory service;

    /**
     * Returns the action command of the selected button in the provided button group.
     *
     * @param buttonGroup a ButtonGroup instance
     * @return the action command of the selected button, or null if no button is selected
     */
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        if (buttonGroup == null || buttonGroup.getElements() == null) {
            return null;
        }
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getActionCommand();
            }
        }
        return null;
    }

    /**
     * Handles the menu actions based on the selected sound and color modes.
     * Clears the selections, disposes the view, and starts the game with the selected modes.
     *
     * @param service        a ServiceFactory instance
     * @param soundModeGroup a ButtonGroup instance for sound mode
     * @param colorModeGroup a ButtonGroup instance for color mode
     * @param view           a GameSettingsView instance
     * @param sender         the object that triggered the action
     */
    void setMenuAction(ServiceFactory service, ButtonGroup soundModeGroup, ButtonGroup colorModeGroup, GameSettingsView view, Object sender) {
        soundMode = eSoundMode.SOUND_ON;
        colorMode = eColorMode.COLOR_ON;
        selectedColorMode = getSelectedButtonText(colorModeGroup);
        selectedSoundMode = getSelectedButtonText(soundModeGroup);
        this.gameView = view;
        this.service = service;
        if (Objects.equals(selectedSoundMode, "Sound disabled")) {
            soundMode = eSoundMode.SOUND_OFF;
        }

        if (Objects.equals(selectedColorMode, "Single Color Mode")) {
            colorMode = eColorMode.COLOR_OFF;
        } else if (Objects.equals(selectedColorMode, "Audio only Mode")) {
            colorMode = eColorMode.COLOR_AUDIO_ONLY;
        } else if (Objects.equals(selectedColorMode, "Multi Button Mode (Alpha)")) {
            colorMode = eColorMode.COLOR_MULTI_BUTTONS;
        }
        view.getSubmitButton().setEnabled(isSetupComplete());
        if (sender instanceof JButton) {
            startGame();
        }
    }

    private void startGame() {
        if (isSetupComplete()) {
            this.gameView.buttonGroupSoundMode.clearSelection();
            this.gameView.buttonGroupColorMode.clearSelection();
            this.gameView.dispose();
            service.GameBehaviour().startGame(soundMode, colorMode);
        }
    }

    private boolean isSetupComplete() {
        return selectedColorMode != null && selectedSoundMode != null;
    }
}