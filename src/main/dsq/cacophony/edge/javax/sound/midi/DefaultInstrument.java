package dsq.cacophony.edge.javax.sound.midi;

public class DefaultInstrument implements Instrument {
    
    private final javax.sound.midi.Instrument edge;

    public DefaultInstrument(final javax.sound.midi.Instrument edge) {
        this.edge = edge;
    }

    public javax.sound.midi.Instrument unedge() {
        return edge;    
    }
    
    public String getName() {
        return edge.getName();
    }

    public Patch getPatch() {
        return new DefaultPatch(edge.getPatch());
    }
}
