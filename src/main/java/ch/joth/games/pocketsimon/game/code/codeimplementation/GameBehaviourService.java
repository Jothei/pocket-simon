package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.*;
import ch.joth.games.pocketsimon.game.code.codedefinition.IGameBehaviour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import static ch.joth.games.pocketsimon.game.code.codeimplementation.FormRendererService.HEIGHT;
import static ch.joth.games.pocketsimon.game.code.codeimplementation.FormRendererService.WIDTH;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.Level.WARN;

/**
 * This class represents the game behavior for the Pocket Simon game.
 * It implements the IGameBehaviour interface and ActionListener and MouseListener for handling events.
 */
public class GameBehaviourService implements IGameBehaviour, ActionListener, MouseListener {

    /**
     * Singleton instance of the GameBehaviour class.
     */
    public static GameBehaviourService gameBehaviour;
    /**
     * Instance of the ServiceFactory class used to access various services like audio and logging.
     */
    private final ServiceFactory service;
    private final Timer timer;
    /**
     * Instance of the FormRenderer class used to render the game GUI.
     */
    public FormRendererService renderer;
    /**
     * The number representing the current button that is flashing. 0 means no button is flashing.
     */
    public int flashed = 0;
    /**
     * The number of ticks since the game started. A tick is a unit of time used in the game.
     */
    public int ticks;
    /**
     * The current index in the pattern that the player needs to follow.
     */
    public int indexPattern;
    /**
     * The number of ticks until the game goes dark. When it reaches 0, the game goes dark.
     */
    public int dark;
    /**
     * A boolean indicating whether a new pattern should be created. If true, a new pattern is created.
     */
    public boolean createPattern = true;
    /**
     * The pattern that the player needs to follow. It is a list of integers where each integer represents a button.
     */
    public ArrayList<Integer> pattern = new ArrayList<>();
    /**
     * A random number generator used to create the pattern.
     */
    public Random randomizer;
    /**
     * A boolean indicating whether the game is over. If true, the game is over.
     */

    public boolean gameOver;
    private eSoundMode soundMode = eSoundMode.SOUND_ON;
    private eColorMode colorMode = eColorMode.COLOR_ON;

    /**
     * Constructor for the GameBehaviour class.
     * Initializes the renderer and sets the static gameBehaviour variable to this instance.
     * Also initializes the timer and service for the game.
     */
    public GameBehaviourService() {
        renderer = new FormRendererService();
        gameBehaviour = this;
        timer = new Timer(20, this);
        service = new ServiceFactory();

    }

    public void startGame(eSoundMode soundMode, eColorMode colorMode) {
        this.colorMode = colorMode;
        this.soundMode = soundMode;
        this.startGame();
    }

    public void exitGame() {
        System.exit(0);
    }

    /**
     * Create ch.joth.games.pocketsimon.game.Main GUI Frame. and Start Game
     */
    public void startGame() {
        // Start the game
        JFrame frame = new JFrame(service.ConfigService().getValue(eConfigValues.GAME_TITLE));
        frame.setSize(WIDTH + 8, HEIGHT + 30);
        frame.setVisible(true);
        frame.addMouseListener(this);
        frame.setResizable(false);
        frame.add(this.renderer);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGameVariables();
        timer.start();
    }

    /**
     * This method starts the game by initializing the game variables.
     */
    public void initGameVariables() {
        pattern = new ArrayList<>();
        randomizer = new Random();
        indexPattern = 0;
        dark = 2;
        flashed = 0;
        ticks = 0;
    }

    /**
     * This method is called when an action event occurs.
     * It handles the game logic for creating the pattern and flashing the buttons.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ticks++;

        if (ticks % 20 == 0) {
            flashed = 0;

            if (dark >= 0) {
                dark--;
            }
        }

        if (createPattern) {
            if (dark <= 0) {
                if (indexPattern >= pattern.size()) {
                    flashed = randomizer.nextInt(40) % 4 + 1;
                    pattern.add(flashed);
                    indexPattern = 0;
                    createPattern = false;
                } else {
                    flashed = pattern.get(indexPattern);
                    indexPattern++;
                }

                dark = 2;
            }
        } else if (indexPattern == pattern.size()) {
            createPattern = true;
            indexPattern = 0;
            dark = 2;
        }

        renderer.repaint();
    }

    /**
     * This method paints the game GUI.
     *
     * @param g The Graphics2D object to paint on.
     */
    public void paint(Graphics2D g) {

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        addButtons(g);
        addLayout(g);
        String gameoverEmoji = service.ConfigService().getValue(eConfigValues.LOOSE_EMOJI);
        String delimiterSymbol = service.ConfigService().getValue(eConfigValues.DELIMITER_Symbol);
        if (gameOver) {
            g.drawString(gameoverEmoji, WIDTH / 2 - 100, HEIGHT / 2 + 42);

        } else {
            g.drawString(indexPattern + delimiterSymbol + pattern.size(), WIDTH / 2 - 100, HEIGHT / 2 + 42);
        }
    }

