package dsq.cacophony.event.note;

import dsq.cacophony.data.Tick;
import dsq.cacophony.edge.javax.sound.midi.DefaultMidiEvent;
import dsq.cacophony.edge.javax.sound.midi.DefaultShortMessage;
import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Key;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.edge.javax.sound.midi.ShortMessage;

public class DefaultMidiNotes implements MidiNotes {

    private static final int VELOCITY = 64;
    
    public MidiEvent start(final Key key, final Tick tick, final Channel channel) {
        return make(ShortMessage.NOTE_ON, tick, key, channel);
    }

    public MidiEvent stop(final Key key, final Tick tick, final Channel channel) {
        return make(ShortMessage.NOTE_OFF, tick, key, channel);
    }

    private MidiEvent make(final int command, final Tick tick, final Key key, final Channel channel) {
        final ShortMessage message = new DefaultShortMessage();
        message.setMessage(command, channel.value, key.value, VELOCITY);
        return new DefaultMidiEvent(message, tick.value);
    }
}