package dsq.cacophony.ui;

import dsq.cacophony.data.Key;
import dsq.cacophony.data.LineNumber;
import dsq.cacophony.ui.DefaultPositioner;
import dsq.cacophony.ui.Positioner;
import junit.framework.TestCase;

public class DefaultPositionerTest extends TestCase {
    
    private final Positioner subject = new DefaultPositioner();
    
    private void check(final double expected, final int input) {
        final LineNumber actual = subject.position(new Key(input));
        assertEquals(expected, actual.value);
    }
    
    public void test() {
        check(6.0, 60);
        check(3.0, 70);
        check(2.5, 72);
    }
}
