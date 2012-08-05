package dsq.cacophony.operation.system;

import dsq.cacophony.core.SoundSystem;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.grate.*;
import dsq.cacophony.io.Extension;
import dsq.cacophony.io.FileBrowser;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.F;
import dsq.cacophony.shame.G;
import dsq.cacophony.shame.H;
import dsq.cacophony.shame.Option;
import dsq.cacophony.ui.StatusBar;
import dsq.cacophony.ui.ToridorManager;

import java.io.File;
import java.util.List;

public class ExportOperation implements Operation {
    
    private final FileBrowser browser;
    private final StatusBar status;

    public ExportOperation(final FileBrowser browser, final StatusBar status) {
        this.browser = browser;
        this.status = status;
    }

    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return systems.op(new H<SoundSystem, ToridorManager, Boolean>() {
            public Boolean h(final SoundSystem soundSystem, final ToridorManager toridorManager) {
                final List<MidiEvent> notes = toridorManager.noteEvents();
                final Option<File> saveOption = browser.saveAs(null, Extension.MIDI, "MIDI files (*.midi)");
                return saveOption.fold(new G<File, Boolean>() {
                    public Boolean g(final File output) {
                        soundSystem.export(notes, output);
                        status.setMessage("Project successfully exported to " + output.getPath() + ".");
                        return true;
                    }
                }, new F<Boolean>() {
                    public Boolean f() {
                        status.setMessage("Project export aborted.");
                        return false;
                    }
                });
            }
        });
    }
}
