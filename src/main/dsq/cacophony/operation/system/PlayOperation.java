package dsq.cacophony.operation.system;

import dsq.cacophony.core.SoundSystem;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.grate.*;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.H;
import dsq.cacophony.ui.StatusBar;
import dsq.cacophony.ui.ToridorManager;

import java.util.List;

public class PlayOperation implements Operation {
    private final StatusBar status;

    public PlayOperation(final StatusBar status) {
        this.status = status;
    }

    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return systems.op(new H<SoundSystem, ToridorManager, Boolean>() {
            public Boolean h(final SoundSystem input, final ToridorManager manager) {
                final List<MidiEvent> notes = manager.noteEvents();
                input.start(notes);
                status.setMessage("Started playing ...");
                return true;
            }
        });
    }
}
