package dsq.cacophony.io;

import dsq.cacophony.data.Duration;
import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import dsq.cacophony.io.ChannelWriter;
import dsq.cacophony.io.DefaultChannelWriter;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class DefaultChannelWriterTest extends TestCase {
    
    private final ChannelWriter writer = new DefaultChannelWriter();
    
    public void test() {
        check("60_16", asList(
            new Note(asList(new Key(60)), new Duration(16), false)
        ));
        check("61,62_8|40_16|_64|56_32", asList(
            new Note(asList(new Key(61), new Key(62)), new Duration(8), false),
            new Note(asList(new Key(40)), new Duration(16), false),
            new Note(new ArrayList<Key>(), new Duration(64), false),
            new Note(asList(new Key(56)), new Duration(32), false)
        ));    
    }

    private void check(final String expected, final List<Note> input) {
        final String actual = writer.write(input);
        assertEquals(expected, actual);
    }
}
