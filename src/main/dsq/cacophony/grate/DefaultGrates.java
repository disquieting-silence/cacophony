package dsq.cacophony.grate;

import dsq.cacophony.data.GrateModes;
import dsq.cacophony.operation.common.NoOperation;
import dsq.cacophony.operation.common.Operation;
import dsq.cacophony.shame.*;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class DefaultGrates implements Grates {
    
    private final Map<String, Grate> grates = new HashMap<String, Grate>();
    private Option<GrateModes> current = new NoneOption<GrateModes>();

    public void register(final GrateModes mode, final Grate grate) {
        grates.put(mode.name(), grate);
        current = current.setIfUnset(mode);
    }

    // FIX 4/03/12 Just a bit convoluted.
    public Operation handle(final KeyEvent event) {
        return current.fold(new G<GrateModes, Operation>() {
            public Operation g(final GrateModes input) {
                final Option<Grate> grateOption = get(input);
                return grateOption.fold(new G<Grate, Operation>() {
                            public Operation g(final Grate input) {
                                return input.key(event);
                            }
                        }, new F<Operation>() {
                    public Operation f() {
                        return new NoOperation();
                    }
                }
                );
            }
        }, new F<Operation>() {
            public Operation f() {
                return new NoOperation() {};
            }
        });        
    }

    public Option<GrateModes> getMode() {
        return current;            
    }

    public void setMode(final GrateModes mode) {
        current = new SomeOption<GrateModes>(mode);
    }

    private Option<Grate> get(final GrateModes mode) {
        final Grate grate = grates.get(mode.name());
        // FIX 4/03/12 Maybe I should just break?
        return grate != null ? new SomeOption<Grate>(grate) : new NoneOption<Grate>();
    }

}
