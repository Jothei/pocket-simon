package ch.joth.games.pocketsimon.game.code.codeimplementation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FormRendererServiceTest {


    @ParameterizedTest
    @ValueSource(ints = {10, 100, 1000, 10000})
    void colorRangeTest(int iterations) {
        SecureRandom random = new SecureRandom();

        for (int i = 0; i <= iterations; i++) {
            Color color = FormRendererService.getRandomColor(random);
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();

            assertTrue(r >= 0 && r <= 255, "R-Wert außerhalb des Bereichs.");
            assertTrue(g >= 0 && g <= 255, "G-Wert außerhalb des Bereichs.");
            assertTrue(b >= 0 && b <= 255, "B-Wert außerhalb des Bereichs.");
        }
    }
}