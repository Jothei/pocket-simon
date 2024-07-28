package ch.joth.games.pocketsimon.game;

import java.util.ArrayList;
import java.util.List;

/**
 * HighscoreModel is a model class that manages a list of highscores.
 * It provides methods to retrieve and add highscores.
 */
public class HighscoreModel {
    private List<String> highscores;

    /**
     * Constructs a new HighscoreModel.
     * Initializes the highscores list.
     */
    public HighscoreModel() {
        highscores = new ArrayList<>();
    }

    /**
     * Retrieves the list of highscores.
     *
     * @return a list of highscore strings
     */
    public List<String> getHighscores() {
        return highscores;
    }

    /**
     * Adds a new highscore to the list.
     *
     * @param highscore the highscore string to be added
     */
    public void addHighscore(String highscore) {
        highscores.add(highscore);
    }
}