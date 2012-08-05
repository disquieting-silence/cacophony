package dsq.cacophony.common;

import dsq.cacophony.data.Note;

public interface NoteCheck {
    void checkEqual(Note expected, Note actual);
}
