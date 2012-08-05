package dsq.cacophony.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class DefaultCellophane implements Cellophane {

    private static final int TRANSPARENT = 0x00EEEEEE;

    public Image cellophane(final BufferedImage source, final Color transparent) {
        final Map<Integer, Integer> replacements = new HashMap<Integer, Integer>();
        replacements.put(transparent.getRGB(), TRANSPARENT);
        // FIX 25/02/12 Magic number (120 = Degree for similar colour)
        return cellophane(source, replacements, 120);
    }

    public Image cellophane(final BufferedImage source, final Map<Integer, Integer> replacements, final int accuracy) {
        final int width = source.getWidth();
        final int height = source.getHeight();
        final BufferedImage copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int rgb = source.getRGB(x, y);
                final int colour = findColour(replacements, rgb, accuracy);
                copy.setRGB(x, y, colour);
            }
        }
        return copy;
    }

    private int findColour(final Map<Integer, Integer> replacements, final Integer rgb, final int accuracy) {
        for (final Integer color : replacements.keySet()) {
            if (colourMatches(color, rgb, accuracy)) {
                return replacements.get(color);
            }
        }
        return rgb;
    }

    // FIX 25/02/12 All a bit hacky and too not-near
    private boolean colourMatches(final Integer rgb1, final Integer rgb2, final int degree) {
        final Color c1 = new Color(rgb1);
        final Color c2 = new Color(rgb2);
        return isNear(c1.getRed(), c2.getRed(), degree) && isNear(c1.getBlue(), c2.getBlue(), degree) &&
            isNear(c1.getGreen(), c2.getGreen(), degree);
    }

    private boolean isNear(final int value1, final int value2, final int degree) {
        return Math.abs(value1 - value2) < degree;
    }
}
