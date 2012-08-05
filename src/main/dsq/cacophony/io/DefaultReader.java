package dsq.cacophony.io;

import dsq.cacophony.data.Note;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultReader implements Reader {
    
    private final ChannelReader reader = new DefaultChannelReader();
    
    public Project read(final java.io.Reader input) {
        try {
            // FIX 17/03/12 Perhaps I should just edge it.
            return unsafeRead(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Project unsafeRead(final java.io.Reader input) throws IOException {
        final List<InstrumentNotes> r = new ArrayList<InstrumentNotes>();
        final BufferedReader br = new BufferedReader(input);
        String line = br.readLine();
        while (line != null) {
            final String notesLine = br.readLine();                                      
            final List<Note> notes = reader.readChannel(notesLine);
            final InstrumentNotes channel = new InstrumentNotes(line, notes);
            r.add(channel);
            line = br.readLine();
        }
        return new Project(r);
    }
}
