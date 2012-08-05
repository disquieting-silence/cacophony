package dsq.cacophony.io;

import java.util.List;

public class Project {
    // FIX 17/03/12 This could be an array to limit the channels, and allow ordering.
    public final List<InstrumentNotes> contents;

    public Project(final List<InstrumentNotes> contents) {
        this.contents = contents;
    }
}
