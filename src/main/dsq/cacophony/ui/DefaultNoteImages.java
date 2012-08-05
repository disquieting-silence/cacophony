package dsq.cacophony.ui;

import dsq.cacophony.data.*;

import java.awt.*;

public class DefaultNoteImages implements NoteImages {

    private final KeyImages images = new DefaultKeyImages();
    private final Positioner positioner = new DefaultPositioner();
    
    private static final int EGG_HEIGHT = 8;

    private static final Key REST_KEY = new Key(62);

    public NoteImage key(final Key key, final Duration duration, final int lineHeight, final boolean selected) {
        final BiImage pics = images.getKeyNote(duration);
        return note(key, selected ? pics.selected : pics.normal, lineHeight);
    }

    public NoteImage rest(final Duration duration, final int lineHeight, final boolean selected) {
        final BiImage pics = images.getRestNote(duration);
        return note(REST_KEY, selected ? pics.selected : pics.normal, lineHeight);
    }

    private NoteImage note(final Key key, final Image image, final int lineHeight) {
        final LineNumber lineNumber = positioner.position(key);
        final int noteMiddle = (int)(lineNumber.value * lineHeight);

        final int width = image.getWidth(null);
        final int height = image.getHeight(null);

        final int yOffset = (lineHeight - EGG_HEIGHT) / 2;
        final Point position = new Point(-width/2, yOffset + noteMiddle + EGG_HEIGHT/2 - height);
        return new NoteImage(image, position);
    }


}
