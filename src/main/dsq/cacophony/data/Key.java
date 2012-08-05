package dsq.cacophony.data;

public class Key {
    public final int value;

    // FIX 25/02/12 Move this out of here.
    public static final int OCTAVE_LENGTH = 12;

    private final int MAX_KEY = 120;
    public static final int BASE_OCTAVE = 5;


    public Key(final int value) {
        this.value = Math.min(MAX_KEY, Math.max(0, value));
    }
}
