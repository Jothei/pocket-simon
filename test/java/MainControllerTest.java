import ch.joth.games.pocketsimon.game.MainModel;
import ch.joth.games.pocketsimon.game.MainView;
import org.junit.jupiter.api.BeforeEach;

class MainControllerTest {

    private MainModel controllerTest;
    private MainView viewMock;

    @BeforeEach
    void setUp() {
        controllerTest = new MainModel();
        viewMock = new MainView();

    }



}