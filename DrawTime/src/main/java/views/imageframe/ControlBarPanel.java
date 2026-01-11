package views.imageframe;

import static models.constants.files.IconsFileConstants.*;

import controllers.Controller;
import models.commands.CommandInvoker;
import models.commands.concrete.*;
import models.objects.CountdownTimer;
import models.services.ImageLoaderService;
import models.services.ImageService;
import views.components.InterchangeableComponents;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.IOException;

public class ControlBarPanel extends JPanel {

    private static final int BUTTONS_SCALE = 32;

    private final CountdownTimer timer;
    private final ImageService imageService;

    private JButton pauseButton;
    private JButton resumeButton;
    private JButton resetButton;
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

        InterchangeableComponents<JButton, JButton> pauseResumeButtonComponent;


        // Timer label
        timerLabel = new JLabel(timer.getFormattedTime());


        // Load icons and buttons
        try {
            ImageLoaderService imageLoaderService = new ImageLoaderService();

            ImageIcon pauseTimerIcon = new ImageIcon(imageLoaderService.loadImage(PAUSE_ICON, BUTTONS_SCALE));
            ImageIcon resumeTimerIcon = new ImageIcon(imageLoaderService.loadImage(PLAY_ICON, BUTTONS_SCALE));
            ImageIcon resetTimerIcon = new ImageIcon(imageLoaderService.loadImage(RESET_ICON, BUTTONS_SCALE));
            ImageIcon nextImageIcon = new ImageIcon(imageLoaderService.loadImage(NEXT_ICON, BUTTONS_SCALE));
            ImageIcon previousImageIcon = new ImageIcon(imageLoaderService.loadImage(PREVIOUS_ICON, BUTTONS_SCALE));

            pauseButton = new JButton(pauseTimerIcon);
            resumeButton = new JButton(resumeTimerIcon);
            resetButton = new JButton(resetTimerIcon);
            skipButton = new JButton(nextImageIcon);
            previousButton = new JButton(previousImageIcon);
        } catch (IOException _) {
            pauseButton = new JButton("Pause");
            resumeButton = new JButton("Resume");
            resetButton = new JButton("Reset");
            skipButton = new JButton("Skip");
            previousButton = new JButton("Previous");
        } finally {
            previousButton.setEnabled(false);
        }


        // Tooltips
        pauseButton.setToolTipText("Pause timer");
        resumeButton.setToolTipText("Resume timer");
        resetButton.setToolTipText("Reset timer");
        skipButton.setToolTipText("Next image");
        previousButton.setToolTipText("Previous image");


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
