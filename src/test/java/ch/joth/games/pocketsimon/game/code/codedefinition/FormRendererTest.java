package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.codeimplementation.FormRendererService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormRendererTest {


    @Test
    void isBlueButtonTest() {

        assertTrue(FormRendererService.isBlueButton(568, 654));
        assertFalse(FormRendererService.isBlueButton(260, 654));
    }

    @Test
    void isYellowButtonTest() {

        assertTrue(FormRendererService.isYellowButton(260, 654));
        assertFalse(FormRendererService.isYellowButton(568, 654));
        assertFalse(FormRendererService.isYellowButton(614, 307));


    }

    @Test
    void isRedButtonTest() {
        assertFalse(FormRendererService.isRedButton(260, 654));
        assertFalse(FormRendererService.isRedButton(568, 654));
        assertTrue(FormRendererService.isRedButton(614, 307));
     }

    @Test
    void isGreenButtonTest() {
        assertTrue(FormRendererService.isGreenButton(215, 188));
        assertFalse(FormRendererService.isGreenButton(260, 654));
        assertFalse(FormRendererService.isGreenButton(568, 654));
    }

    @Test
    void checkScreenDimensionsTest() {
        assertEquals(800, FormRendererService.WIDTH);
        assertEquals(800, FormRendererService.HEIGHT);
    }
    @Test
    void blueButtonShouldReturnTrueForBottomRightQuadrant() {
        assertTrue(FormRendererService.isBlueButton(600, 600));
    }

    @Test
    void blueButtonShouldReturnFalseForOtherQuadrants() {
        assertFalse(FormRendererService.isBlueButton(200, 200));
        assertFalse(FormRendererService.isBlueButton(600, 200));
        assertFalse(FormRendererService.isBlueButton(200, 600));
    }

    @Test
    void yellowButtonShouldReturnTrueForBottomLeftQuadrant() {
        assertTrue(FormRendererService.isYellowButton(200, 600));
    }

    @Test
    void yellowButtonShouldReturnFalseForOtherQuadrants() {
        assertFalse(FormRendererService.isYellowButton(600, 600));
        assertFalse(FormRendererService.isYellowButton(200, 200));
        assertFalse(FormRendererService.isYellowButton(600, 200));
    }

    @Test
    void redButtonShouldReturnTrueForTopRightQuadrant() {
        assertTrue(FormRendererService.isRedButton(600, 200));
    }

    @Test
    void redButtonShouldReturnFalseForOtherQuadrants() {
        assertFalse(FormRendererService.isRedButton(200, 200));
        assertFalse(FormRendererService.isRedButton(600, 600));
        assertFalse(FormRendererService.isRedButton(200, 600));
    }

    @Test
    void greenButtonShouldReturnTrueForTopLeftQuadrant() {
        assertTrue(FormRendererService.isGreenButton(200, 200));
    }

    @Test
    void greenButtonShouldReturnFalseForOtherQuadrants() {
        assertFalse(FormRendererService.isGreenButton(600, 600));
        assertFalse(FormRendererService.isGreenButton(200, 600));
        assertFalse(FormRendererService.isGreenButton(600, 200));
    }



}