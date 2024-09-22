package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.eColorMode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * HighscoreEntry is a class that represents a highscore entry in the Pocket Simon game.
 * It contains the name of the player, the score, and the date when the score was achieved.
 */
public class HighscoreEntry {
    /**
     * Name of the player.
     */
    private String name;
    /**
     * Score achieved by the player.
     */
    private int score;
    /**
     * Date when the score was achieved.
     */
    private String date;
    /**
     * Game mode of the highscore entry.
     */
    private eColorMode gameMode;

    /**
     * Default constructor for HighscoreEntry.
     * This constructor is required for deserialization purposes.
     */
    public HighscoreEntry() {
        // NOSONAR to Prevent SonarLint from complaining about an empty constructor
    }

    /**
     * Constructs a new HighscoreEntry with the specified name and score.
     * The date is set to the current date and time.
     *
     * @param name     the name of the player
     * @param score    the score achieved by the player
     * @param gameMode the game mode of the highscore entry
     */
    public HighscoreEntry(String name, int score, eColorMode gameMode) {
        this.name = name;
        this.setName(name);
        this.score = score;
        this.gameMode = gameMode;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        this.date = now.format(formatter);
    }

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    public eColorMode getGameMode() {
        return gameMode;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the score achieved by the player.
     *
     * @return the score achieved by the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the date when the score was achieved.
     *
     * @return the date when the score was achieved
     */
    public String getDate() {
        return date;
    }
}