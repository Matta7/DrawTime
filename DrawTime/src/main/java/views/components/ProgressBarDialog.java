package views.components;

import views.components.swing_components.DTDialog;

import javax.swing.*;
import java.awt.*;

public class ProgressBarDialog extends DTDialog {

    public ProgressBarDialog(Frame parent) {
        super(parent, false);
    }

    @Override
    protected void initialize() {
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        progressBar.setString("Loading images ...");
        progressBar.setStringPainted(true);


        setSize(new Dimension(380, 80));
        setResizable(false);
        setLocationRelativeTo(null);


        add(progressBar);
        setVisible(true);
    }
}
