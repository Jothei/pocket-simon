package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;

class MainViewTest {
    @Disabled("Test is disabled because it is not possible to test the GUI")
    @Test
    void setButtonActionListener() throws NoSuchFieldException, IllegalAccessException {
        MainView mainView = new MainView();
        mainView.setButtonActionListener(null);
        Field startGameButtonField = mainView.getClass().getDeclaredField("startGameButton");
        startGameButtonField.setAccessible(true);
        Field exitGameButtonField = mainView.getClass().getDeclaredField("exitGameButton");
        exitGameButtonField.setAccessible(true);
        Field highscoreButtonField = mainView.getClass().getDeclaredField("highscoreButton");
        highscoreButtonField.setAccessible(true);
        ActionListener listener = mock(ActionListener.class);

        mainView.setButtonActionListener(listener);

        JButton startGameButtonListener = ((JButton) startGameButtonField.get(mainView));
        JButton exitGameButtonListener = ((JButton) exitGameButtonField.get(mainView));
        JButton highscoreButtonListener = ((JButton) highscoreButtonField.get(mainView));

        Assertions.assertEquals(1, startGameButtonListener.getActionListeners().length);
        Assertions.assertEquals(1, exitGameButtonListener.getActionListeners().length);
        Assertions.assertEquals(1, highscoreButtonListener.getActionListeners().length);

    }
}