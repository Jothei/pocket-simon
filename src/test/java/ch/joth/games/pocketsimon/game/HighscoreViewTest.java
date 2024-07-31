package ch.joth.games.pocketsimon.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HighscoreViewTest {

    @Mock
    HighscoreModel modelMock;
    HighscoreView view;

    JFrame frame;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        modelMock = mock(HighscoreModel.class);
        view = new HighscoreView();
    }

    @Disabled
    @Test
    void constructor_initializesFrame() throws NoSuchFieldException, IllegalAccessException {
        Field frameField = HighscoreView.class.getDeclaredField("frame");
        frameField.setAccessible(true);
        frame = (JFrame) frameField.get(view);
        assertNotNull(frame);
        assertEquals("Leaderboard", frame.getTitle());
        assertEquals(WindowConstants.DISPOSE_ON_CLOSE, frame.getDefaultCloseOperation());
        assertEquals(400, frame.getWidth());
        assertEquals(300, frame.getHeight());
    }


    @Disabled
    @Test
    void tableModel_containsCorrectColumns() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");
        tableModel.addColumn("Date");

        assertEquals(3, tableModel.getColumnCount());
        assertEquals("Name", tableModel.getColumnName(0));
        assertEquals("Score", tableModel.getColumnName(1));
        assertEquals("Date", tableModel.getColumnName(2));
    }

    @Disabled
    @Test
    void tableModel_containsCorrectRows() {
        HighscoreEntry entry1 = new HighscoreEntry("Player1", 100);
        HighscoreEntry entry2 = new HighscoreEntry("Player2", 200);
        view = spy(new HighscoreView());
        when(view.getRows()).thenReturn(List.of(entry1, entry2));

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Score");
        tableModel.addColumn("Date");
        for (HighscoreEntry entry : view.getRows()) {
            tableModel.addRow(new Object[]{entry.getName(), entry.getScore(), entry.getDate()});
        }

        assertEquals(2, tableModel.getRowCount());
        assertEquals("Player1", tableModel.getValueAt(0, 0));
        assertEquals(100, tableModel.getValueAt(0, 1));
        assertNotNull(tableModel.getValueAt(0, 2));
        assertEquals("Player2", tableModel.getValueAt(1, 0));
        assertEquals(200, tableModel.getValueAt(1, 1));
        assertNotNull(tableModel.getValueAt(1, 2));
    }
}