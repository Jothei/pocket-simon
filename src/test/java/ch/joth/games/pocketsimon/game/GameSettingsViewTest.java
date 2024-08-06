package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

class GameSettingsViewTest {

    private GameSettingsView view;

    @BeforeEach
    void setUp() {
        view = mock(GameSettingsView.class);
        doCallRealMethod().when(view).getPanel();
    }


    @Test
    void getPanel() {
        view.getPanel();

        assertEquals(4, view.buttonGroupColorMode.getButtonCount());
        assertEquals(2, view.buttonGroupSoundMode.getButtonCount());
    }


    @Test
    void setButtonActionListener() {
        ActionListener listener = mock(ActionListener.class);
        doCallRealMethod().when(view).setButtonActionListener(any());

        view.getPanel();
        view.setButtonActionListener(listener);

        assertEquals(1, view.submitButton.getActionListeners().length);
        assertEquals(1, view.radioButtonColor.getActionListeners().length);
        assertEquals(1, view.radioButtonNoColor.getActionListeners().length);
        assertEquals(1, view.radioButtonAudioOnlyMode.getActionListeners().length);
        assertEquals(1, view.radioButtonNoSound.getActionListeners().length);
        assertEquals(1, view.radioButtonSound.getActionListeners().length);
        assertEquals(1, view.radioButtonColorMultiButton.getActionListeners().length);
    }


    @Test
    void submitButtonInitiallyDisabled() {
        view.getPanel();

        assertEquals(false, view.submitButton.isEnabled());
    }


}