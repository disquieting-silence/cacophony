package dsq.cacophony.ui;

import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.io.InstrumentNotes;
import dsq.cacophony.io.Project;
import dsq.cacophony.shame.Option;
import dsq.cacophony.core.Composition;
import dsq.cacophony.core.DefaultComposition;

import java.util.ArrayList;
import java.util.List;

public class DefaultMeerkat implements Meerkat {
    public List<Toridor> views(final Project project, final MidiInstruments instruments) {
        final List<InstrumentNotes> contents = project.contents;
        // FIX 17/03/12 Consider view separation (channels vs visible channels) later ... just load as many channels as it has.
        final List<Toridor> r = new ArrayList<Toridor>();
        for (int i = 0; i < contents.size(); i++) {
            final InstrumentNotes content = contents.get(i);
            final MusicPanel v = new DefaultMusicPanel();
            final Option<Instrument> instrumentOption = instruments.get(content.instrument);
            final Composition composition = comp(content);
            final Toridor view = new DefaultToridor(new Channel(i), instrumentOption, composition, v);
            r.add(view);
        }
        return r;
    }

    private Composition comp(final InstrumentNotes content) {
        final Composition composition = new DefaultComposition();
        for (final Note note : content.notes) {
            composition.add(note);
        }
        return composition;
    }
}
