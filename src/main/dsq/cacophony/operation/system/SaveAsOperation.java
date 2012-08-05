package dsq.cacophony.operation.system;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.core.SoundSystem;
import dsq.cacophony.grate.*;
import dsq.cacophony.io.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.F;
import dsq.cacophony.shame.G;
import dsq.cacophony.shame.H;
import dsq.cacophony.shame.Option;
import dsq.cacophony.ui.StatusBar;
import dsq.cacophony.ui.ToridorManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveAsOperation implements Operation {
    
    private final FileBrowser browser;
    private final MidiInstruments instruments;
    private final Writer writer = new DefaultWriter();
    private final StatusBar status;

    public SaveAsOperation(final FileBrowser browser, final MidiInstruments instruments, final StatusBar status) {
        this.browser = browser;
        this.instruments = instruments;
        this.status = status;
    }

    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return systems.op(new H<SoundSystem, ToridorManager, Boolean>() {
            public Boolean h(final SoundSystem soundSystem, final ToridorManager toridorManager) {
                final Option<File> saveOption = browser.saveAs(null, Extension.CACOPHONY, "cacophony files (*.cacophony)");
                // FIX 17/03/12 Duplication with export.
                return saveOption.fold(new G<File, Boolean>() {
                    public Boolean g(final File output) {
                        try {
                            final Project project = toridorManager.project(instruments);
                            final PrintWriter pw = new PrintWriter(new FileWriter(output));
                            writer.write(project, pw);
                            pw.close();
                            status.setMessage("Project successfully saved to " + output.getPath());
                            return true;
                        } catch (IOException e) {
                            status.setMessage("Error when trying to save file: " + output.getPath() + ". Exception raised: " + e.getMessage());
                            e.printStackTrace();
                            return false;
                        }
                    }
                }, new F<Boolean>() {
                    public Boolean f() {
                        status.setMessage("Project save aborted.");
                        return false;
                    }
                });
            }
        });
    }
}
