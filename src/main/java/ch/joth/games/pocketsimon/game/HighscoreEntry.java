package ch.joth.games.pocketsimon.game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HighscoreEntry {

    private String name;

    private int score;

    private String date;

    public HighscoreEntry() {
//NOSONAR to Prevent SonarLint from complaining about an empty constructor
    }

    public HighscoreEntry(String name, int score) {
        this.name = name;
        this.score = score;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        this.date = now.format(formatter);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }


}
