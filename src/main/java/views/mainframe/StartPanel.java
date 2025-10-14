package views.mainframe;

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
        // TODO : Add action listener

        
        // Add components
        add(startButton);
    }
}
