package dsq.cacophony.util;

import dsq.cacophony.shame.G;

import java.util.List;

public interface StringUtils {
    <A> String joinF(List<A> xs, G<A, String> g, String delimiter);
}
