package dsq.cacophony.util;

import dsq.cacophony.shame.G;

import java.util.List;

public class DefaultStringUtils implements StringUtils {

    public <A> String joinF(final List<A> xs, final G<A, String> g, final String delimiter) {
        if (xs.isEmpty()) return "";

        final StringBuilder r = new StringBuilder();
        final A first = xs.get(0);
        r.append(g.g(first));

        for (int i = 1; i < xs.size(); i++) {
            final A x = xs.get(i);
            r.append(delimiter).append(g.g(x));
        }

        return r.toString();
    }
}
