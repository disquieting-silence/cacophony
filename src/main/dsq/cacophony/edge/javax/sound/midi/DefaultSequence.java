package dsq.cacophony.edge.javax.sound.midi;

import javax.sound.midi.InvalidMidiDataException;

public class DefaultSequence implements Sequence {
    
    private final javax.sound.midi.Sequence edge;

    public DefaultSequence(final float divisionType, final int resolution) {
        try {
            edge = new javax.sound.midi.Sequence(divisionType, resolution);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public javax.sound.midi.Sequence unedge() {
        return edge;
    }

    public Track createTrack() {
        final javax.sound.midi.Track track = edge.createTrack();
        return new DefaultTrack(track);
    }

    public void deleteTrack(final Track track) {
        edge.deleteTrack(track.unedge());
    }
}
