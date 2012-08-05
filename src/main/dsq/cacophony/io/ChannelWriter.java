package dsq.cacophony.io;

import dsq.cacophony.data.Note;

import java.util.List;

public interface ChannelWriter {
    String write(List<Note> notes);

}
