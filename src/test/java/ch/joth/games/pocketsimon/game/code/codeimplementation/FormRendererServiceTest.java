package ch.joth.games.pocketsimon.game.code.codeimplementation;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FormRendererServiceTest {

    @Test
    void getRandomColorTest() {
        SecureRandom random = new SecureRandom();

        Color color1 = FormRendererService.getRandomColor(random);
        Color color2 = FormRendererService.getRandomColor(random);
        assertTrue(color1.getRed() != color2.getRed());
        assertTrue(color1.getBlue() != color2.getBlue());
        assertTrue(color1.getGreen() != color2.getGreen());

    }
}