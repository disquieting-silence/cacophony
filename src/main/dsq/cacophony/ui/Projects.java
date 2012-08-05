package dsq.cacophony.ui;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.io.Project;

import java.util.List;

// FIX 17/03/12 Really the wrong place for this.
public interface Projects {

    Project project(final MidiInstruments instruments, List<Toridor> views);
}
