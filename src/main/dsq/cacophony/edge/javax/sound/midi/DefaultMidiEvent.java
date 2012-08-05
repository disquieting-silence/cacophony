package dsq.cacophony.edge.javax.sound.midi;

public class DefaultMidiEvent implements MidiEvent {
    
    private final javax.sound.midi.MidiEvent edge;

    public DefaultMidiEvent(final javax.sound.midi.MidiEvent edge) {
        this.edge = edge;
    }

    public DefaultMidiEvent(final ShortMessage message, final long tick) {
        this.edge = new javax.sound.midi.MidiEvent(message.unedge(), tick);
    }
    
    public DefaultMidiEvent(final MetaMessage message, final long tick) {
        this.edge = new javax.sound.midi.MidiEvent(message.unedge(), tick);
    }

    public javax.sound.midi.MidiEvent unedge() {
        return edge;
    }
}
