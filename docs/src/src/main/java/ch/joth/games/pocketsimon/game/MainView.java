package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eConfigValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainView extends JFrame {
    private final JButton startGameButton;
    private final JButton exitGameButton;

    public MainView() {
        ServiceFactory serviceFactory = new ServiceFactory();
        this.setTitle(serviceFactory.ConfigService().getValue(eConfigValues.GAME_TITLE) + " - Main Menu");
        this.setSize(Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_WIDTH)), Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_HEIGHT)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Initialize Buttons
        startGameButton = new JButton("Start Game");
        exitGameButton = new JButton("Exit Game");

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(startGameButton);
        buttonPanel.add(exitGameButton);

        // Add components to frame
        this.add(buttonPanel, BorderLayout.CENTER);
    }


    public void setButtonActionListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
        exitGameButton.addActionListener(listener);
    }


}
