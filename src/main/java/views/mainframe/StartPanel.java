package views.mainframe;

import controllers.Controller;
import models.commands.CommandInvoker;
import models.commands.concrete.StartCommand;
import models.observer.objectlisteners.ParametersListener;

import javax.swing.*;

public class StartPanel extends JPanel implements ParametersListener {

    private JButton startButton;

    public StartPanel() {
        initialize();

        // Listen to parameters
        Controller.getInstance().getParameters().addListener(this);
    }

    /**
     * Initialize Panel
     */
    private void initialize() {
        // Start button
        startButton = new JButton("Start");
        startButton.addActionListener(_ -> CommandInvoker.getInstance().executeCommand(new StartCommand()));
        startButton.setEnabled(false);


        // Add components
        add(startButton);
    }


    // EVENT HANDLER METHODS

    @Override
    public void onFilePathChange(String oldFilePath, String newFilePath) {
        // Nothing to do
    }

    @Override
    public void onTimePerImage(int oldTimePerImage, int newTimePerImage) {
        // Nothing to do
    }

    @Override
    public void onValidityChange(boolean valid) {
        startButton.setEnabled(valid);
    }

    @Override
    public void onChange(Object source) {
        // Handled in onValidityChange method
    }
}
