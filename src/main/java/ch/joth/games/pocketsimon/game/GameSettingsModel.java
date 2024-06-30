package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;

import javax.swing.*;
import java.util.Enumeration;
import java.util.Objects;


public class GameSettingsModel {

    public GameSettingsModel() {
    }

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

    void setMenuAction(ServiceFactory service, ButtonGroup soundModeGroup, ButtonGroup colorModeGroup, GameSettingsView view) {
        eSoundMode soundMode = eSoundMode.SOUND_ON;
        eColorMode colorMode = eColorMode.COLOR_ON;
        String selectedColorMode = getSelectedButtonText(colorModeGroup);
        String selectedSoundMode = getSelectedButtonText(soundModeGroup);


        if (Objects.equals(selectedSoundMode, "Sound disabled")) {
            soundMode = eSoundMode.SOUND_OFF;
        }


        if (Objects.equals(selectedColorMode, "Single Color Mode")) {
            colorMode = eColorMode.COLOR_OFF;
        } else if (Objects.equals(selectedColorMode, "Audio only Mode")) {
            colorMode = eColorMode.Color_Audio_Only;
        }

        if ((selectedColorMode != null && selectedSoundMode != null)) {
            view.buttonGroupSoundMode.clearSelection();
            view.buttonGroupColorMode.clearSelection();
            view.dispose();
            service.GameBehaviour().startGame(soundMode, colorMode);
        }
    }

}
