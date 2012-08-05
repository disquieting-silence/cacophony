package dsq.cacophony.ui;

import dsq.cacophony.data.Duration;
import dsq.cacophony.data.NoteImage;
import dsq.cacophony.data.Key;

public interface NoteImages {
    NoteImage key(Key key, Duration duration, int lineHeight, boolean selected);
    NoteImage rest(Duration duration, int lineHeight, boolean selected);
}
