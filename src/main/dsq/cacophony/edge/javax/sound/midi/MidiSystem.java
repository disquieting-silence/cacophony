package dsq.cacophony.edge.javax.sound.midi;

import java.io.File;

public interface MidiSystem {
    Sequencer getSequencer();
    Synthesizer getSynthesizer();
    void write(Sequence seq, int type, File file);
    int [] getMidiFileTypes(Sequence seq);

}
