package dsq.cacophony.ui;

import dsq.cacophony.data.Key;
import dsq.cacophony.data.LineNumber;

public interface Positioner {
    LineNumber position(Key key);
}
