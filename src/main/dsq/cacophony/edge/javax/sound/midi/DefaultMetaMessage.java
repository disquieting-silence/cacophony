package dsq.cacophony.edge.javax.sound.midi;

import javax.sound.midi.InvalidMidiDataException;

public class DefaultMetaMessage implements MetaMessage {

    private final javax.sound.midi.MetaMessage edge;

    public DefaultMetaMessage() {
        this.edge = new javax.sound.midi.MetaMessage();
    }

    public javax.sound.midi.MetaMessage unedge() {
        return edge;
    }

    public void setMessage(final int type, final byte[] data, final int length) {
        try {
            edge.setMessage(type, data, length);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
