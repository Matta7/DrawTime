package views.imageframe;

import controllers.Controller;
import models.commands.CommandInvoker;
import models.commands.concrete.*;
import models.objects.CountdownTimer;
import models.services.ImageService;
import views.components.InterchangeableComponents;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class ControlBarPanel extends JPanel {

    private final CountdownTimer timer;
    private final ImageService imageService;

    private JButton previousButton;
    private JButton skipButton;

    private JLabel timerLabel;
    private JLabel imageLeftLabel;

    public ControlBarPanel() {
        Controller controller = Controller.getInstance();
        timer = controller.getTimer();
        imageService = controller.getImageService();

        initialize();

        // Allow to update label whenever timer tick
        timer.addTickAction(() -> timerLabel.setText(timer.getFormattedTime()));

        // Update
        controller.getImageService().addImageChangedAction(this::onImageChanged);
    }

    /**
     * Initialize the component
     */
    private void initialize() {
        setBorder(new MatteBorder(3, 0, 0, 0, Color.DARK_GRAY));

        JButton resetButton;
        JButton pauseButton;
        JButton resumeButton;

        InterchangeableComponents<JButton, JButton> pauseResumeButtonComponent;


        // Timer label
        timerLabel = new JLabel(timer.getFormattedTime());


        // Pause button
        pauseButton = new JButton("Pause");
        pauseButton.setToolTipText("Pause timer");


        // Resume button
        resumeButton = new JButton("Resume");
        resumeButton.setToolTipText("Resume timer");


        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setToolTipText("Reset timer");


        // Skip button
        skipButton = new JButton("Skip");
        skipButton.setToolTipText("Skip the image");


        // Previous button
        previousButton = new JButton("Previous");
        previousButton.setToolTipText("Go to previous image");
        previousButton.setEnabled(false);


        // Interchangeable components with pause and resume button
        pauseResumeButtonComponent = new InterchangeableComponents<>(pauseButton, resumeButton);


        // Image left label
        imageLeftLabel = new JLabel((imageService.countRemainingImages() - 1) + " images left");
        imageLeftLabel.setToolTipText(imageService.countImages() + " images in total");


        // Event listeners
        resetButton.addActionListener(_ -> CommandInvoker.getInstance().executeCommand(new ResetTimerCommand()));

        pauseButton.addActionListener(_ -> {
            CommandInvoker.getInstance().executeCommand(new PauseTimerCommand());
            pauseResumeButtonComponent.swap();
            resumeButton.requestFocus();
        });

        resumeButton.addActionListener(_ -> {
            CommandInvoker.getInstance().executeCommand(new ResumeTimerCommand());
            pauseResumeButtonComponent.swap();
            pauseButton.requestFocus();
        });

        previousButton.addActionListener(_ -> CommandInvoker.getInstance().executeCommand(new PreviousImageCommand()));

        skipButton.addActionListener(_ -> CommandInvoker.getInstance().executeCommand(new NextImageCommand()));


        // Add components
        add(timerLabel);
        add(previousButton);
        add(resetButton);
        add(pauseResumeButtonComponent);
        add(skipButton);
        add(imageLeftLabel);
    }


    // EVENT HANDLER METHODS

    /**
     * Method called when image has changed
     */
    private void onImageChanged() {
        int imageLeft = imageService.countRemainingImages();
        int imageLeftWithoutCurrentImage = imageLeft - 1;

        imageLeftLabel.setText(imageLeftWithoutCurrentImage + " images left");

        if (imageLeftWithoutCurrentImage > 0) {
            skipButton.setEnabled(true);
            previousButton.setEnabled(imageLeft != imageService.countImages());
        } else {
            skipButton.setEnabled(false);
        }
    }
}
