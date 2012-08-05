package dsq.cacophony.grate;

import dsq.cacophony.core.SoundSystem;
import dsq.cacophony.shame.H;
import dsq.cacophony.ui.ToridorManager;

public interface SystemOp {
    boolean op(H<SoundSystem, ToridorManager, Boolean> h);
}
