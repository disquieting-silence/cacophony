package dsq.cacophony.ui;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.io.InstrumentNotes;
import dsq.cacophony.io.Project;
import dsq.cacophony.shame.F;
import dsq.cacophony.shame.G;
import dsq.cacophony.shame.Option;

import java.util.ArrayList;
import java.util.List;

public class DefaultProjects implements Projects {
    
    public Project project(final MidiInstruments instruments, final List<Toridor> views) {
        final List<InstrumentNotes> r = new ArrayList<InstrumentNotes>();
        for (final Toridor view : views) {
            final Option<Instrument> instrumentOption = view.getInstrument();
            final String name = instrumentName(instruments, instrumentOption);
            final List<Note> notes = view.getNotes();
            final InstrumentNotes iNotes = new InstrumentNotes(name, notes);
            r.add(iNotes);
        }        
        return new Project(r);
    }

    private String instrumentName(final MidiInstruments instruments, final Option<Instrument> instrumentOption) {
        final Option<Instrument> iOption = instrumentOption.or(instruments.getDefault());
        return iOption.fold(new G<Instrument, String>() {
                    public String g(final Instrument input) {
                        return input.getName();
                    }
                }, new F<String>() {
            public String f() {
                return "NO INSTRUMENT";
            }
        });
    }
}
