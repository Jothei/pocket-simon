package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class GameSettingsViewTest {

    private GameSettingsView view;

    @BeforeEach
    void setUp() {
        view = new GameSettingsView();
    }

    @Disabled
    @Test
    void getPanel() {
        view.getPanel();

        assertEquals(3, view.buttonGroupColorMode.getButtonCount());
        assertEquals(2, view.buttonGroupSoundMode.getButtonCount());
    }

    @Disabled
    @Test
    void setButtonActionListener() {
        ActionListener listener = mock(ActionListener.class);
        view.setButtonActionListener(listener);
        assertEquals(1, view.submitButton.getActionListeners().length);
        assertEquals(1, view.radioButtonColor.getActionListeners().length);
        assertEquals(1, view.radioButtonNoColor.getActionListeners().length);
        assertEquals(1, view.radioButtonAudioOnlyMode.getActionListeners().length);
        assertEquals(1, view.radioButtonNoSound.getActionListeners().length);
        assertEquals(1, view.radioButtonSound.getActionListeners().length);
    }

    @Disabled
    @Test
    void submitButtonInitiallyDisabled() {
        assertEquals(false, view.submitButton.isEnabled());
    }

    @Disabled
    @Test
    void constructorInitializesComponents() {
        assertNotNull(view.labelButtonColor);
        assertNotNull(view.radioButtonNoColor);
        assertNotNull(view.radioButtonColor);
        assertNotNull(view.radioButtonAudioOnlyMode);
        assertNotNull(view.labelSoundMode);
        assertNotNull(view.radioButtonSound);
        assertNotNull(view.radioButtonNoSound);
        assertNotNull(view.buttonGroupColorMode);
        assertNotNull(view.buttonGroupSoundMode);
        assertNotNull(view.submitButton);
    }
}