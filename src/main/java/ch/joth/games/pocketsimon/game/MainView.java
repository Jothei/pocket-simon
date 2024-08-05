package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eConfigValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * MainView class extends JFrame and represents the main menu of the game.
 * It contains buttons to start and exit the game.
 */
public class MainView extends JFrame {

    /**
     * Button to start the game. When clicked, the game will begin.
     */
    private final JButton startGameButton;

    /**
     * Button to exit the game. When clicked, the game will close.
     */
    private final JButton exitGameButton;

    /**
     * Button to show the highscore. When clicked, the highscore will be shown.
     */
    private final JButton highscoreButton;

    /**
     * Constructor for MainView.
     * Initializes the frame with title, size, layout and buttons.
     */
    public MainView() {
        ServiceFactory serviceFactory = new ServiceFactory();
        // Set the title of the frame
        this.setTitle(serviceFactory.ConfigService().getValue(eConfigValues.GAME_TITLE) + " - Main Menu");
        // Set the size of the frame
        this.setSize(Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_WIDTH)), Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_HEIGHT)));
        // Set the default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the layout of the frame
        this.setLayout(new BorderLayout());
        // Center the frame
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // Initialize Buttons
        startGameButton = new JButton("Start Game");
        exitGameButton = new JButton("Exit Game");
        highscoreButton = new JButton("Highscore");

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(startGameButton);
        buttonPanel.add(highscoreButton);
        buttonPanel.add(exitGameButton);

        // Add components to frame
        this.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * Sets the action listener for the start and exit game buttons.
     *
     * @param listener the ActionListener to be set
     */
    public void setButtonActionListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
        exitGameButton.addActionListener(listener);
        highscoreButton.addActionListener(listener);
    }
}