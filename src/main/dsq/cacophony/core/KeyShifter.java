package dsq.cacophony.core;

import dsq.cacophony.data.Key;

import java.util.List;

public interface KeyShifter {
    List<Key> shift(List<Key> input, int amount);
}
