package dsq.cacophony.ui;

import java.awt.*;

public class DefaultShapes implements Shapes {
    public void rect(final Graphics g, final int x, final int y, final int width, final int height, final int size) {
        g.fillRect(x, y, size, height);
        g.fillRect(x + width - size, y, size, height);
        g.fillRect(x, y, width, size);
        g.fillRect(x, y + height - size, width, size);
    }
}
