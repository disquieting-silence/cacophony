package dsq.cacophony.event.tempo;

import dsq.cacophony.data.Tempo;
import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Tick;
import dsq.cacophony.edge.javax.sound.midi.DefaultMidiEvent;
import dsq.cacophony.edge.javax.sound.midi.DefaultMetaMessage;
import dsq.cacophony.edge.javax.sound.midi.MetaMessage;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;

public class DefaultTempos implements Tempos {

    private static final int TEMPO = 0x51;

    private byte[] data(final Tempo tempo) {
        // FIX 24/02/12 I copied this from my old code. Original source: http://jsresources.org/faq_midi.html#add_tempo_change
        final long millisPerQuarter = 60000000 / tempo.value;
        final byte[] r = new byte[3];
        r[0] = (byte)((millisPerQuarter >> 16) & 0xFF);
        r[1] = (byte)((millisPerQuarter >> 8) & 0xFF);
        r[2] = (byte)(millisPerQuarter & 0xFF);
        return r;
    }
    
    public MidiEvent change(final Tempo tempo, final Tick tick, final Channel channel) {
        final MetaMessage message = new DefaultMetaMessage();
        final byte[] info = data(tempo);
        message.setMessage(TEMPO, info, info.length);
        return new DefaultMidiEvent(message, tick.value);
    }
}