package ch.joth.games.pocketsimon.game;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * HighscoreView is a JFrame that displays a highscore board.
 * It allows users to view and add new highscores.
 */
public class HighscoreView extends JFrame {
    /**
     * The list model for the highscore list.
     */
    private DefaultListModel<String> listModel;
    /**
     * Show a List of Highscores.
     */
    private JList<String> highscoreList;

    /**
     * Constructs a new HighscoreView.
     * Initializes the UI components and sets up the layout.
     */
    public HighscoreView() {
        setTitle("Highscore Board");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        highscoreList = new JList<>(listModel);

        add(new JScrollPane(highscoreList), BorderLayout.CENTER);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the list of highscores to be displayed.
     *
     * @param highscores a list of highscore strings
     */
    public void setHighscores(List<String> highscores) {
        listModel.clear();
        for (String highscore : highscores) {
            listModel.addElement(highscore);
        }
    }

    /**
     * Gets the new highscore entered by the user.
     *
     * @return the new highscore as a string
     */


}