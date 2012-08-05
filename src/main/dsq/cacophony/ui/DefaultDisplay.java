package dsq.cacophony.ui;

import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import dsq.cacophony.data.NoteImage;

import java.awt.*;
import java.util.List;

public class DefaultDisplay implements Display {
    
    private static final int LINE_HEIGHT = 10;
    private static final int NUM_LINES = 5;
    
    private static final Color BACKGROUND_COLOUR = Color.WHITE;
    private static final Color LINE_COLOUR = Color.BLACK;

    private final NoteImages images = new DefaultNoteImages();
    private static final double SPACE_PER_QUART = 40;

    public void display(final Graphics g, final List<Note> notes, final int x, final int y, final int width, final int height) {
        g.setColor(BACKGROUND_COLOUR);
        g.fillRect(0, 0, width, height);
        drawNotes(g, notes, x, y);
        drawBacklines(g, y, width);
    }

    private void drawBacklines(final Graphics g, final int y, final int width) {
        g.setColor(LINE_COLOUR);
        final int startY = y + 2;
        for (int i = 0; i < NUM_LINES; i++) {
            final int currentY = startY + i * LINE_HEIGHT;
            g.drawLine(0, currentY, width, currentY);    
        }
    }

    private void drawNotes(final Graphics g, final List<Note> notes, final int x, final int y) {
        int current = x;
        for (final Note note : notes) {
            final int nextTick = current + (int)(note.duration.value / 16.0 * SPACE_PER_QUART);
            final int noteX = (current + nextTick) / 2;
            if (note.keys.isEmpty()) drawRest(g, y, noteX, note);
            else drawKey(g, y, noteX, note);
            current = nextTick;
        }
    }

    private void drawRest(final Graphics g, final int y, final int current, final Note note) {
        final NoteImage rest = images.rest(note.duration, LINE_HEIGHT, note.selected);
        drawNote(g, rest, current, y);
    }

    private void drawKey(final Graphics g, final int y, final int current, final Note note) {
        for (final Key key : note.keys) {
            final NoteImage noteImage = images.key(key, note.duration, LINE_HEIGHT, note.selected);
            drawNote(g, noteImage, current, y);
        }
    }

    private void drawNote(final Graphics g, final NoteImage noteImage, final int current, final int y) {
        g.drawImage(noteImage.image, current + noteImage.position.x, y + noteImage.position.y, null);
    }

}
