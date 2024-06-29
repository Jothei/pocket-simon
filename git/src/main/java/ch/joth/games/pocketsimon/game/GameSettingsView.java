package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.eConfigValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class GameSettingsView extends JFrame {
    public ServiceFactory serviceFactory = new ServiceFactory();
    JLabel labelButtonColor;
    JRadioButton radioButtonNoColor;
    JRadioButton radioButtonColor;
    JRadioButton radioButtonAudioOnlyMode;
    ButtonGroup buttonGroupColorMode;
    JLabel labelSoundMode;
    JRadioButton radioButtonNoSound;
    JRadioButton radioButtonSound;
    ButtonGroup buttonGroupSoundMode;
    JButton submitButton;

    public GameSettingsView() {
        this.setTitle(serviceFactory.ConfigService().getValue(eConfigValues.GAME_TITLE) + " - Game Setup");
        this.setSize(Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_WIDTH)), Integer.parseInt(serviceFactory.ConfigService().getValue(eConfigValues.MENU_HEIGHT)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        // Initialize Components
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        JPanel panelButtonColor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelButtonColor = new JLabel("Color mode:");
        radioButtonNoColor = new JRadioButton("Single Color Mode");
        radioButtonColor = new JRadioButton("Simon Color Mode");
        radioButtonAudioOnlyMode = new JRadioButton("Audio only Mode");
        labelSoundMode = new JLabel("Sound mode:");
        radioButtonSound = new JRadioButton("Sound enabled");
        radioButtonNoSound = new JRadioButton("Sound disabled");
        buttonGroupColorMode = new ButtonGroup();
        buttonGroupColorMode.add(radioButtonNoColor);
        buttonGroupColorMode.add(radioButtonColor);
        buttonGroupColorMode.add(radioButtonAudioOnlyMode);
        panelButtonColor.add(labelButtonColor);
        panelButtonColor.add(radioButtonNoColor);
        panelButtonColor.add(radioButtonColor);
        panelButtonColor.add(radioButtonAudioOnlyMode);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        settingsPanel.add(panelButtonColor, gridBagConstraints);

        JPanel panelSoundMode = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonGroupSoundMode = new ButtonGroup();
        buttonGroupSoundMode.add(radioButtonSound);
        buttonGroupSoundMode.add(radioButtonNoSound);
        panelSoundMode.add(labelSoundMode);
        panelSoundMode.add(radioButtonNoSound);
        panelSoundMode.add(radioButtonSound);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        settingsPanel.add(panelSoundMode, gridBagConstraints);

        submitButton = new JButton("Spielen!");
        submitButton.setEnabled(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        settingsPanel.add(submitButton, gridBagConstraints);
        this.add(settingsPanel);
    }

    public void setButtonActionListener(ActionListener listener) {
        submitButton.addActionListener(listener);
        radioButtonColor.addActionListener(listener);
        radioButtonNoColor.addActionListener(listener);
        radioButtonAudioOnlyMode.addActionListener(listener);
        radioButtonNoSound.addActionListener(listener);
        radioButtonSound.addActionListener(listener);
    }




}
