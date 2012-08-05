package dsq.cacophony.io;

import dsq.cacophony.shame.Option;

import java.awt.*;
import java.io.File;

public interface FileBrowser {
    
    // FIX 17/03/12 Maybe strong type description
    Option<File> open(Container window, Extension extension, String description);
    Option<File> saveAs(Container window, Extension extension, String description);
}