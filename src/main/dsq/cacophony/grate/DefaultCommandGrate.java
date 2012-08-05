package dsq.cacophony.grate;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.io.DefaultFileBrowser;
import dsq.cacophony.io.FileBrowser;
import dsq.cacophony.operation.common.NoOperation;
import dsq.cacophony.operation.channel.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.operation.ui.SwitchToKeysOperation;
import dsq.cacophony.ui.StatusBar;
import dsq.cacophony.operation.system.ExportOperation;
import dsq.cacophony.operation.system.PlayOperation;
import dsq.cacophony.operation.system.QuitOperation;
import dsq.cacophony.operation.system.SaveAsOperation;

import java.awt.event.KeyEvent;
import java.io.File;

public class DefaultCommandGrate implements CommandGrate {
    
    private final FileBrowser browser = new DefaultFileBrowser(new File("."));
    // FIX 17/03/12 Not sure if I want this here, or as an argument to the right fold for operation.
    private final StatusBar status;
    private final MidiInstruments instruments;

    public DefaultCommandGrate(final MidiInstruments instruments, final StatusBar status) {
        this.instruments = instruments;
        this.status = status;
    }

    public Operation key(final KeyEvent event) {
        switch (event.getKeyCode()) {
            
            case KeyEvent.VK_A: return new SwitchToKeysOperation();
            case KeyEvent.VK_ENTER: return new PlayOperation(status);
            case KeyEvent.VK_TAB: return new CycleOperation();

            case KeyEvent.VK_S:
                return justControl(event) ? new SaveAsOperation(browser, instruments, status) : new NoOperation();

            case KeyEvent.VK_X:
                return justControl(event) ? new ExportOperation(browser, status) : new NoOperation();
                
            case KeyEvent.VK_Q:
                return justControl(event) ? new QuitOperation() : new NoOperation();
            
            case KeyEvent.VK_0: return handleNum(event, 0);
            case KeyEvent.VK_1: return handleNum(event, 1);
            case KeyEvent.VK_2: return handleNum(event, 2);
            case KeyEvent.VK_3: return handleNum(event, 3);
            case KeyEvent.VK_4: return handleNum(event, 4);
            case KeyEvent.VK_5: return handleNum(event, 5);

            case KeyEvent.VK_7: return handleNum(event, 7);
            case KeyEvent.VK_8: return handleNum(event, 8);
            case KeyEvent.VK_9: return handleNum(event, 9);

            case KeyEvent.VK_LEFT: return new LeftOperation();
            case KeyEvent.VK_RIGHT: return new RightOperation();

            case KeyEvent.VK_UP: return new UpOperation();
        }
        return new NoOperation();
    }

    private Operation handleNum(final KeyEvent event, final int number) {
        return justShiftDown(event) ? new ChangeChannelOperation(number) : new NoOperation();
    }

    private boolean justControl(final KeyEvent event) {
        return event.isControlDown() && !event.isAltDown() && !event.isMetaDown() && !event.isShiftDown();
    }

    private boolean justShiftDown(final KeyEvent event) {
        return !event.isControlDown() && !event.isAltDown() && !event.isMetaDown() && event.isShiftDown();
    }
}
