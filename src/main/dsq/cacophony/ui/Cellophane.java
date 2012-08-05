package dsq.cacophony.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public interface Cellophane {
    Image cellophane(BufferedImage source, Color transparent);
    Image cellophane(BufferedImage source, Map<Integer, Integer> replacements, int accuracy);
}
