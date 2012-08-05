package dsq.cacophony.core;

import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.shame.Option;

import java.util.Set;

public interface MidiInstruments {
    Option<Instrument> get(String name);
    Set<String> search(String pattern);
    Option<Instrument> getDefault();
    
}
