package dsq.cacophony.control;

import dsq.cacophony.data.Duration;

public interface Control {
    void incOctave();
    void decOctave();
    // FIX 25/02/12 Make a strong type.
    int getOctave();
    
    void incDuration();
    void decDuration();
    Duration getDuration();
    String getDurationText();
}

