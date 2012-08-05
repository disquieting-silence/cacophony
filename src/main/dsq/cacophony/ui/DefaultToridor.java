package dsq.cacophony.ui;

import dsq.cacophony.core.Composition;
import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.shame.Option;

import java.awt.*;
import java.util.List;

public class DefaultToridor implements Toridor {

    private final Channel channel;
    private final Option<Instrument> instrumentOption;
    private final Composition composition;
    private final MusicPanel display;

    public DefaultToridor(final Channel channel, final Option<Instrument> instrumentOption, final Composition composition, final MusicPanel display) {
        this.channel = channel;
        this.instrumentOption = instrumentOption;
        this.composition = composition;
        this.display = display;
        
        refresh();
    }

    public void left() {
        composition.prev(); 
        refresh();
    }

    private void refresh() {
        final List<Note> notes = composition.get();
        display.refresh(notes);
    }

    public void right() {
        composition.next();
        refresh();
    }

    public void up() {
        System.out.println("compositioning it up.");
        composition.up();
        refresh();
    }

    public void add(final Note note) {
        composition.add(note);
        refresh();
    }

    public void clear() {
        composition.clear();
        refresh();
    }

    public List<Note> getNotes() {
        return composition.get();
    }

    public Channel channel() {
        return this.channel;
    }

    public Component view() {
        return display.view();
    }

    public void setSelected(final boolean selected) {
        display.setSelected(selected);
    }

    public Option<Instrument> getInstrument() {
        return instrumentOption;
    }
}
