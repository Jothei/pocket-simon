import ch.joth.games.pocketsimon.game.MainController;
import ch.joth.games.pocketsimon.game.MainModel;
import ch.joth.games.pocketsimon.game.MainView;

/**
 * The Main class is the entry point of the application.
 * It creates instances of the MainModel, MainView, and MainController classes,
 * and sets the view to be visible.
 */
public class Main {
    /**
     * Default constructor for the Main class.
     * It is used to create an instance of the Main class.
     */
    public Main(){

    }
    /**
     * The main method is the entry point of the application.
     * It creates instances of the MainModel, MainView, and MainController classes,
     * and sets the view to be visible.
     *
     * @param args command-line arguments passed to the application. This application does not use command-line arguments.
     */
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView view = new MainView();
        new MainController(model, view);

        view.setVisible(true);
    }
}
