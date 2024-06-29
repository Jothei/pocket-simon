package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;

import java.awt.*;

/**
 * This interface represents the game behaviour for the game.
 * It provides methods to paint graphics, start the game and initialize the drawing.
 */
public interface IGameBehaviour {
    /**
     * This method is used to paint graphics on the game screen.
     *
     * @param g The Graphics2D object to be painted.
     */
    void paint(Graphics2D g);

    /**
     * This method is used to start the game.
     */
    void initGameVariables();

    /**
     * This method is used to initialize the drawing on the game screen.
     */
    void startGame();

    void startGame(eSoundMode soundMode, eColorMode colorMode);

    void exitGame();
}
