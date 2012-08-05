package dsq.cacophony.core;

import dsq.cacophony.edge.javax.sound.midi.Synthesizer;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;

import java.io.File;
import java.util.List;

public interface SoundSystem {

    void start(List<MidiEvent> notes);
    void stop();
    void pause();
    void reset();
    void shutdown();

    void export(List<MidiEvent> music, File file);

    Synthesizer getSynthesizer();

    void resume();


}
