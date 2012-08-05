package dsq.cacophony.core;

import dsq.cacophony.data.Duration;
import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;

import java.util.ArrayList;
import java.util.List;

public class DefaultComposition implements Composition {
    
    private final List<Note> notes = new ArrayList<Note>();
    
    // FIX 25/02/12 How am I going to keep this is sync?
    private int currentIndex = -1;
    
    public void add(final Note note) {
        notes.add(note);
    }

    public void prev() {
        move(currentIndex - 1);
    }

    // FIX 25/02/12 Move navigation out.
    private void move(final int destination) {
        if (currentIndex == -1 && !notes.isEmpty()) {
            currentIndex = notes.size() - 1;
            replaceNote(currentIndex, true);
        } else if (!notes.isEmpty()) {
            // FIX 25/02/12 How to handle multi-select?
            if (currentIndex < notes.size()) replaceNote(currentIndex,  false);
            currentIndex = Math.max(0, Math.min(notes.size() - 1, destination));  
            replaceNote(currentIndex, true);
        }
    }
    
    private void replaceNote(final int index, final boolean selected) {
        if (index >= 0 && index < notes.size()) {
            final Note note = notes.get(index);
            replaceNote(index, note.keys, note.duration, selected);            
        }
    }
    
    private void replaceNote(final int index, final List<Key> keys, final Duration duration, final boolean selected) {
        if (index >= 0 && index < notes.size()) {
            notes.set(index, new Note(keys, duration, selected));
        }              
    }

    public void next() {
        move(currentIndex + 1);
    }

    public void up() {
        if (isValidSelection()) {
            // FIX 25/02/12 I don't want this to shift the octave ... but it's a constant addition until I write more stuff.
            final Note selected = notes.get(currentIndex);
            final List<Key> keys = new ArrayList<Key>();
            for (final Key key : selected.keys) {
                keys.add(new Key(key.value + Key.OCTAVE_LENGTH));
            }
            replaceNote(currentIndex, keys, selected.duration, true);
        }
    }

    private boolean isValidSelection() {
        return currentIndex >= 0 && currentIndex < notes.size();
    }

    public void down() {
    }

    public void clear() {
        notes.clear();
    }

    public List<Note> get() {
        // FIX 25/02/12 Should I bother with this?
        return new ArrayList<Note>(notes);    
    }
}
