package dsq.cacophony.edge.javax.sound.midi;

public interface MetaMessage {
    javax.sound.midi.MetaMessage unedge();
    void setMessage(int type, byte[] data, int length);
}
