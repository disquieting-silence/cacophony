package dsq.cacophony.ui;

import dsq.cacophony.data.Note;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultMusicPanel implements MusicPanel {

    private final Display display = new DefaultDisplay();
    private final Shapes shapes = new DefaultShapes();
    
    private List<Note> notes = new ArrayList<Note>();
    
    private boolean selected = false;
    
    private final JPanel panel = new JPanel() {
        public void paint(final Graphics g) {
            super.paint(g);
            // FIX 3/03/12 Fun with magic numbers.
            display.display(g, notes, 50, 50, getWidth(), getHeight());
            g.setColor(Color.BLUE);
            if (selected) shapes.rect(g, 0, 0, getWidth(), getHeight(), 5);
        }
    };

    public DefaultMusicPanel() {
        panel.setFocusTraversalKeysEnabled(false);
    }

    public void refresh(final List<Note> notes) {
        this.notes = notes;
        // FIX 3/03/12 Should I clear selection here if this view isn't selected?
        panel.repaint();
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
        panel.repaint();
    }

    public Component view() {
        return panel;
    }
}
