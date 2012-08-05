package dsq.cacophony.ui;

public interface InfoPanel extends JView {
    // FIX 4/03/12 Should make mode an enum.
    void display(String mode, int octave, String duration);
    void refresh();
}
