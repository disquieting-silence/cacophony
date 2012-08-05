package dsq.cacophony.operation.system;

import dsq.cacophony.core.SoundSystem;
import dsq.cacophony.grate.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.H;
import dsq.cacophony.ui.ToridorManager;

public class QuitOperation implements Operation {
    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return systems.op(new H<SoundSystem, ToridorManager, Boolean>() {
            public Boolean h(final SoundSystem soundSystem, final ToridorManager toridorManager) {
                soundSystem.shutdown();
                System.exit(0);
                // FIX 17/03/12 How odd.
                return true;
            }
        });
    }
}
