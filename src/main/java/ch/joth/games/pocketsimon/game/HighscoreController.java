package ch.joth.games.pocketsimon.game;

public class HighscoreController {

    private final HighscoreModel model;

    private final HighscoreView view;

    public HighscoreController(HighscoreModel model, HighscoreView view) {

        this.model = model;

        this.view = view;

        updateView();

    }

    private void updateView() {

        var sb = new StringBuilder();

        for (HighscoreEntry entry : model.getEntries()) {

            sb.append(entry.getName()).append(": ").append(entry.getScore()).append("\t").append(entry.getDate()).append("\n");

        }
    }

}