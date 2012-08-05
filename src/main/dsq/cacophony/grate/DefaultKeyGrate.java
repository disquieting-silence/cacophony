package dsq.cacophony.grate;

import dsq.cacophony.data.Key;
import dsq.cacophony.operation.common.NoOperation;
import dsq.cacophony.operation.channel.AddNoteOperation;
import dsq.cacophony.operation.channel.ClearOperation;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.operation.control.DecDurationOperation;
import dsq.cacophony.operation.control.DecOctaveOperation;
import dsq.cacophony.operation.control.IncOctaveOperation;
import dsq.cacophony.operation.ui.SwitchToCommandOperation;
import dsq.cacophony.operation.control.IncDurationOperation;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class DefaultKeyGrate implements KeyGrate {

    private final static List<Key> A = Arrays.asList(new Key(56));
    private final static List<Key> B = Arrays.asList(new Key(58));
    private final static List<Key> C = Arrays.asList(new Key(60));
    private final static List<Key> D = Arrays.asList(new Key(61));
    private final static List<Key> E = Arrays.asList(new Key(63));
    private final static List<Key> F = Arrays.asList(new Key(65));
    private final static List<Key> G = Arrays.asList(new Key(67));
    
    public Operation key(final KeyEvent event) {
        switch (event.getKeyCode()) {
            
            case KeyEvent.VK_ESCAPE: return new SwitchToCommandOperation();

            case KeyEvent.VK_UP : return new IncOctaveOperation();
            case KeyEvent.VK_DOWN: return  new DecOctaveOperation();
            case KeyEvent.VK_SUBTRACT: return new DecDurationOperation();
            case KeyEvent.VK_ADD: return new IncDurationOperation();

            
            case KeyEvent.VK_F1 :  return new ClearOperation();
            // FIX 3/03/12 Probably use a Map.
            // Maybe use Shift for sharp / flat.
            case KeyEvent.VK_Q: return new AddNoteOperation(F, 0);
            case KeyEvent.VK_W: return new AddNoteOperation(G, 0);
            case KeyEvent.VK_E: return new AddNoteOperation(A, 1);
            case KeyEvent.VK_R: return new AddNoteOperation(B, 1);
            case KeyEvent.VK_T: return new AddNoteOperation(C, 1);
            case KeyEvent.VK_Y: return new AddNoteOperation(D, 1);
            case KeyEvent.VK_U: return new AddNoteOperation(E, 1);
            case KeyEvent.VK_I: return new AddNoteOperation(F, 1);
            case KeyEvent.VK_O: return new AddNoteOperation(G, 1);
            case KeyEvent.VK_P: return new AddNoteOperation(A, 2);
            
            case KeyEvent.VK_A: return new AddNoteOperation(F, -1);
            case KeyEvent.VK_S: return new AddNoteOperation(G, -1);
            case KeyEvent.VK_D: return new AddNoteOperation(A, 0);
            case KeyEvent.VK_F: return new AddNoteOperation(B, 0);
            case KeyEvent.VK_G: return new AddNoteOperation(C, 0);
            case KeyEvent.VK_H: return new AddNoteOperation(D, 0);
            case KeyEvent.VK_J: return new AddNoteOperation(E, 0);
            case KeyEvent.VK_K: return new AddNoteOperation(F, 0);
            case KeyEvent.VK_L: return new AddNoteOperation(G, 0);
            case KeyEvent.VK_SEMICOLON: return new AddNoteOperation(A, 1);
            case KeyEvent.VK_QUOTE: return new AddNoteOperation(B, 1);

            case KeyEvent.VK_Z: return new AddNoteOperation(F, -2);
            case KeyEvent.VK_X: return new AddNoteOperation(G, -2);
            case KeyEvent.VK_C: return new AddNoteOperation(A, -1);
            case KeyEvent.VK_V: return new AddNoteOperation(B, -1);
            case KeyEvent.VK_B: return new AddNoteOperation(C, -1);
            case KeyEvent.VK_N: return new AddNoteOperation(D, -1);
            case KeyEvent.VK_M: return new AddNoteOperation(E, -1);
        }
        return new NoOperation();
    }
}
