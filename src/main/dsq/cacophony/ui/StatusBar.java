package dsq.cacophony.ui;

import javax.swing.*;
import java.awt.*;

public interface StatusBar {
    void setMessage(String message);
    void clearMessage();
    Container view();
}
