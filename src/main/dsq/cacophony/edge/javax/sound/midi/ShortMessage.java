package dsq.cacophony.edge.javax.sound.midi;

public interface ShortMessage {
    
    int NOTE_ON = javax.sound.midi.ShortMessage.NOTE_ON;
    int NOTE_OFF = javax.sound.midi.ShortMessage.NOTE_OFF;
    int CONTROL_CHANGE = javax.sound.midi.ShortMessage.CONTROL_CHANGE;
    int PROGRAM_CHANGE = javax.sound.midi.ShortMessage.PROGRAM_CHANGE;

    javax.sound.midi.ShortMessage unedge();
    void setMessage(int command, int key, int velocity, int channel);

}
