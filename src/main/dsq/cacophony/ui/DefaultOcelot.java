package dsq.cacophony.ui;

import dsq.cacophony.data.Channel;
import dsq.cacophony.data.Note;
import dsq.cacophony.edge.javax.sound.midi.Instrument;
import dsq.cacophony.shame.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultOcelot implements Ocelot {

    private static final int NUM_VISIBLE_VIEWS = 4;
    private final SwitchableView[] sections = new SwitchableView[NUM_VISIBLE_VIEWS];

    // FIX 17/03/12 Spiking. Not happy with this knowing all the views.
    private List<Toridor> candidates = new ArrayList<Toridor>();
    
    private int index = 0;
    private SwitchableView active;

    public DefaultOcelot() {
        for (int i = 0; i < sections.length; i++) {
            sections[i] = new DefaultSwitchableView();
        }
        active = sections[index];
        active.setSelected(true);
    }

    public void cycle() {
        // FIX 3/03/12 Only cycles down.
        index = (index + 1) % sections.length;
        active.setSelected(false);
        active = sections[index];
        active.setSelected(true);
    }

    public SwitchableView[] views() {
        return sections;
    }

    public void setActiveChannel(final Channel channel) {
        if (channel.value < 0 || channel.value >= candidates.size()) 
            throw new RuntimeException("Cannot set channel to: " + channel.value);
        final Toridor toridor = candidates.get(channel.value);
        active.show(toridor);
    }
    public void setCandidates(final List<Toridor> toridors) {
        this.candidates = toridors;
    }

    private void runOnActive(final G0<Toridor> g) {
        active.current().fold(new G<Toridor, Boolean>() {
            public Boolean g(final Toridor input) {
                g.g(input);
                return true;
            }
        }, new F<Boolean>() {
            public Boolean f() {
                return false;
            }
        });
    }

    public void left() {
        runOnActive(new G0<Toridor>() {
            public void g(final Toridor toridor) {
                toridor.left();
            }
        });
    }

    public void right() {
        runOnActive(new G0<Toridor>() {
            public void g(final Toridor toridor) {
                toridor.right();
            }
        });
    }

    public void up() {
        runOnActive(new G0<Toridor>() {
            public void g(final Toridor toridor) {
                System.out.println("Past first option.");
                toridor.up();
            }
        });
    }

    public void add(final Note note) {
        runOnActive(new G0<Toridor>() {
            public void g(final Toridor toridor) {
                toridor.add(note);
            }
        });
    }

    public void clear() {
        runOnActive(new G0<Toridor>() {
            public void g(final Toridor toridor) {
                toridor.clear();
            }
        });
    }

    public List<Note> getNotes() {
        final Option<List<Note>> optionNotes = active.current().bind(new G<Toridor, Option<List<Note>>>() {
            public Option<List<Note>> g(final Toridor input) {
                return new SomeOption<List<Note>>(input.getNotes());
            }
        });
        // FIX 17/03/12 Really shouldn't be doing this.
        return optionNotes.getOr(new ArrayList<Note>());
    }

    public Channel channel() {
        final Option<Channel> channelOption = active.current().bind(new G<Toridor, Option<Channel>>() {
            public Option<Channel> g(final Toridor input) {
                return new SomeOption<Channel>(input.channel());
            }
        });
        // FIX 17/03/12 Ugh. Shouldn't EVER use getOr.
        return channelOption.getOr(new Channel(Channel.NO_CHANNEL));
    }

    public Option<Instrument> getInstrument() {
        return active.current().bind(new G<Toridor, Option<Instrument>>() {
            public Option<Instrument> g(final Toridor input) {
                return input.getInstrument();
            }
        });
    }

    public Component view() {
        return active.view();
    }
}
