package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eConfigValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * GameSettingsView class extends JFrame and represents the game settings view.
 * It allows the user to select the color mode and sound mode for the game.
 */
public class GameSettingsView extends JFrame implements Serializable {
    /**
     * ServiceFactory instance used to access various services.
     */
    final ServiceFactory serviceFactory = new ServiceFactory(); //NOSONAR
    /**
     * JLabel for the color mode selection.
     */
    JLabel labelButtonColor;
    /**
     * JRadioButton for the No Color mode selection.
     */
    JRadioButton radioButtonNoColor;
    /**
     * JRadioButton for the Color mode selection.
     */
    JRadioButton radioButtonColor;
    /**
     * JRadioButton for the Multi Button mode selection.
     */
    JRadioButton radioButtonColorMultiButton;
    /**
     * JRadioButton for the Audio Only mode selection.
     */
    JRadioButton radioButtonAudioOnlyMode;
    /**
     * ButtonGroup for the color mode selection.
     */
    ButtonGroup buttonGroupColorMode;
    /**
     * JLabel for the sound mode selection.
     */
    JLabel labelSoundMode;
    /**
     * JRadioButton for the No Sound mode selection.
     */
    JRadioButton radioButtonNoSound;

    /**
     * JRadioButton for the Sound mode selection.
     */
    JRadioButton radioButtonSound;

    /**
     * ButtonGroup for the sound mode selection.
     */
    ButtonGroup buttonGroupSoundMode;
    /**
     * JButton for submitting the selected settings.
     */
    JButton submitButton;

    /**
     * Constructor for the GameSettingsView class.
     * Initializes the game settings view with the title, size, default close operation, layout, and location.
     * Also initializes the components for the color mode and sound mode selection.
     */
    public GameSettingsView() {
        this.setTitle(serviceFactory.ConfigService().getValue(eConfigValues.GAME_TITLE) + " - Game Setup");
        this.setSize(Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_WIDTH)), Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_HEIGHT)));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        int frameWidth = Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_WIDTH)) + 300;
        int frameHeight = Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_HEIGHT));
        this.setSize(frameWidth, frameHeight);
        this.add(getPanel());
    }

    /**
     * Returns the JPanel for the game settings view.
     * The panel contains components for selecting the color mode and sound mode.
     *
     * @return the JPanel for the game settings view
     */
    public JPanel getPanel() {

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        setColorPanel(settingsPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = buttonGroupColorMode.getButtonCount() + 1;


        JPanel panelSoundMode = setSoundPanel(gridBagConstraints);


        submitButton = new JButton("Spielen!");
        submitButton.setEnabled(false);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        settingsPanel.add(panelSoundMode, gridBagConstraints);
        gridBagConstraints.gridy = 3;

        settingsPanel.add(submitButton, gridBagConstraints);
        return settingsPanel;
    }

    private void setColorPanel(JPanel settingsPanel, GridBagConstraints gridBagConstraints) {
        JPanel panelButtonColor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonGroupColorMode = new ButtonGroup();
        labelButtonColor = new JLabel("Color mode:");
        radioButtonNoColor = new JRadioButton("Single Color Mode");
        radioButtonColor = new JRadioButton("Simon Color Mode");
        radioButtonAudioOnlyMode = new JRadioButton("Audio only Mode");
        radioButtonColorMultiButton = new JRadioButton("Multi Button Mode (Alpha)");
        buttonGroupColorMode.add(radioButtonNoColor);
        buttonGroupColorMode.add(radioButtonColor);
        buttonGroupColorMode.add(radioButtonColorMultiButton);
        buttonGroupColorMode.add(radioButtonAudioOnlyMode);

        panelButtonColor.add(labelButtonColor);
        panelButtonColor.add(radioButtonNoColor);
        panelButtonColor.add(radioButtonColor);
        panelButtonColor.add(radioButtonAudioOnlyMode);
        panelButtonColor.add(radioButtonColorMultiButton);
        settingsPanel.add(panelButtonColor, gridBagConstraints);
        labelSoundMode = new JLabel("Sound mode:");
        radioButtonSound = new JRadioButton("Sound enabled");
        radioButtonNoSound = new JRadioButton("Sound disabled");
    }

    private JPanel setSoundPanel(GridBagConstraints gridBagConstraints) {
        JPanel panelSoundMode = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonGroupSoundMode = new ButtonGroup();
        buttonGroupSoundMode.add(radioButtonSound);
        buttonGroupSoundMode.add(radioButtonNoSound);
        panelSoundMode.add(labelSoundMode);
        panelSoundMode.add(radioButtonNoSound);
        panelSoundMode.add(radioButtonSound);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        return panelSoundMode;
    }

    /**
     * Sets the action listener for the buttons in the game settings view.
     *
     * @param listener the ActionListener to be set for the buttons
     */
    public void setButtonActionListener(ActionListener listener) {
        submitButton.addActionListener(listener);
        radioButtonColor.addActionListener(listener);
        radioButtonNoColor.addActionListener(listener);
        radioButtonAudioOnlyMode.addActionListener(listener);
        radioButtonNoSound.addActionListener(listener);
        radioButtonSound.addActionListener(listener);
        radioButtonColorMultiButton.addActionListener(listener);

    }

    /**
     * Retrieves SubmitButton Instance.
     *
     * @return the SubmitButton for starting a Game.
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

}
