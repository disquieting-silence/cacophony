package dsq.cacophony.grate;

import dsq.cacophony.control.Control;
import dsq.cacophony.shame.H;
import dsq.cacophony.ui.Ocelot;

public interface ChannelOp {
    boolean op(H<Ocelot, Control, Boolean> g);
}
