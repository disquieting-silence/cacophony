package dsq.cacophony.edge.javax.sound.midi;

public class DefaultPatch implements Patch {
    
    private final javax.sound.midi.Patch edge;

    public DefaultPatch(final javax.sound.midi.Patch edge) {
        this.edge = edge;
    }

    public int getBank() {
        return edge.getBank();
    }

    
    public int getProgram() {
        return edge.getProgram();
    }
}
