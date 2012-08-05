package dsq.cacophony.event.note;

import dsq.cacophony.data.Tick;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Key;

public interface MidiNotes {
    MidiEvent start(Key key, Tick tick, Channel channel);
    MidiEvent stop(Key key, Tick tick, Channel channel);
}
