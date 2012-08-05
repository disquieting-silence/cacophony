package dsq.cacophony.core;

import dsq.cacophony.data.Key;

import java.util.ArrayList;
import java.util.List;

public class DefaultKeyShifter implements KeyShifter {
    public List<Key> shift(final List<Key> input, final int amount) {
        final List<Key> r = new ArrayList<Key>();
        for (final Key rawKey : input) {
            r.add(new Key(rawKey.value + (Key.OCTAVE_LENGTH * amount)));
        }
        return r;
    }
}
