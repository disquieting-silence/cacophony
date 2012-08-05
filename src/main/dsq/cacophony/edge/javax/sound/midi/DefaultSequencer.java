package dsq.cacophony.edge.javax.sound.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class DefaultSequencer implements Sequencer {
    
    private final javax.sound.midi.Sequencer edge;

    public DefaultSequencer(final javax.sound.midi.Sequencer edge) {
        this.edge = edge;
    }

    public boolean isOpen() {
        return edge.isOpen();
    }

    public void stop() {
        edge.stop();
    }

    public void setTickPosition(final long position) {
        edge.setTickPosition(position);
    }

    public void close() {
        edge.close();
    }

    public void setSequence(final Sequence sequence) {
        try {
            edge.setSequence(sequence.unedge());
        } catch (InvalidMidiDataException e) {
            e.printStackTrace(); 
        }
    }

    public void start() {
        edge.start();
    }

    public void open() {
        try {
            edge.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
