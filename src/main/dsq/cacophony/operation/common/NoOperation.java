package dsq.cacophony.operation.common;

import dsq.cacophony.grate.*;

public class NoOperation implements Operation {
    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return noop.op();
    }
}
