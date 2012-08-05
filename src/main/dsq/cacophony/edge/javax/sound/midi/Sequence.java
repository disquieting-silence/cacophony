package dsq.cacophony.edge.javax.sound.midi;


public interface Sequence {
    javax.sound.midi.Sequence unedge();
    Track createTrack();
    void deleteTrack(Track track);
}
