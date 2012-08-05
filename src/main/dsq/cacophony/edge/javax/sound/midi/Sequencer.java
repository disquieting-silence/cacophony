package dsq.cacophony.edge.javax.sound.midi;

public interface Sequencer {
    boolean isOpen();
    void stop();
    void setTickPosition(long position);
    void close();
    void setSequence(Sequence sequence);
    void start();
    void open();
}
