package dsq.cacophony.data;

public class Channel {
    public final int value;

    // FIX 17/03/12 Hacky. Find a better way.
    public static final int NO_CHANNEL = -1;

    public Channel(final int value) {
        this.value = value;
    }
}
