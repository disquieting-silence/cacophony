package dsq.cacophony.event.tempo;

import dsq.cacophony.data.Tempo;
import dsq.cacophony.data.Tick;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.data.Channel;

public interface Tempos {
    MidiEvent change(Tempo tempo, Tick tick, Channel channel);

}
