package dsq.cacophony.operation.channel;

import dsq.cacophony.control.Control;
import dsq.cacophony.grate.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.H;
import dsq.cacophony.ui.Ocelot;

public class RightOperation implements Operation {
    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return channels.op(new H<Ocelot, Control, Boolean>() {
            public Boolean h(final Ocelot ocelot, final Control control) {
                ocelot.right();
                return true;
            }
        });
    }
}
