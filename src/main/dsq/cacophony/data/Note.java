package dsq.cacophony.data;

import java.util.List;

public class Note {
    public final List<Key> keys;
    public final Duration duration;
    public final boolean selected;

    public Note(final List<Key> keys, final Duration duration, final boolean selected) {
        this.keys = keys;
        this.duration = duration;
        this.selected = selected;
    }
}
