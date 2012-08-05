package dsq.cacophony.control;

import dsq.cacophony.data.Duration;

import java.util.HashMap;
import java.util.Map;

public class DefaultControl implements Control {

    private int octave = 5;
    private Duration duration = new Duration(16L);
    
    // FIX 25/02/12 Move octaves into separate control and durations.
    private static final int MAX_OCTAVE = 8;
    private static final long MAX_DURATION = 64L;
    private static final long MIN_DURATION = 4L;

    private final Map<Long, String> translations = new HashMap<Long, String>();

    public DefaultControl() {
        translations.put(64L, "semibreve");
        translations.put(32L, "minim");
        translations.put(16L, "crotchet");
        translations.put(8L, "quaver");
        translations.put(4L, "semiquaver");
    }

    public void incOctave() {
        octave = Math.min(MAX_OCTAVE, octave + 1);
    }

    public void decOctave() {
        octave = Math.max(0, octave - 1);
    }

    public int getOctave() {
        return octave;
    }

    public void incDuration() {
        duration = new Duration(Math.min(duration.value * 2, MAX_DURATION));
    }

    public void decDuration() {
        duration = new Duration(Math.max(duration.value / 2, MIN_DURATION));            
    }

    public Duration getDuration() {
        return duration;
    }

    public String getDurationText() {
        final String r = translations.get(duration.value);
        if (r == null) throw new IllegalStateException("Could not find translations for duration length: "  + duration.value);
        return r;
    }
}
