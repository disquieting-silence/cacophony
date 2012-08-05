package dsq.cacophony.ui;

import dsq.cacophony.data.Channel;
import dsq.cacophony.shame.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DefaultSwitchableView implements SwitchableView {
    
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel("Channel: NONE");
    private Option<Toridor> v;

    public DefaultSwitchableView() {
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        v = new NoneOption<Toridor>();
        setSelected(false);
    }

    public boolean show(final Toridor view) {
        if (view.view().getParent() != null) return false;
        removeCurrent();
        final Channel channel = view.channel();
        label.setText("Channel: " + (channel.value > -1 ? String.valueOf(channel.value) : " NONE"));
        v = new SomeOption<Toridor>(view);
        panel.add(view.view());
        view.view().repaint();
        return true;
    }

    private void removeCurrent() {
        v.fold(new G<Toridor, Boolean>() {
            public Boolean g(final Toridor input) {
                panel.remove(input.view());
                return true;
            }
        }, new F<Boolean>() {
            public Boolean f() {
                return false;
            }
        });
    }

    public Component view() {
        return panel;
    }

    public Option<Toridor> current() {
        return v;
    }

    public void setSelected(final boolean selected) {
        final Color colour = selected ? Color.BLUE : Color.BLACK;
        panel.setBorder(new LineBorder(colour, 10));
    }
}
