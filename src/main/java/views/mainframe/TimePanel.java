package views.mainframe;

import models.constants.ui.TimePanelConstants;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class TimePanel extends JPanel {

    public TimePanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));


        // Label
        JLabel label = new JLabel("Time :");

        // Text field
        JTextField textField = new JTextField("io");

        // Slider
        JSlider timeSlider = new JSlider(SwingConstants.HORIZONTAL, TimePanelConstants.TIME_MIN, TimePanelConstants.TIME_MAX_DEFAULT, TimePanelConstants.TIME_INIT);
        timeSlider.setToolTipText("Miaou");
        //timeSlider.addChangeListener(this);

        timeSlider.setSnapToTicks(true);
        timeSlider.setMinorTickSpacing(TimePanelConstants.MINOR_TICK_SPACING);
        timeSlider.setMajorTickSpacing(TimePanelConstants.MAJOR_TICK_SPACING);

        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);

        add(label);
        add(textField);
        add(timeSlider);
    }
}
