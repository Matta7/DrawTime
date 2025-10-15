package views.mainframe;

import models.commands.CommandInvoker;
import models.commands.concrete.StartCommand;

import javax.swing.*;

public class StartPanel extends JPanel {

    public StartPanel() {
        initialize();
    }

    /**
     * Initialize Panel
     */
    private void initialize() {
        JButton startButton;


        // Start button
        startButton = new JButton("Start");
        startButton.addActionListener(_ -> CommandInvoker.getInstance().executeCommand(new StartCommand()));


        // Add components
        add(startButton);
    }
}
