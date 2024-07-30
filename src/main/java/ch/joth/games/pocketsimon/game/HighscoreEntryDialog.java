package ch.joth.games.pocketsimon.game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighscoreEntryDialog extends JDialog {
    private JTextField nameField;
    private JSpinner scoreSpinner;

    public HighscoreEntryDialog(JFrame parent, int points) {
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

        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 50; i++) {
                    new HighscoreModel().addEntry(new HighscoreEntry(nameField.getText().trim() + "_Test", points));

                }
                dispose();
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnAdd);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getNameField() {
        return nameField.getText().trim();
    }

    public int getScore() {
        return (int) scoreSpinner.getValue();
    }


}
