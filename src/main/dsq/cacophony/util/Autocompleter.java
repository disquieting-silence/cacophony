package dsq.cacophony.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Autocompleter {
    Collection<String> matches(Collection<String> values, String regularExpression);
}
