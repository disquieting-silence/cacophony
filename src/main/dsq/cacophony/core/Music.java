package dsq.cacophony.core;

import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.shame.Option;

import java.util.List;

public interface Music {
    List<MidiEvent> music(Option<Instrument> instrumentOption, List<Note> notes, Channel channel);
}
