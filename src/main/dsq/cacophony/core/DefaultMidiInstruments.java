package dsq.cacophony.core;

import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.edge.javax.sound.midi.Synthesizer;
import dsq.cacophony.shame.NoneOption;
import dsq.cacophony.shame.Option;
import dsq.cacophony.shame.SomeOption;
import dsq.cacophony.util.Autocompleter;
import dsq.cacophony.util.DefaultAutocompleter;

import java.util.*;

public class DefaultMidiInstruments implements MidiInstruments {
    
    private final Map<String, Instrument> instruments = new HashMap<String, Instrument>();
    private final Autocompleter completer = new DefaultAutocompleter();
    private final Option<Instrument> defaultInstrument;

    public DefaultMidiInstruments(final Synthesizer synthesizer) {
        final Instrument[] available = synthesizer.getAvailableInstruments();
        defaultInstrument = available.length > 0 ? new SomeOption<Instrument>(available[0]) : new NoneOption<Instrument>();
        for (final Instrument instrument : available) {
            final String name = instrument.getName();
            System.out.println("name = " + name);
            instruments.put(name, instrument);
            synthesizer.loadInstrument(instrument);
        }
    }

    public Option<Instrument> get(final String name) {
        final Instrument instrument = instruments.get(name);
        System.out.println("instrument = " + instrument.getName());
        // FIX 4/03/12 Need to generalise this.
        return instrument != null ? new SomeOption<Instrument>(instrument) : new NoneOption<Instrument>();
    }

    public Set<String> search(final String pattern) {
        final Set<String> names = instruments.keySet();
        // FIX 4/03/12 Hmm. Maybe just generalise the return type.
        return new HashSet<String>(completer.matches(names, pattern));
    }

    public Option<Instrument> getDefault() {
        return defaultInstrument;
    }
}
