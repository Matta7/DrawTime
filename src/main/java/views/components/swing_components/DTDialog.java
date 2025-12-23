package views.components.swing_components;

import javax.swing.*;
import java.awt.*;

public abstract class DTDialog extends JDialog {

    protected DTDialog(Frame parent, boolean modal) {
        super(parent, modal);
        initialize();
    }

    /**
     * Initialize the frame
     */
    protected abstract void initialize();
}