    private void addButtons(Graphics2D g) {
        if (flashed == 1) {
            this.setColor(Color.GREEN, g, true);
            playSound(eSoundFile.Green);
        } else {
            this.setColor(Color.GREEN, g, false);

        }

        g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

        if (flashed == 2) {
            this.setColor(Color.RED, g, true);
            playSound(eSoundFile.Red);

        } else {
            this.setColor(Color.RED, g, false);

        }

        g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

        if (flashed == 3) {
            this.setColor(Color.ORANGE, g, true);
            playSound(eSoundFile.Yellow);

        } else {
            this.setColor(Color.ORANGE, g, false);
        }

        g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

        if (flashed == 4) {
            this.setColor(Color.BLUE, g, true);
            playSound(eSoundFile.Blue);

        } else {
            this.setColor(Color.BLUE, g, false);
        }
    }

    private static void addLayout(Graphics2D g) {
        g.fillRect(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

        g.setColor(Color.BLACK);
        g.fillRoundRect(220, 220, 350, 350, 350, 350);

        g.setColor(Color.GRAY);
        g.setStroke(new BasicStroke(200));
        g.drawOval(-100, -100, WIDTH + 200, HEIGHT + 200);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        g.drawOval(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 142));
    }

    /**
     * This method is called when the mouse button has been clicked (pressed and released) on a component.
     *
     * @param e The MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * This method is used to get the current color mode of the game.
     *
     * @return colorMode - The current color mode of the game.
     */
    public eColorMode getColorMode() {
        return colorMode;
    }

    /**
     * This method is used to get the current sound mode of the game.
     *
     * @return soundMode - The current sound mode of the game.
     */
    public eSoundMode getSoundMode() {
        return soundMode;
    }

    /**
     * This method is called when a mouse button has been pressed on a component.
     * It handles the game logic for button presses.
     *
     * @param e The MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();

        if (!createPattern && !gameOver) {
            if (FormRendererService.isGreenButton(x, y)) {
                service.LoggingService().log("Green button clicked", INFO, this.getClass());
                flashed = 1;
                ticks = 1;
                playSound(eSoundFile.Green);


            } else if (FormRendererService.isRedButton(x, y)) {
                service.LoggingService().log("Red button clicked", INFO, this.getClass());

                flashed = 2;
                ticks = 1;
                playSound(eSoundFile.Red);


            } else if (FormRendererService.isYellowButton(x, y)) {
                service.LoggingService().log("Yellow button clicked", INFO, this.getClass());

                flashed = 3;
                ticks = 1;
                playSound(eSoundFile.Yellow);

            } else if (FormRendererService.isBlueButton(x, y)) {
                service.LoggingService().log("Blue button clicked", INFO, this.getClass());
                flashed = 4;
                ticks = 1;
                playSound(eSoundFile.Blue);

            }

            if (flashed != 0) {
                if (pattern.get(indexPattern) == flashed) {
                    indexPattern++;
                } else {
                    playSound(eSoundFile.Fail);
                    service.LoggingService().log("Game Over", WARN, this.getClass(), "flashed: " + flashed);
                    gameOver = true;
                }
            }
        } else if (gameOver) {
            service.LoggingService().log("Restart Game after Game Over", INFO, this.getClass(), "flashed: " + flashed);
            initGameVariables();
            gameOver = false;
        }

    }

    void setColor(Color color, Graphics2D g, Boolean flashed) {
        if (this.colorMode == eColorMode.COLOR_ON) {
            if (flashed) {
                g.setColor(color);
            } else {
                g.setColor(color.darker());
            }
        } else if (this.colorMode == eColorMode.Color_Audio_Only) {
            g.setColor(color);
        } else {
            if (flashed) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.lightGray);
            }
        }
    }

    private void playSound(eSoundFile soundFile) {
        if (this.soundMode == eSoundMode.SOUND_ON || this.colorMode == eColorMode.Color_Audio_Only || soundFile == eSoundFile.Fail) {
            service.AudioService().playSound(soundFile);
        }

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }


}
