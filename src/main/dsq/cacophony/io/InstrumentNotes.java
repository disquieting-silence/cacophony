package dsq.cacophony.io;

import dsq.cacophony.data.Note;

import java.util.List;

// FIX 17/03/12 This is sort of hard-coding that instruments cannot change in a channel ... it simplifies a lot of
// interface considerations but is a bit limiting. Hmmm. Perhaps the 14 or so possible channels can overcome the problem.
public class InstrumentNotes {
    public final String instrument;
    public final List<Note> notes;

    public InstrumentNotes(final String instrument, final List<Note> notes) {
        this.instrument = instrument;
        this.notes = notes;
    }
}
