package dsq.cacophony.ui;

import dsq.cacophony.core.DefaultMusic;
import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.core.Music;
import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.edge.javax.sound.midi.MidiEvent;
import dsq.cacophony.io.Project;
import dsq.cacophony.shame.Option;

import java.util.ArrayList;
import java.util.List;

public class DefaultToridorManager implements ToridorManager {

    private final List<Toridor> views;
    private final Music music = new DefaultMusic();
    private final Projects projects = new DefaultProjects();

    public DefaultToridorManager(final List<Toridor> views) {
        // FIX 3/03/12 Better error message.
        if (views.size() == 0) throw new IllegalStateException("Must have at least one Toridor defined.");
        this.views = views;
    }

    // FIX 3/03/12 Move out.
    public List<MidiEvent> noteEvents() {
        final List<MidiEvent> r = new ArrayList<MidiEvent>();
        for (int i = 0; i < views.size(); i++) {
            final Toridor view = views.get(i);
            final List<Note> notes = view.getNotes();
            final Option<Instrument> instrumentOption = view.getInstrument();
            final List<MidiEvent> events = music.music(instrumentOption, notes, new Channel(i));
            r.addAll(events);
        }
        return r;
    }

    public Project project(final MidiInstruments instruments) {
        return projects.project(instruments, views);
    }
}
