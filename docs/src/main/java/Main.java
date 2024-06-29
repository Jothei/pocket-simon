import ch.joth.games.pocketsimon.game.MainController;
import ch.joth.games.pocketsimon.game.MainModel;
import ch.joth.games.pocketsimon.game.MainView;

public class Main {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView view = new MainView();
        new MainController(model, view);

        view.setVisible(true);
    }
}
