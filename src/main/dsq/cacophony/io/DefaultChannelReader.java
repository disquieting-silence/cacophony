package dsq.cacophony.io;

import dsq.cacophony.data.Duration;
import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;

import java.util.ArrayList;
import java.util.List;

public class DefaultChannelReader implements ChannelReader {

    /* Format
            Project file: powerful.cacophony
            Acoustic Piano
            60,61_16|62_16|63_16
            Trumpet




     */


    public List<Note> readChannel(final String input) {
        final List<Note> r = new ArrayList<Note>();
        final String[] lines = input.split("\\|");
        for (final String line : lines) {
            final String[] sections = line.split("_");
            if (sections.length != 2) throw new IllegalStateException("Each segment representing a note must be split by exactly one '_' character. Supplied input: [" + line + "]");
            // FIX 24/02/12 Make cleaner. Rest API perhaps?
            final Note note = parseNotes(sections);
            r.add(note);
        }
        return r;
    }

    private Note parseNotes(final String[] sections) {
        final String[] rawKeys = sections[0].isEmpty() ? new String[0] : sections[0].split(",");
        final Duration duration = new Duration(Integer.parseInt(sections[1]));
        
        final List<Key> keys = new ArrayList<Key>();
        for (final String rawKey : rawKeys) {
            keys.add(new Key(Integer.parseInt(rawKey)));
        }
        
        return new Note(keys, duration, false);
    }
}
