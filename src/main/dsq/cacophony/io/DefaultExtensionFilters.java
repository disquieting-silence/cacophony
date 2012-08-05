package dsq.cacophony.io;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class DefaultExtensionFilters implements ExtensionFilters {
    public FileFilter filter(final Extension extension, final String description) {
        return new FileFilter() {
            
            public boolean accept(final File file) {
                return file != null && (file.isDirectory() || matches(file, extension));    
            }

            public String getDescription() {
                return description;
            }
        };
    }

    public boolean matches(final File file, final Extension extension) {
        final String name = file.getName().toLowerCase();
        final String ex = extension.name().toLowerCase();
        return name.endsWith("." + ex);
    }
}
