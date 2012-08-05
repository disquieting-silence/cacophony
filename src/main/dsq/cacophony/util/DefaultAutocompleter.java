package dsq.cacophony.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// FIX 4/03/12 Not particularly efficient.
public class DefaultAutocompleter implements Autocompleter {
    public Collection<String> matches(final Collection<String> values, final String regularExpression) {
        final Pattern regex = Pattern.compile(regularExpression);
        final Set<String> r = new HashSet<String>();
        for (final String value : values) {
            final Matcher matcher = regex.matcher(value);
            if (matcher.matches()) r.add(value);
        }
        return r;
    }
}
