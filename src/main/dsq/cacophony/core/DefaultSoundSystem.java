package dsq.cacophony.core;

import dsq.cacophony.edge.javax.sound.midi.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSoundSystem implements SoundSystem {

    private final MidiSystem midis = new DefaultMidiSystem();

    private final Sequencer sequencer;

    private final Map<String, Instrument> instrumentMap = new HashMap<String, Instrument> ();

    public DefaultSoundSystem() {
        sequencer = midis.getSequencer();
    }

    // FIX 16/03/12 Possibly move out.
    private Sequence sequence(final List<MidiEvent> music) {
        final Sequence seq = new DefaultSequence(javax.sound.midi.Sequence.PPQ, 4);
        final Track track = seq.createTrack();
        for (final MidiEvent midiEvent : music) {
            track.add(midiEvent);
        }
        return seq;
    }

    public void resume() {
        if (sequencer.isOpen()) {
            sequencer.start();
        }
    }
    
    public void start(final List<MidiEvent> music) {
        final Sequence seq = sequence(music);
        sequencer.open();
        sequencer.setTickPosition(0);
        sequencer.setSequence(seq);
        sequencer.start();
    }

    public void stop() {
        if (sequencer.isOpen()) {
            sequencer.stop();
            sequencer.setTickPosition(0);
        }
    }

    public void pause() {
        if (sequencer.isOpen()) sequencer.stop();
    }

    public void reset() {
        if (sequencer.isOpen()) {
            sequencer.setTickPosition(0);
        }
    }

    public void shutdown() {
       if (sequencer.isOpen()) {
           sequencer.stop();
           sequencer.close();
       }
    }

    public void export(final List<MidiEvent> music, final File file) {
        final Sequence seq = sequence(music);
        final int[] types = midis.getMidiFileTypes(seq);
        if (types.length == 0) throw new RuntimeException("Cannot export MIDI file. No types are supported.");
        midis.write(seq, types[0], file);
    }

    public Synthesizer getSynthesizer() {
        return midis.getSynthesizer();
    }
}
