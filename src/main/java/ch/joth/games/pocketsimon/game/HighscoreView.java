package ch.joth.games.pocketsimon.game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class HighscoreView {

    private final JFrame frame;


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
        for (HighscoreEntry entry : this.getRows()) {
            tableModel.addRow(new Object[]{entry.getName(), entry.getScore(), entry.getDate()});
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.DESCENDING)));
        sorter.sort();

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        this.display();

    }


    public void display() {

        frame.setVisible(true);

        new HighscoreController(new HighscoreModel(), this);

    }


    public List<HighscoreEntry> getRows() {
        HighscoreModel model = new HighscoreModel();
        return model.getEntries();
    }
}