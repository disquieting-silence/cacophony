package dsq.cacophony.ui;

import javax.swing.*;
import java.awt.*;

public class DefaultStatusBar implements StatusBar {

    // FIX 17/03/12 It would be *great* if this faded after some amount of time.
    private final JLabel label = new JLabel();
    private final JPanel panel = new JPanel();

    public DefaultStatusBar() {
        panel.add(label);
        label.setForeground(Color.WHITE);
        panel.setBackground(Color.GRAY);
    }

    public void setMessage(final String message) {
        label.setText(message);
    }

    public void clearMessage() {
        label.setText("");
    }

    public Container view() {
        return panel;
    }
}
