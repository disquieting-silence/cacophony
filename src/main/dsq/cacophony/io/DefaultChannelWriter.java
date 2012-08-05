package dsq.cacophony.io;

import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import dsq.cacophony.shame.G;
import dsq.cacophony.util.DefaultStringUtils;
import dsq.cacophony.util.StringUtils;

import java.util.List;

public class DefaultChannelWriter implements ChannelWriter {
    
    private final StringUtils utils = new DefaultStringUtils();
    
    public String write(final List<Note> notes) {

        return utils.joinF(notes, new G<Note, String>() {
            public String g(final Note note) {
                final StringBuilder r = new StringBuilder();
                final List<Key> keys = note.keys;
                final String keysString = keysCsv(keys);
                r.append(keysString);
                r.append('_');
                r.append(note.duration.value);    
                return r.toString();
            }
        }, "|");
    }

    private String keysCsv(final List<Key> keys) {
        return utils.joinF(keys, new G<Key, String>() {
            public String g(final Key input) {
                return String.valueOf(input.value);
            }
        }, ",");
    }
}
