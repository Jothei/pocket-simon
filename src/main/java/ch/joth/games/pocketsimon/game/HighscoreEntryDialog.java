package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.eColorMode;

import javax.swing.*;
import java.awt.*;

/**
 * HighscoreEntryDialog is a class that represents a dialog for adding a highscore entry.
 * It extends JDialog and provides fields for entering the name and score.
 */
public class HighscoreEntryDialog extends JDialog {
    /**
     * Field for entering the name.
     */
    private JTextField nameField;
    /**
     * Spinner to display the score.
     */
    private JSpinner scoreSpinner;

    /**
     * Constructs a new HighscoreEntryDialog.
     * Initializes the dialog with the given parent frame and points.
     *
     * @param parent the parent JFrame
     * @param points preview of the points to be displayed in the score and added to the highscore entry
     * @param mode   the color mode of the game
     */
    public HighscoreEntryDialog(JFrame parent, int points, eColorMode mode) {
        super(parent, "Add Highscore Entry", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(nameLabel, cs);

        nameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(nameField, cs);

        JLabel scoreLabel = new JLabel("Score: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(scoreLabel, cs);

        scoreSpinner = new JSpinner(new SpinnerNumberModel(points, 0, 10, Integer.MAX_VALUE));
        scoreSpinner.setEnabled(false);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(scoreSpinner, cs);

        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> {
            new HighscoreModel().addEntry(new HighscoreEntry(getNameField(), getScore(), mode));
            dispose();
        });
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose());

        JPanel bp = new JPanel();
        bp.add(btnAdd);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    /**
     * Retrieves the text from the name field.
     *
     * @return the name entered in the name field
     */
    public String getNameField() {
        return nameField.getText().trim();
    }

    /**
     * Retrieves the value from the score spinner.
     *
     * @return the score entered in the score spinner
     */
    public int getScore() {
        return (int) scoreSpinner.getValue();
    }
}