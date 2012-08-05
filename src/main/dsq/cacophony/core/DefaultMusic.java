package dsq.cacophony.core;

import dsq.cacophony.data.*;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.event.instrument.DefaultInstruments;
import dsq.cacophony.event.instrument.Instruments;
import dsq.cacophony.event.note.DefaultMidiNotes;
import dsq.cacophony.event.note.MidiNotes;
import dsq.cacophony.event.tempo.DefaultTempos;
import dsq.cacophony.event.tempo.Tempos;
import dsq.cacophony.shame.F;
import dsq.cacophony.shame.G;
import dsq.cacophony.shame.Option;

import java.util.ArrayList;
import java.util.List;

public class DefaultMusic implements Music {
    
    private final Tempos tempos = new DefaultTempos();
    private final Instruments instruments = new DefaultInstruments();
    private final MidiNotes midiNotes = new DefaultMidiNotes();
    
    private static final Tempo BASE_TEMPO = new Tempo(500);
    
    // FIX 24/02/12 Maybe allow change of instruments inside a track later.
    public List<MidiEvent> music(final Option<Instrument> instrumentOption, final List<Note> notes, final Channel channel) {
        final List<MidiEvent> events = baseEvents(instrumentOption, channel);

        long current = 0;
        for (final Note note : notes) {
            final List<Key> keys = note.keys;
            for (final Key key : keys) {
                final MidiEvent start = midiNotes.start(key, new Tick(current), channel);
                final MidiEvent stop = midiNotes.stop(key, new Tick(current + note.duration.value), channel);
                events.add(start);
                events.add(stop);
            }
            current += note.duration.value;
        }
        return events;
    }

    private List<MidiEvent> baseEvents(final Option<Instrument> instrumentOption, final Channel channel) {
        final List<MidiEvent> r = new ArrayList<MidiEvent>();
        final MidiEvent tempoEvent = tempos.change(BASE_TEMPO, new Tick(0), channel);
        r.add(tempoEvent);
        final List<MidiEvent> instEvent = instrumentOption.fold(new G<Instrument, List<MidiEvent>>() {
                    public List<MidiEvent> g(final Instrument input) {
                        return instruments.change(input, new Tick(0), channel);
                    }
                }, new F<List<MidiEvent>>() {
            public List<MidiEvent> f() {
                return new ArrayList<MidiEvent>();
            }
        }
        );
        r.addAll(instEvent);
        return r;
    }
}
    