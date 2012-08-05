package dsq.cacophony.edge.javax.sound.midi;

public class DefaultSynthesizer implements Synthesizer {
    
    private final javax.sound.midi.Synthesizer edge;

    public DefaultSynthesizer(final javax.sound.midi.Synthesizer edge) {
        this.edge = edge;
    }

    public Instrument[] getAvailableInstruments() {
        final javax.sound.midi.Instrument[] edgeInstruments = edge.getAvailableInstruments();
        final Instrument[] instruments = new Instrument[edgeInstruments.length];
        // FIX 24/02/12 Is there s a better way?
        for (int i = 0; i < instruments.length; i++) {
            instruments[i] = new DefaultInstrument(edgeInstruments[i]);
        }
        return instruments;
    }
    
    public void loadInstrument(final Instrument instrument) {
        edge.loadInstrument(instrument.unedge());
    }
}
