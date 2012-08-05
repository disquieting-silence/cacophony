package dsq.cacophony.ui;

import dsq.cacophony.control.Control;
import dsq.cacophony.control.DefaultControl;
import dsq.cacophony.data.GrateModes;
import dsq.cacophony.edge.javax.sound.midi.Synthesizer;
import dsq.cacophony.io.DefaultReader;
import dsq.cacophony.io.Reader;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.G;
import dsq.cacophony.shame.H;
import dsq.cacophony.core.DefaultMidiInstruments;
import dsq.cacophony.core.DefaultSoundSystem;
import dsq.cacophony.core.MidiInstruments;
import dsq.cacophony.core.SoundSystem;
import dsq.cacophony.grate.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.List;

public class MainFrame extends JFrame {

    private final SoundSystem system = new DefaultSoundSystem();
    private final Reader reader = new DefaultReader();

    // FIX 25/02/12 Hmm ... how do I narrow this interface and keep it swing?
    private final InfoPanel infoPanel = new DefaultInfoPanel();
    
    private final StatusBar status = new DefaultStatusBar();

    private final Control control = new DefaultControl();
    
    private final Grates grates = new DefaultGrates();
    private final Meerkat meerkat = new DefaultMeerkat();
    
    private final MidiInstruments instruments;

   
    private final ToridorManager manager;
    private final Ocelot ocelot = new DefaultOcelot();
    
    public MainFrame() {
        super("Cacophony");
        setFocusTraversalKeysEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        final Synthesizer syn = system.getSynthesizer();
        instruments = new DefaultMidiInstruments(syn);

        grates.register(GrateModes.KEYS, new DefaultKeyGrate());
        grates.register(GrateModes.COMMAND, new DefaultCommandGrate(instruments, status));

        
        final JPanel mainView = new JPanel();
        add(mainView, BorderLayout.CENTER);
        
        add(status.view(), BorderLayout.SOUTH);

        final SwitchableView[] switchableViews = ocelot.views();         
        mainView.setLayout(new GridLayout(switchableViews.length + 1, 1));

        final List<Toridor> views = meerkat.views(reader.read(new StringReader("Vibraphone\n" +
                "50,51,52_16|54_32|53_16|56_16|55_16|58_16|55_16|56_16|58_16|53_16|68_16|70_16|68_16|70_16|68_16|70_16|67_16\n" +
                "String Ensemble 1\n" +
                "70_64|70_16|61,62_16|64_32|53_16|56_16|55_16|58_16|55_16|56_16|58_16|53_16|68_16|70_16|68_16|70_16|68_16|70_16|67_16\n" +
                "Flute\n" +
                "70_64|61,62_16|64_32|53_16|56_16|55_64\n" +
//                "Lead 8 (bass + lead)\n" +
//                "70_64|61,62_16\n" +
                "Seashore\n" +
                "70_64|61,62_16|64_32|53_16|56_16|55_16|58_16|70_16|68_16|70_16|67_16\n" +
                "Gunshot\n" +
                "70_64|61,62_16|64_32|53_16|56_16|55_16|58_16|55_16|56_16|58_16|53_16|68_16|70_16|68_16|70_16|68_16|70_16|67_16")
        ), instruments);


        for (final SwitchableView switchableView : switchableViews) {
            mainView.add(switchableView.view());
        }

        for (int i = 0; i < views.size(); i++) {
            final Toridor toridor = views.get(i);
            if (i < switchableViews.length) switchableViews[i].show(toridor);
        }
        
        ocelot.setCandidates(views);
        
        manager = new DefaultToridorManager(views);
        
        mainView.add(infoPanel.view());
                
        infoPanel.refresh();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent e) {
                final Operation operation = grates.handle(e);
                status.clearMessage();
                operation.fold(
                    new ChannelOp() {
                        public boolean op(final H<Ocelot, Control, Boolean> h) {
                            return h.h(ocelot, control);
                        }
                    }, new ControlOp() {
                        public boolean op(final G<Control, Boolean> g) {
                            g.g(control);
                            updateInfo();
                            return true;
                        }
                    }, new SystemOp() {
                        public boolean op(final H<SoundSystem, ToridorManager, Boolean> h) {
                            return h.h(system, manager);
                        }
                    }, new UiOp() {
                        public boolean op(final G<Grates, Boolean> g) {
                            g.g(grates);
                            updateInfo();
                            return true;
                        }
                    }, new NoOp() {
                        public boolean op() {
                            // FIX 17/03/12 This may be a bit verbose as it picks up when a meta key is pressed.
                            return true;
                        }
                    }
                );
            }
        });
    }
    
    private void updateInfo() {
        final GrateModes mode = grates.getMode().getOr(GrateModes.NONE);
        infoPanel.display(mode.name(), control.getOctave(), control.getDurationText());
    }
}
