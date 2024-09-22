package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class HighscoreControllerTest {

    private HighscoreView view;
    private HighscoreController controller;

    @BeforeEach
    void setUp() {
        HighscoreModel model = mock(HighscoreModel.class);
        view = mock(HighscoreView.class);
        controller = new HighscoreController(model, view);
    }

    @Test
    void getViewReturnsViewInstanceTest() {
        assertEquals(view, controller.getView());
    }


}