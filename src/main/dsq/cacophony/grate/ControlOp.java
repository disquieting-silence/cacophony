package dsq.cacophony.grate;

import dsq.cacophony.control.Control;
import dsq.cacophony.shame.G;

public interface ControlOp {
    boolean op(G<Control, Boolean> g);
}
