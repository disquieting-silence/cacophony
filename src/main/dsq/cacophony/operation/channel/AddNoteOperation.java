package dsq.cacophony.operation.channel;

import dsq.cacophony.control.Control;
import dsq.cacophony.core.DefaultKeyShifter;
import dsq.cacophony.core.KeyShifter;
import dsq.cacophony.data.Key;
import dsq.cacophony.data.Note;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.H;
import dsq.cacophony.ui.Ocelot;
import dsq.cacophony.grate.*;

import java.util.List;

public class AddNoteOperation implements Operation {
    
    private final List<Key> key;
    private final int octaveOffset;
    
    private final KeyShifter shifter = new DefaultKeyShifter();

    public AddNoteOperation(final List<Key> key, final int octaveOffset) {
        this.key = key;
        this.octaveOffset = octaveOffset;
    }

    public boolean fold(final ChannelOp channels, final ControlOp controls, final SystemOp systems, final UiOp ui, final NoOp noop) {
        return channels.op(new H<Ocelot, Control, Boolean>() {
            public Boolean h(final Ocelot ocelot, final Control control) {
                final Note note = makeNote(key, control, octaveOffset);
                ocelot.add(note);
                return true;
            }
        });
    }

    private Note makeNote(final List<Key> rawKeys, final Control control, final int octaveOffset) {
        final int offset = control.getOctave() - Key.BASE_OCTAVE + octaveOffset;
        final List<Key> keys = shifter.shift(rawKeys, offset);
        return new Note(keys, control.getDuration(), false);
    }
}
