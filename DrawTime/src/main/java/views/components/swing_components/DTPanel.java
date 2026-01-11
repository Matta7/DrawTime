package views.components.swing_components;

import javax.swing.*;

public abstract class DTPanel extends JPanel {

    protected DTPanel() {
        initialize();
    }

    public void showPlainMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }

    public void showInformationMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showWarningMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public void showErrorMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Initialize the frame
     */
    protected abstract void initialize();
}
