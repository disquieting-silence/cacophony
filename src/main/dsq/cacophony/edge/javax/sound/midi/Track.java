package dsq.cacophony.edge.javax.sound.midi;

public interface Track {
    javax.sound.midi.Track unedge();
    void add(MidiEvent event);
    MidiEvent get(int index);
    boolean remove(MidiEvent event);
    int size();
}
