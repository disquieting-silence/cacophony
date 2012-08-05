package dsq.cacophony.io;

import dsq.cacophony.common.DefaultNoteCheck;
import dsq.cacophony.common.NoteCheck;
import dsq.cacophony.data.Duration;
import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import junit.framework.TestCase;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

public class DefaultReaderTest extends TestCase {
    private final Reader reader = new DefaultReader();
    private final NoteCheck checker = new DefaultNoteCheck();
    
    public void test() {
        final String input = "Acoustic Grand Piano\n_16";
        final Project expected = new Project(
            Arrays.asList(
                new InstrumentNotes("Acoustic Grand Piano", Arrays.asList(
                    new Note(new ArrayList<Key>(), new Duration(16), false)
                ))
            )
        );
        check(expected, input);

    }

    private void check(final Project expected, final String input) {
        final Project actual = reader.read(new StringReader(input));
        assertEquals("Number of channels: ", expected.contents.size(), actual.contents.size());
        for (int i = 0; i < expected.contents.size(); i++) {
            final InstrumentNotes exp = expected.contents.get(i);
            final InstrumentNotes act = actual.contents.get(i);
            assertEquals("Instrument for channel " + i + ": ", exp.instrument, act.instrument);
            assertEquals("Number of notes in channel " + i + ": ", exp.notes.size(), act.notes.size());
            for (int j = 0; j < exp.notes.size(); j++) {
                checker.checkEqual(exp.notes.get(j), act.notes.get(j));
            }
        }
    }
}
