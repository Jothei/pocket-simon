package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class HighscoreControllerTest {

    private HighscoreModel model;
    private HighscoreView view;
    private HighscoreController controller;

    @BeforeEach
    void setUp() {
        model = mock(HighscoreModel.class);
        view = mock(HighscoreView.class);
        controller = new HighscoreController(model, view);
    }

    @Test
    void getView_returnsViewInstance() {
        assertEquals(view, controller.getView());
    }


}