package dsq.cacophony.edge.javax.sound.midi;

import javax.sound.midi.InvalidMidiDataException;

public class DefaultShortMessage implements ShortMessage {
    
    private final javax.sound.midi.ShortMessage edge;

    public DefaultShortMessage() {
        edge = new javax.sound.midi.ShortMessage();
    }

    public javax.sound.midi.ShortMessage unedge() {
        return edge;
    }

    public void setMessage(final int command, final int key, final int velocity, final int channel) {
        try {
            edge.setMessage(command, key, velocity, channel);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
