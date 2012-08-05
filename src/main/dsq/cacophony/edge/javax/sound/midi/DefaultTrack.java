package dsq.cacophony.edge.javax.sound.midi;

public class DefaultTrack implements Track {
    
    private final javax.sound.midi.Track edge;

    public DefaultTrack(final javax.sound.midi.Track edge) {
        this.edge = edge;
    }

    public javax.sound.midi.Track unedge() {
        return edge;
    }

    public void add(final MidiEvent event) {
        edge.add(event.unedge());
    }

    public MidiEvent get(final int index) {
        return new DefaultMidiEvent(edge.get(index));
    }

    public boolean remove(final MidiEvent event) {
        return edge.remove(event.unedge());
    }

    public int size() {
        return edge.size();
    }
}
