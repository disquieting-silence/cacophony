package dsq.cacophony.io;

import dsq.cacophony.common.DefaultNoteCheck;
import dsq.cacophony.common.NoteCheck;
import dsq.cacophony.data.Duration;
import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class DefaultChannelReaderTest extends TestCase {

    private final ChannelReader subject = new DefaultChannelReader();
    private final NoteCheck checker = new DefaultNoteCheck();

    public void test() {
        checkErr("Each segment representing a note must be split by exactly one '_' character. Supplied input: []", "");
        check(asList(
                new Note(asList(new Key(60)), new Duration(16), false)
        ), "60_16");
        check(asList(
                new Note(asList(new Key(61), new Key(62)), new Duration(8), false),
                new Note(asList(new Key(40)), new Duration(16), false),
                new Note(new ArrayList<Key>(), new Duration(64), false),
                new Note(asList(new Key(56)), new Duration(32), false)
        ), "61,62_8|40_16|_64|56_32");
    }
    
    private void checkErr(final String expected, final String input) {
        try {
            subject.readChannel(input);
            fail("Expected exception not thrown: " + expected);    
        } catch (Exception exc) {
            assertEquals(expected, exc.getMessage());
        }
    }

    private void check(final List<Note> expected, final String input) {
        final List<Note> actual = subject.readChannel(input);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            final Note exp = expected.get(i);
            final Note act = actual.get(i);
            checker.checkEqual(exp, act);
        }
    }
}
