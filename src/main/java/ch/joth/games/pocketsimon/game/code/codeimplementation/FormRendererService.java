package ch.joth.games.pocketsimon.game.code.codeimplementation;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

/**
 * This class extends JPanel and is responsible for rendering the game form.
 * It contains methods for painting components and detecting button presses.
 */
public class FormRendererService extends JPanel {

    /**
     * Default constructor for the FormRendererService class.
     * It is used to create an instance of the FormRendererService class.
     */
    public FormRendererService() {
        // Comment to prevent Warnings from JavaDoc generation
    }

    /**
     * Width of the game form.
     */
    public static final int WIDTH = 800;

    /**
     * Height of the game form.
     */
    public static final int HEIGHT = 800;

    /**
     * This method is called to paint a component.
     * It calls the paint method of the game behaviour if it is not null.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (GameBehaviourService.gameBehaviour != null) {
            GameBehaviourService.gameBehaviour.paint((Graphics2D) g);
        }

    }

    /**
     * This method checks if the blue button is pressed.
     *
     * @param x The x coordinate of the mouse click.
     * @param y The y coordinate of the mouse click.
     * @return True if the blue button is pressed, false otherwise.
     */
    public static boolean isBlueButton(int x, int y) {
        return x > FormRendererService.WIDTH / 2 && x < FormRendererService.WIDTH && y > FormRendererService.HEIGHT / 2 && y < FormRendererService.HEIGHT;
    }

    /**
     * This method checks if the yellow button is pressed.
     *
     * @param x The x coordinate of the mouse click.
     * @param y The y coordinate of the mouse click.
     * @return True if the yellow button is pressed, false otherwise.
     */
    public static boolean isYellowButton(int x, int y) {
        return x > 0 && x < FormRendererService.WIDTH / 2 && y > FormRendererService.HEIGHT / 2 && y < FormRendererService.HEIGHT;
    }

    /**
     * This method checks if the red button is pressed.
     *
     * @param x The x coordinate of the mouse click.
     * @param y The y coordinate of the mouse click.
     * @return True if the red button is pressed, false otherwise.
     */
    public static boolean isRedButton(int x, int y) {
        return x > FormRendererService.WIDTH / 2 && x < FormRendererService.WIDTH && y > 0 && y < FormRendererService.HEIGHT / 2;
    }

    /**
     * This method checks if the green button is pressed.
     *
     * @param x The x coordinate of the mouse click.
     * @param y The y coordinate of the mouse click.
     * @return True if the green button is pressed, false otherwise.
     */
    public static boolean isGreenButton(int x, int y) {
        return x > 0 && x < WIDTH / 2 && y > 0 && y < HEIGHT / 2;
    }

    /**
     * This method generates a random color.
     *
     * @return A random color.
     */
    public static Color getRandomColor(SecureRandom rand) {

        float r = (float) (rand.nextFloat() / 2f + 0.5);
        float g = (float) (rand.nextFloat() / 2f + 0.5);
        float b = (float) (rand.nextFloat() / 2f + 0.5);
        return new Color(r, g, b, 1f);
    }

}
