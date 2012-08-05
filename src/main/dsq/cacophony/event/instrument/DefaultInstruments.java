package dsq.cacophony.event.instrument;

import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Tick;
import dsq.cacophony.edge.javax.sound.midi.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultInstruments implements Instruments {

    private static final int BANK_SELECT = 0;
    // FIX 24/02/12 Not sure what this is used for.
    private static final int BANK_SELECT_FINE = 32;

    public List<MidiEvent> change(final Instrument instrument, final Tick tick, final Channel channel) {
        final Patch patch = instrument.getPatch();
        final int bank = patch.getBank();
        final int program = patch.getProgram();

        final ShortMessage bankMessage = message(ShortMessage.CONTROL_CHANGE, BANK_SELECT, bank, channel);
        final ShortMessage programMessage = message(ShortMessage.PROGRAM_CHANGE, program, 0, channel);

        final List<MidiEvent> r = new ArrayList<MidiEvent>();
        r.add(new DefaultMidiEvent(bankMessage, tick.value));
        r.add(new DefaultMidiEvent(programMessage, tick.value));
        return r;
    }

    private ShortMessage message(final int command, final int controlSelect, final int bank, final Channel channel) {
        final ShortMessage r = new DefaultShortMessage();
        r.setMessage(command, channel.value, controlSelect, bank);
        return r;
    }
}