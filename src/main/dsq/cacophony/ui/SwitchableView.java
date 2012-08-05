package dsq.cacophony.ui;

import dsq.cacophony.shame.Option;

import java.awt.Component;

public interface SwitchableView {
    // FIX 17/03/12 Disappointed that this isn't generalised, but c'est la vie
    boolean show(Toridor view);
    Component view();
    Option<Toridor> current();
    void setSelected(boolean selected);
}
