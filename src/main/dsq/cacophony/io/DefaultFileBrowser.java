package dsq.cacophony.io;

import dsq.cacophony.shame.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;

public class DefaultFileBrowser implements FileBrowser {
    
    private final JFileChooser browser = new JFileChooser();
    private final ExtensionFilters exFilters = new DefaultExtensionFilters();

    public DefaultFileBrowser(final File currentDirectory) {
        if (!currentDirectory.isDirectory())  throw new RuntimeException("Must specify a directory for initial directory of file browser.");
        browser.setCurrentDirectory(currentDirectory);
        browser.setAcceptAllFileFilterUsed(false);                
    }
    
    private void clearFilters() {
        final FileFilter[] filters = browser.getChoosableFileFilters();
        for (final FileFilter filter : filters) {
            browser.removeChoosableFileFilter(filter);
        }
    }
    
    public Option<File> open(final Container window, final Extension extension, final String description) {
        setFilter(extension, description);
        final int success = browser.showOpenDialog(window);
        return selected(success);
    }

    private Option<File> selected(final int success) {
        return success == JFileChooser.APPROVE_OPTION ? selected(browser.getSelectedFile()) : new NoneOption<File>();
    }

    private SomeOption<File> selected(final File file) {
        // FIX 17/03/12 Hmmm. Try and force-suffix the extension?
        return new SomeOption<File>(file);
    }

    public Option<File> saveAs(final Container window, final Extension extension, final String description) {
        setFilter(extension, description);
        final int success = browser.showSaveDialog(window);
        final Option<File> selectedFile = selected(success);
        return selectedFile.fold(new G<File, Option<File>>() {
            public Option<File> g(final File input) {
                if (!exFilters.matches(input, extension)) {
                    // FIX 17/03/12 Wrap my own dialogs
                    final String ext = extension.name().toLowerCase();
                    final int answer = JOptionPane.showConfirmDialog(window, "You have not specified the expected extension type " + 
                        "for a " + ext + " file (*." + ext + ").\nWould you like Cacophony to add the expected extension type to " +
                        "your chosen file:\n" + input.getPath() + "?",
                        "Save Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                    return answer == JOptionPane.YES_OPTION ? massageExtension(selectedFile, extension) :
                            answer == JOptionPane.NO_OPTION ? selectedFile : saveAs(window, extension, description);
                } else {
                    return selectedFile;
                }
            }
                    
        }, new F<Option<File>>() {
            public Option<File> f() {
                return selectedFile;
            }
        });
    }

    private Option<File> massageExtension(final Option<File> selectedFile, final Extension extension) {
        return selectedFile.fold(new G<File, Option<File>>() {
            public Option<File> g(final File input) {
                return new SomeOption<File>(new File(input.getPath() + "." + extension.name().toLowerCase()));
            }
        }, new F<Option<File>>() {
            public Option<File> f() {
                return selectedFile;
            }
        });
    }

    private void setFilter(final Extension extension, final String description) {
        clearFilters();
        final FileFilter filter = exFilters.filter(extension, description);
        browser.addChoosableFileFilter(filter);
        browser.setFileFilter(filter);
    }
}
