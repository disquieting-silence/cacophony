package dsq.cacophony.ui;

import dsq.cacophony.data.BiImage;
import dsq.cacophony.data.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DefaultKeyImages implements KeyImages {
    
    private final Map<Long, BiImage> keyImages = new HashMap<Long, BiImage>();
    private final Map<Long, BiImage> restImages = new HashMap<Long, BiImage>();
    private final Cellophane cellophane = new DefaultCellophane();
    
    private final Map<Integer, Integer> replacements = new HashMap<Integer, Integer>();

    public DefaultKeyImages() {
        replacements.put(Color.BLACK.getRGB(), 0xEECCCC00);

        add(keyImages, 4L, "Sixteenth_note.gif");
        add(keyImages, 8L, "Eighth_note.gif");
        add(keyImages, 16L, "Quarter_note.gif");
        add(keyImages, 32L, "Half_note.gif");
        add(keyImages, 64L, "Whole_note.gif");

        add(restImages, 4L, "Sixteenth_rest.png");
        add(restImages, 8L, "Eighth_rest.png");
        add(restImages, 16L, "Quarter_rest.png");
        add(restImages, 32L, "Half_rest.png");
        add(restImages, 64L, "Whole_rest.png");

    }

    private void add(final Map<Long, BiImage> map, final long duration, final String filename) {
        final BufferedImage base = loadResource("images/" + filename);
        final Image normal = cellophane.cellophane(base, Color.WHITE);
        final Image selected = cellophane.cellophane((BufferedImage)normal, replacements, 150);
        map.put(duration, new BiImage(normal, selected));
    }

    public BiImage getKeyNote(final Duration duration) {
        final BiImage images = keyImages.get(duration.value);
        return strict(duration, images);
    }

    private BiImage strict(final Duration duration, final BiImage images) {
        if (images == null) throw new IllegalStateException("No image available for duration: " + duration.value);
        return images;
    }

    public BiImage getRestNote(final Duration duration) {
        final BiImage images = restImages.get(duration.value);
        return strict(duration, images);
    }

    // FIX 25/02/12 Probably move out.
    private BufferedImage loadResource(final String filename) {
        final InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filename);
        try {
            return ImageIO.read(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
