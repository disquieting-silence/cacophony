package dsq.cacophony.io;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public interface ExtensionFilters {
    FileFilter filter(Extension extension, String description);

    boolean matches(File file, Extension extension);
}
