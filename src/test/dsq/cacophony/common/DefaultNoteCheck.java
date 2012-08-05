package dsq.cacophony.common;

import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import junit.framework.Assert;

public class DefaultNoteCheck implements NoteCheck {
    public void checkEqual(final Note expected, final Note actual) {
        Assert.assertEquals("Number of keys in a note: ", expected.keys.size(), actual.keys.size());
        for (int j = 0; j < expected.keys.size(); j++)  {
            final Key e = expected.keys.get(j);
            final Key a = actual.keys.get(j);
            Assert.assertEquals("Value of a key in a note: ", e.value, a.value);
        }
        Assert.assertEquals("Duration of key(s) in note: ", expected.duration.value, actual.duration.value);
        Assert.assertEquals("Selected status of note: ", expected.selected, actual.selected);
    }
}
