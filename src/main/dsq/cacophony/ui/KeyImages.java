package dsq.cacophony.ui;

import dsq.cacophony.data.BiImage;
import dsq.cacophony.data.Duration;

public interface KeyImages {
    BiImage getKeyNote(Duration duration);
    BiImage getRestNote(Duration duration);
}
