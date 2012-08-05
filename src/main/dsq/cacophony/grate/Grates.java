package dsq.cacophony.grate;

import dsq.cacophony.data.GrateModes;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.Option;

import java.awt.event.KeyEvent;

public interface Grates {
    Option<GrateModes> getMode();
    void register(GrateModes tag, Grate grate);
    Operation handle(KeyEvent event);
    void setMode(GrateModes mode);
}
