package dsq.cacophony.edge.javax.sound.midi;

public interface Instrument {
    javax.sound.midi.Instrument unedge();
    Patch getPatch();
    String getName();
}
