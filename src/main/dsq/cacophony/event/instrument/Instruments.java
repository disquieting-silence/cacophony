package dsq.cacophony.event.instrument;

import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Tick;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;

import java.util.List;

public interface Instruments {   
    List<MidiEvent> change(Instrument instrument, Tick tick, Channel channel);

}
