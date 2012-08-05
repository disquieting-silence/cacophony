package dsq.cacophony.ui;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.io.Project;

import java.util.List;

// FIX 17/03/12 Rename me.
public interface Meerkat {
    List<Toridor> views(Project project, final MidiInstruments instruments);
}
