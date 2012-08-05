package dsq.cacophony.operation.ui;

import dsq.cacophony.data.GrateModes;
import dsq.cacophony.grate.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.G;

public class SwitchToCommandOperation implements Operation {
    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return ui.op(new G<Grates, Boolean>() {
            public Boolean g(final Grates input) {
                input.setMode(GrateModes.COMMAND);
                return true;
            }
        });
    }
}
