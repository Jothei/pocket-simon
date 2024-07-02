import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void reset() throws NoSuchFieldException, IllegalAccessException {
        serviceFactory.reset();

        Field audioServiceFld = serviceFactory.getClass().getDeclaredField("_audioService");
        audioServiceFld.setAccessible(true);
        Field gameBehaviourFld = serviceFactory.getClass().getDeclaredField("_gameBehaviour");
        gameBehaviourFld.setAccessible(true);
        Field formRendererFld = serviceFactory.getClass().getDeclaredField("_formRenderer");
        formRendererFld.setAccessible(true);
        Field loggingServiceFld = serviceFactory.getClass().getDeclaredField("_loggingService");
        loggingServiceFld.setAccessible(true);
        Field configServiceFld = serviceFactory.getClass().getDeclaredField("_configService");
        configServiceFld.setAccessible(true);


        assertNull(audioServiceFld.get(serviceFactory));
        assertNull(gameBehaviourFld.get(serviceFactory));
        assertNull(formRendererFld.get(serviceFactory));
        assertNull(loggingServiceFld.get(serviceFactory));
        assertNull(configServiceFld.get(serviceFactory));
        assertNotNull(serviceFactory.AudioService());
        assertNotNull(serviceFactory.GameBehaviour());
        assertNotNull(serviceFactory.FormRenderer());
        assertNotNull(serviceFactory.LoggingService());
        assertNotNull(serviceFactory.ConfigService());
    }



    @AfterEach
    void tearDown() {
        serviceFactory.reset();
    }

}