package dsq.cacophony.edge.javax.sound.midi;

public interface Synthesizer {
    Instrument[] getAvailableInstruments();
    void loadInstrument(Instrument instrument);
}
