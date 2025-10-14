package views.mainframe;

import models.constants.ui.TimePanelConstants;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class TimePanel extends JPanel {

    private NumberFormatter formatter;

    public TimePanel() {
        initializeFormatter();
        initialize();
    }

    /**
     * Initialize formatter for text field
     */
    private void initializeFormatter() {
        formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(TimePanelConstants.TIME_PER_IMAGE_MIN);
        formatter.setMaximum(TimePanelConstants.TIME_PER_IMAGE_MAX_DEFAULT);
        formatter.setAllowsInvalid(false);
    }

    /**
     * Initialize Panel
     */
    private void initialize() {
        JLabel label;
        JFormattedTextField textField;
        JSlider timeSlider;


        // Label
        label = new JLabel("Time per image (minute) :");


        // Text field
        textField = new JFormattedTextField(formatter);
        textField.setText(String.valueOf(TimePanelConstants.TIME_PER_IMAGE_INIT));


        // Slider
        timeSlider = new JSlider(SwingConstants.HORIZONTAL, TimePanelConstants.TIME_PER_IMAGE_MIN, TimePanelConstants.TIME_PER_IMAGE_MAX_DEFAULT, TimePanelConstants.TIME_PER_IMAGE_INIT);
        timeSlider.setToolTipText("Miaou");
        timeSlider.setPreferredSize(new Dimension(400, 50));

        timeSlider.setSnapToTicks(true);
        timeSlider.setMinorTickSpacing(TimePanelConstants.MINOR_TICK_SPACING);
        timeSlider.setMajorTickSpacing(TimePanelConstants.MAJOR_TICK_SPACING);

        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);


        // Event listeners
        ChangeListener changeSliderListener = _ -> textField.setText(String.valueOf(timeSlider.getValue()));
        timeSlider.addChangeListener(changeSliderListener);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                int textFieldValue = Integer.parseInt(textField.getText());
                if (textFieldValue != timeSlider.getValue()) {
                    timeSlider.removeChangeListener(changeSliderListener);
                    timeSlider.setSnapToTicks(false);
                    timeSlider.setValue(textFieldValue);
                    timeSlider.addChangeListener(changeSliderListener);
                    timeSlider.setSnapToTicks(true);
                }
                // TODO : Change model
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Handled in insertUpdate method
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Nothing to do
            }
        });


        // Add components
        add(label);
        add(textField);
        add(timeSlider);
    }
}
