package dsq.cacophony.edge.javax.sound.midi;

import javax.sound.midi.MidiUnavailableException;
import java.io.File;
import java.io.IOException;

public class DefaultMidiSystem implements MidiSystem {
    
    public DefaultSequencer getSequencer() {
        try {
            final javax.sound.midi.Sequencer sequencer = javax.sound.midi.MidiSystem.getSequencer();
            return new DefaultSequencer(sequencer);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Synthesizer getSynthesizer() {
        try {
            final javax.sound.midi.Synthesizer synthesizer = javax.sound.midi.MidiSystem.getSynthesizer();
            return new DefaultSynthesizer(synthesizer);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void write(final Sequence seq, final int type, final File file) {
        try {
            javax.sound.midi.MidiSystem.write(seq.unedge(), type, file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int[] getMidiFileTypes(final Sequence seq) {
        return javax.sound.midi.MidiSystem.getMidiFileTypes(seq.unedge());
    }
}