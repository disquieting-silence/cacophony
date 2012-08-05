package dsq.cacophony.operation.control;

import dsq.cacophony.control.Control;
import dsq.cacophony.grate.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.G;

public class IncDurationOperation implements Operation {
    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return controls.op(new G<Control, Boolean>() {
            public Boolean g(final Control control) {
                control.incDuration();
                return true;
            }
        });
    }
}
