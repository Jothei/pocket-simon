package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.HighscoreEntryDialog;
import ch.joth.games.pocketsimon.game.HighscoreView;
import ch.joth.games.pocketsimon.game.code.*;
import ch.joth.games.pocketsimon.game.code.codedefinition.IGameBehaviour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.SecureRandom;
import java.util.ArrayList;

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
    static GameBehaviourService gameBehaviour;


    /**
     * Instance of the ServiceFactory class used to access various services like audio and logging.
     */
    final ServiceFactory service;
    /**
     * Instance of the Timer class used to handle the game ticks.
     */
    private final Timer timer;
    /**
     * Instance of the FormRenderer class used to render the game GUI.
     */
    private FormRendererService renderer;
    /**
     * The number representing the current button that is flashing. 0 means no button is flashing.
     */
    private int flashed = 0;
    /**
     * The number of ticks since the game started. A tick is a unit of time used in the game.
     */
    private int ticks;
    /**
     * The current index in the pattern that the player needs to follow.
     */
    private int indexPattern;
    /**
     * The number of ticks until the game goes dark. When it reaches 0, the game goes dark.
     */
    private int dark;
    /**
     * A boolean indicating whether a new pattern should be created. If true, a new pattern is created.
     */
    private boolean createPattern = true;
    /**
     * The pattern that the player needs to follow. It is a list of integers where each integer represents a button.
     */
    private ArrayList<Integer> pattern = new ArrayList<>();
    /**
     * A random number generator used to create the pattern.
     */
    private SecureRandom randomizer;
    /**
     * A boolean indicating whether the game is over. If true, the game is over.
     */
    private boolean gameOver;
    /**
     * The sound mode of the game. It can be SOUND_ON, SOUND_OFF.
     */
    private eSoundMode soundMode = eSoundMode.SOUND_ON;
    /**
     * The color mode of the game. It can be COLOR_ON, COLOR_OFF or Color_Audio_Only.
     */
    private eColorMode colorMode = eColorMode.COLOR_ON;
    /**
     * The JFrame used to display the game GUI.
     */
    private JFrame gameFrame;
    /**
     * The highscore entry dialog used to enter the player's name.
     */
    private HighscoreEntryDialog dialog;
    private Integer buttonCount = 4;
    private SecureRandom rand = new SecureRandom();
    private JPanel buttonPanel;

    /**
     * Constructor for the GameBehaviour class.
     * Initializes the renderer and sets the static gameBehaviour variable to this instance.
     * Also initializes the timer and service for the game.
     */
    public GameBehaviourService() {
        renderer = new FormRendererService();
        timer = new Timer(20, this);
        service = new ServiceFactory();
        gameBehaviour = this; //NOSONAR - Fix to prevent Buttons from being not displayed
    }

    /**
     * This method is used to get the instance of the GameBehaviour class. The View will be visible after calling this method.
     */
    public void showHighscore() {
        HighscoreView view = new HighscoreView();
        view.display();
    }

    /**
     * Initializes the highscore entry dialog with the given parent frame and points.
     *
     * @param parent the parent JFrame
     * @param points the points to be displayed in the dialog
     */
    public void initHighscoreInsertDialog(JFrame parent, int points) {
        dialog = new HighscoreEntryDialog(parent, points);
        showHighscoreDialog();
    }

    /**
     * Displays the highscore entry dialog.
     */
    private void showHighscoreDialog() {
        dialog.setVisible(true);
    }

    /**
     * Starts the game with the specified sound and color modes.
     *
     * @param soundMode the sound mode to be used
     * @param colorMode the color mode to be used
     */
    public void startGame(eSoundMode soundMode, eColorMode colorMode) {
        this.colorMode = colorMode;
        this.soundMode = soundMode;
        if (this.colorMode == eColorMode.COLOR_MULTI_BUTTONS) {
            this.buttonCount = 6;
            startMultiButtonGame();
        } else {
            this.buttonCount = 4;
            this.startGame();
        }
    }

    /**
     * Exits the whole game.
     */
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Create ch.joth.games.pocketsimon.game.Main GUI Frame. and Start Game
     */
    public void startGame() {
        // Start the game
        if (!GraphicsEnvironment.isHeadless()) {
            this.gameFrame = new JFrame(service.ConfigService().getValue(eConfigValues.GAME_TITLE));
            setGameFrameSettings();
            timer.start();
        } else {
            service.LoggingService().log("Headless mode is enabled. Game cannot be started.", WARN, this.getClass());
        }


    }


    void startMultiButtonGame() {
        if (!GraphicsEnvironment.isHeadless()) {

            this.gameFrame = new JFrame(service.ConfigService().getValue(eConfigValues.GAME_TITLE));

            buttonPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    arrangeButtonsInCircle();

                }
            };
            buttonPanel.setLayout(null);
            for (int i = 1; i <= buttonCount; i++) {
                addNewButton(i);
            }
            JScrollPane scrollPane = new JScrollPane(buttonPanel);
            this.gameFrame.add(scrollPane, BorderLayout.CENTER);

            this.setGameFrameSettings();
            renderer.repaint();
            timer.start();

        } else {
            service.LoggingService().log("Headless mode is enabled. Game cannot be started.", WARN, GameBehaviourService.class);
        }
    }

    /**
     * This method initializing the game variables before the game can start.
     */
    public void initGameVariables() {
        pattern = new ArrayList<>();
        randomizer = new SecureRandom();
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
                    flashed = randomizer.nextInt(40) % this.buttonCount + 1;
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
        if (this.colorMode != eColorMode.COLOR_MULTI_BUTTONS) {
            addButtonsPaint(g);
            addLayout(g);
        } else {
            addMultiButtonsPaint();
            arrangeButtonsInCircle();
        }
        String gameoverEmoji = service.ConfigService().getValue(eConfigValues.LOOSE_EMOJI);
        String delimiterSymbol = service.ConfigService().getValue(eConfigValues.DELIMITER_SYMBOL);
        if (gameOver) {
            g.drawString(gameoverEmoji, WIDTH / 2 - 100, HEIGHT / 2 + 42);
        } else {
            g.drawString(indexPattern + delimiterSymbol + pattern.size(), WIDTH / 2 - 100, HEIGHT / 2 + 42);
        }
    }


    /**
     * Adds the buttons to the game GUI.
     *
     * @param g The Graphics2D object to paint on.
     */
    private void addButtonsPaint(Graphics2D g) {
        if (flashed == 1) {
            this.setColor(Color.GREEN, g, true);
            playSound(eSoundFile.GREEN);
        } else {
            this.setColor(Color.GREEN, g, false);
        }

        g.fillRect(0, 0, WIDTH / 2, HEIGHT / 2);

        if (flashed == 2) {
            this.setColor(Color.RED, g, true);
            playSound(eSoundFile.RED);
        } else {
            this.setColor(Color.RED, g, false);
        }

        g.fillRect(WIDTH / 2, 0, WIDTH / 2, HEIGHT / 2);

        if (flashed == 3) {
            this.setColor(Color.ORANGE, g, true);
            playSound(eSoundFile.YELLOW);
        } else {
            this.setColor(Color.ORANGE, g, false);
        }

        g.fillRect(0, HEIGHT / 2, WIDTH / 2, HEIGHT / 2);

        if (flashed == 4) {
            this.setColor(Color.BLUE, g, true);
            playSound(eSoundFile.BLUE);
        } else {
            this.setColor(Color.BLUE, g, false);
        }
    }

    /**
     * Adds the buttons to the game GUI for Multi Button Mode.
     */
    private void addMultiButtonsPaint() {
        Component[] components = buttonPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            Graphics2D g = (Graphics2D) components[i].getGraphics();
            this.setColor(components[i].getBackground(), g, flashed == i);
            new ServiceFactory().LoggingService().log("Button " + (i + 1) + " flashed: " + (flashed == i), INFO, GameBehaviourService.class);
        }
    }

    /**
     * Adds the layout to the game GUI.
     *
     * @param g The Graphics2D object to paint on.
     */
    void addLayout(Graphics2D g) {
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
        // Add comment to prevent JavaDoc warning.
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
        if (this.colorMode == eColorMode.COLOR_MULTI_BUTTONS) {
            multiButtonMousePressEvent(e);
        } else {
            regularMousePressEvent(e);
        }
    }

    private void flashedIteration() {
        if (flashed != 0) {
            if (pattern.get(indexPattern) == flashed) {
                indexPattern++;
            } else {
                playSound(eSoundFile.FAIL);
                service.LoggingService().log("Game Over", WARN, this.getClass(), "flashed: " + flashed);
                initHighscoreInsertDialog(this.gameFrame, pattern.size());
                gameOver = true;
            }
        }
    }

    private void multiButtonMousePressEvent(MouseEvent e) {
        Point clickPoint = e.getPoint();
        Component[] components = buttonPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (!createPattern && !gameOver) {

                if (components[i].getBounds().contains(clickPoint)) {
                    flashed = i;
                    ticks = 1;
                    new ServiceFactory().LoggingService().log("Button " + (i + 1) + " pressed", INFO, GameBehaviourService.class);
                }
                flashedIteration();
            }
        }
    }


    private void regularMousePressEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (!createPattern && !gameOver) {
            if (FormRendererService.isGreenButton(x, y)) {
                service.LoggingService().log("Green button clicked", INFO, this.getClass());
                flashed = 1;
                ticks = 1;
                playSound(eSoundFile.GREEN);
            } else if (FormRendererService.isRedButton(x, y)) {
                service.LoggingService().log("Red button clicked", INFO, this.getClass());
                flashed = 2;
                ticks = 1;
                playSound(eSoundFile.RED);
            } else if (FormRendererService.isYellowButton(x, y)) {
                service.LoggingService().log("Yellow button clicked", INFO, this.getClass());
                flashed = 3;
                ticks = 1;
                playSound(eSoundFile.YELLOW);
            } else if (FormRendererService.isBlueButton(x, y)) {
                service.LoggingService().log("Blue button clicked", INFO, this.getClass());
                flashed = 4;
                ticks = 1;
                playSound(eSoundFile.BLUE);
            }

            flashedIteration();
        } else if (gameOver) {
            service.LoggingService().log("Restart Game after Game Over", INFO, this.getClass(), "flashed: " + flashed);
            initGameVariables();
            gameOver = false;
        }
    }

    /**
     * Sets the color for the game buttons.
     *
     * @param color   the color to be set
     * @param g       the Graphics2D object to paint on
     * @param flashed a boolean indicating whether the button is flashed
     */
    void setColor(Color color, Graphics2D g, Boolean flashed) {


        if (this.colorMode == eColorMode.COLOR_ON) {
            if (flashed) {
                g.setColor(color);
            } else {
                g.setColor(color.darker());
            }

        } else if (this.colorMode == eColorMode.COLOR_AUDIO_ONLY) {
            g.setColor(color);
        } else {
            if (flashed) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.lightGray);
            }
        }
    }


    /**
     * Plays the specified sound file.
     *
     * @param soundFile the sound file to be played
     */
    void playSound(eSoundFile soundFile) {
        if (this.soundMode == eSoundMode.SOUND_ON || this.colorMode == eColorMode.COLOR_AUDIO_ONLY || soundFile == eSoundFile.FAIL) {
            service.AudioService().playSound(soundFile);
        }
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // Added comment to prevent JavaDoc warning.
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // Added comment to prevent JavaDoc warning.
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // Added comment to prevent JavaDoc warning.
    }

    /**
     * This method is used to get if the game is over or not.
     *
     * @return Return the state of Gameover.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets the game over status.
     *
     * @param gameOver sets the game over status
     */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    private void addNewButton(int number) {
        JButton newButton = new JButton(String.valueOf(number));
        newButton.setBackground(FormRendererService.getRandomColor(this.rand));
        newButton.addActionListener(e -> new ServiceFactory().LoggingService().log("Button " + number + " pressed", INFO, GameBehaviourService.class));

        buttonPanel.add(newButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }


    private void arrangeButtonsInCircle() {
        int radius = 200; // Radius of the circle
        int centerX = buttonPanel.getWidth() / 2;
        int centerY = buttonPanel.getHeight() / 2;
        int buttonSize = 50; // Size of each button

        Component[] components = buttonPanel.getComponents();
        int totalButtons = components.length;
        double angleStep = 2 * Math.PI / totalButtons;

        for (int i = 0; i < totalButtons; i++) {
            double angle = i * angleStep;
            int x = centerX + (int) (radius * Math.cos(angle)) - buttonSize / 2;
            int y = centerY + (int) (radius * Math.sin(angle)) - buttonSize / 2;

            components[i].setBounds(x, y, buttonSize, buttonSize);
        }
    }

    private void setGameFrameSettings() {
        this.gameFrame.setSize(WIDTH + 8, HEIGHT + 30);
        this.gameFrame.setResizable(false);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setEnabled(true);
        this.gameFrame.setVisible(true);
        this.gameFrame.addMouseListener(this);
        this.gameFrame.add(renderer);
        initGameVariables();
    }

}