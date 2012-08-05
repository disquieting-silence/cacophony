package dsq.cacophony.ui;

import dsq.cacophony.data.Channel;

import java.util.List;

public interface Ocelot extends Toridor {
    void cycle();
    
    SwitchableView[] views();
    // FIX 17/03/12 Is this needed?
    void setActiveChannel(Channel channel);
    void setCandidates(List<Toridor> toridors);
}
