package dsq.cacophony.data;

import java.awt.*;

public class NoteImage {
    // FIX 25/02/12 Edge these?
    public final Image image;
    public final Point position;

    public NoteImage(final Image image, final Point position) {
        this.image = image;
        this.position = position;
    }
}
