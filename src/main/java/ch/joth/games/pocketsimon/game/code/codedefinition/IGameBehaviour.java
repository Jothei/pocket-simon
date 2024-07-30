package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.eColorMode;
import ch.joth.games.pocketsimon.game.code.eSoundMode;

import javax.swing.*;
import java.awt.*;

/**
 * IGameBehaviour interface provides methods for handling the game behaviour.
 * It allows to paint graphics, start the game, initialize the game variables and exit the game.
 */
public interface IGameBehaviour {

    /**
     * Paints the provided Graphics2D object on the game screen.
     *
     * @param g the Graphics2D object to be painted
     */
    void paint(Graphics2D g);

    /**
     * Initializes the game variables.
     */
    void initGameVariables();

    /**
     * Starts the game with default sound and color modes.
     */
    void startGame();

    /**
     * Starts the game with the provided sound and color modes.
     *
     * @param soundMode an instance of eSoundMode representing the sound mode
     * @param colorMode an instance of eColorMode representing the color mode
     */
    void startGame(eSoundMode soundMode, eColorMode colorMode);

    /**
     * Exits the game.
     */
    void exitGame();


    void showHighscore();

    void initHighscoreInsertDialog(JFrame parent, int points);
}