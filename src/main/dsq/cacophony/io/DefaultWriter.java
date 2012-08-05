package dsq.cacophony.io;

import java.io.IOException;
import java.util.List;

public class DefaultWriter implements Writer {
    
    private final ChannelWriter cWriter = new DefaultChannelWriter();
    
    public void write(final Project project, final java.io.Writer writer) {
        try {
            unsafeWrite(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void unsafeWrite(final Project project, final java.io.Writer writer) throws IOException {
        final List<InstrumentNotes> contents = project.contents;
        for (final InstrumentNotes content : contents) {
            writer.write(content.instrument + "\n");
            final String notes = cWriter.write(content.notes);
            writer.write(notes + "\n");
        }
    }


}
