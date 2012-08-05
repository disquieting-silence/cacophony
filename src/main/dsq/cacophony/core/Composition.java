package dsq.cacophony.core;

import dsq.cacophony.data.Note;

import java.util.List;

public interface Composition {
    void add(Note note);
    void prev();
    void next();
    void up();
    void down();
    void clear();
    List<Note> get();
}
