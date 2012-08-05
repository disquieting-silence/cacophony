package dsq.cacophony.ui;

import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.shame.Option;

import java.util.List;

public interface Toridor extends JView {
    void left();
    void right();
    void up();

    void add(Note note);
    void clear();
    List<Note> getNotes();
    
    Channel channel();
    
    // FIX 3/03/12 Probably shouldn't be here.
    Option<Instrument> getInstrument();
}
