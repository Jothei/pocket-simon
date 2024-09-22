package ch.joth.games.pocketsimon.game;

import ch.joth.games.pocketsimon.game.code.eColorMode;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

/**
 * HighscoreView is a class that represents the leaderboard view in the Pocket Simon game.
 * It creates a JFrame to display the highscore table.
 */
public class HighscoreView {

    private final JFrame frame;

    /**
     * Constructs a new HighscoreView.
     * Initializes the JFrame and sets up the highscore table.
     */
    public HighscoreView() {
        frame = new JFrame("Leaderboard");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        tableModel.addColumn("Name");
        tableModel.addColumn("Score");
        tableModel.addColumn("Date");
        tableModel.addColumn("Gamemode");
        for (HighscoreEntry entry : this.getRows()) {
            tableModel.addRow(new Object[]{entry.getName(), entry.getScore(), entry.getDate(), getGamemodeTranslation(entry.getGameMode())});
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.DESCENDING)));
        sorter.sort();

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);


    }

    /**
     * Displays the highscore view by making the JFrame visible.
     * Also initializes the HighscoreController.
     */
    public void display() {
        frame.setVisible(true);
        new HighscoreController(new HighscoreModel(), this);
    }

    /**
     * Retrieves the list of highscore entries.
     *
     * @return a list of HighscoreEntry objects
     */
    public List<HighscoreEntry> getRows() {
        HighscoreModel model = new HighscoreModel();
        return model.getEntries();
    }

    private String getGamemodeTranslation(eColorMode mode) {
        if (mode == null) {
            return "Unknown";
        }
        switch (mode) {
            case COLOR_ON:
                return "Pocket Simon";
            case COLOR_OFF:
                return "Single Color";
            case COLOR_AUDIO_ONLY:
                return "Audio Only";
            case COLOR_MULTI_BUTTONS:
                return "Multi Buttons";
            default:
                return "Unknown";
        }
    }
}