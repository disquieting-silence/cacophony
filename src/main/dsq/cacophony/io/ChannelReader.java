package dsq.cacophony.io;

import dsq.cacophony.data.Note;

import java.util.List;

public interface ChannelReader {
    List<Note> readChannel(String input);
}
