package dsq.cacophony.ui;

import javax.swing.*;
import java.awt.*;

public class DefaultInfoPanel implements InfoPanel {


    private int octave = 5;
    private String duration = "crotchet";
    private String mode = "KEYS";
    
    private JPanel panel = new JPanel() {
        public void paint(final Graphics g) {
            super.paint(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            drawLabel(g, "Mode: ", "" + mode, 20);
            drawLabel(g, "Octave: ", "" + octave, 50);
            drawLabel(g, "Duration: ", duration, 80);
        }
    };

    public DefaultInfoPanel() {
        panel.setFocusTraversalKeysEnabled(false);
    }

    private void drawLabel(final Graphics g, final String label, final String value, final int y) {
        g.drawString(label, 10, y);
        g.drawString(value, 100, y);
    }

    public void display(final String mode, final int octave, final String duration) {
        this.mode = mode;
        this.octave = octave;
        this.duration = duration;
        panel.repaint();
    }

    public void refresh() {
        panel.repaint();
    }

    public Component view() {
        return panel;
    }
}
