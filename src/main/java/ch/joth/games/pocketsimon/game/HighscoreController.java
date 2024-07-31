package ch.joth.games.pocketsimon.game;

/**
 * HighscoreController is a class that manages the interaction between the HighscoreModel and HighscoreView.
 * It updates the view based on the data in the model.
 */
public class HighscoreController {
    /**
     * The HighscoreModel instance.
     */
    private final HighscoreModel model;
    /**
     * The HighscoreView instance.
     */
    private final HighscoreView view;

    /**
     * Constructs a new HighscoreController.
     * Initializes the model and view, and updates the view.
     *
     * @param model the HighscoreModel instance
     * @param view  the HighscoreView instance
     */
    public HighscoreController(HighscoreModel model, HighscoreView view) {
        this.model = model;
        this.view = view;
        updateView();
    }

    /**
     * Updates the view with the highscore entries from the model.
     * Constructs a string representation of the highscore entries and updates the view.
     */
    void updateView() {
        var sb = new StringBuilder();
        for (HighscoreEntry entry : model.getEntries()) {
            sb.append(entry.getName()).append(": ").append(entry.getScore()).append("\t").append(entry.getDate()).append("\n");
        }
    }

    /**
     * Retrieves the HighscoreView instance.
     *
     * @return the HighscoreView instance
     */
    public HighscoreView getView() {
        return view;
    }
}