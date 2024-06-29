import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceFactoryTest {


    private ServiceFactory serviceFactory;

    @BeforeEach
    void setUp() {
        serviceFactory = new ServiceFactory();
    }

    @Test
    void audioService() {
        assertNotNull(serviceFactory.AudioService());
    }

    @Test
    void gameBehaviour() {
        assertNotNull(serviceFactory.GameBehaviour());
    }

    @Test
    void formRenderer() {
        assertNotNull(serviceFactory.FormRenderer());
    }

    @Test
    void loggingService() {
        assertNotNull(serviceFactory.LoggingService());
    }

}