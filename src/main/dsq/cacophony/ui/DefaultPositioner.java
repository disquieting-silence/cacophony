package dsq.cacophony.ui;

import dsq.cacophony.data.Key;
import dsq.cacophony.data.LineNumber;

public class DefaultPositioner implements Positioner {
    
    /*
      
    -4(8), -2(10), 0(0), 1(1), 3(3), 5(5), 7(7)
     */
    
    private static final int OCTAVE = 12;
    private static final int MIDDLE_C_OCTAVE = 5;


    public LineNumber position(final Key key) {
        final int offset = key.value % OCTAVE;
        final int octave = key.value / OCTAVE;
        
        final double lineNumber = 2.5 + 3.5 * (MIDDLE_C_OCTAVE - octave + 1);

        final double r = lineNum(offset, lineNumber);
        return new LineNumber(r);
    }

    private double lineNum(final int offset, final double lineNumber) {
        if (offset == 8) return lineNumber - 2.5;
        if (offset == 10) return lineNumber - 3.0;
        if (offset == 0) return lineNumber;
        if (offset == 1) return lineNumber - 0.5;
        if (offset == 3) return lineNumber - 1.0;
        if (offset == 5) return lineNumber - 1.5;
        if (offset == 7) return lineNumber - 2.0;
        else return lineNumber;
    }
}
