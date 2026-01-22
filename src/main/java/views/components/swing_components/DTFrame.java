package views.components.swing_components;

import javax.swing.*;

public abstract class DTFrame extends JFrame {

    protected DTFrame() {
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

    public void showCustomOptionDialog(String message, String title, String[] options, Runnable[] actions) {
        int choice = JOptionPane.showOptionDialog(this, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice < actions.length && choice > -1) {
            actions[choice].run();
        }
    }

    /**
     * Initialize the frame
     */
    protected abstract void initialize();
}
