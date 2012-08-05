package dsq.cacophony.ui;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.io.Project;

import java.util.List;

// FIX 3/03/12 Perhaps a rename ...
public interface ToridorManager {

    List<MidiEvent> noteEvents();
    Project project(final MidiInstruments instruments);


}
