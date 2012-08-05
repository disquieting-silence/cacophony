package dsq.cacophony.ui;

import dsq.cacophony.data.Note;

import java.util.List;

public interface MusicPanel extends JView {
    void refresh(List<Note> notes);
    void setSelected(boolean selected);
}
