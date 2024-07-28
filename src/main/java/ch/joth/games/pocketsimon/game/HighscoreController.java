package ch.joth.games.pocketsimon.game;

/**
 * HighscoreController is a controller class that manages the interaction between the HighscoreModel and HighscoreView.
 * It handles user actions and updates the view and model accordingly.
 */
public class HighscoreController {
    private HighscoreModel model;
    private HighscoreView view;

    /**
     * Constructs a new HighscoreController.
     * Initializes the controller with the given model and view, sets up the initial view state, and adds action listeners.
     *
     * @param model the HighscoreModel instance to be used by the controller
     * @param view  the HighscoreView instance to be used by the controller
     */
    public HighscoreController(HighscoreModel model, HighscoreView view) {
        this.model = model;
        this.view = view;

        // Initialize the view with data from the model
        view.setHighscores(model.getHighscores());

        // Add action listener for the add button
        //  view.addAddButtonListener(new ActionListener() {
        //      @Override
        //      public void actionPerformed(ActionEvent e) {
        //          String newHighscore = view.getNewHighscore();
        //          if (!newHighscore.isEmpty()) {
        //              model.addHighscore(newHighscore);
        //              view.setHighscores(model.getHighscores());
        //          }
        //      }
        //  });
    }
}