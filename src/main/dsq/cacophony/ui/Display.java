package dsq.cacophony.ui;

import dsq.cacophony.data.Note;

import java.awt.Graphics;
import java.util.List;

public interface Display {
    void display(Graphics g, List<Note> notes, int x, int y, int width, int height);
}
