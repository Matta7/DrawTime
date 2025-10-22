package views.imageframe;

import controllers.Controller;
import models.objects.CountdownTimer;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ControlBarPanel extends JPanel {

    private final CountdownTimer timer;

    private JLabel timerLabel;

    public ControlBarPanel() {
        timer = Controller.getInstance().getTimer();

        initialize();

        // Allow to update label whenever timer tick
        timer.addTickAction(() -> timerLabel.setText(timer.getFormattedTime()));
    }

    private void initialize() {
        setBorder(new MatteBorder(3, 0, 0, 0, Color.DARK_GRAY));

        JButton previousButton;
        JButton pauseButton;
        JButton skipButton;


        // Timer label
        timerLabel = new JLabel(timer.getFormattedTime());


        // Pause button
        pauseButton = new JButton("Pause");
        pauseButton.setToolTipText("Pause timer");


        // Skip button
        skipButton = new JButton("Skip");
        skipButton.setToolTipText("Skip the image");


        // Add components
        add(timerLabel);
        add(pauseButton);
        add(skipButton);
    }
}
