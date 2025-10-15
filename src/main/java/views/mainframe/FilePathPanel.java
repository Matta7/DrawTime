package views.mainframe;

import controllers.Controller;
import models.observer.objectlisteners.ParametersListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FilePathPanel extends JPanel implements ParametersListener {

    JTextField textField;

    public FilePathPanel() {
        initialize();

        // Listen to parameters
        Controller.getInstance().getParameters().addListener(this);
    }

    /**
     * Initialize Panel
     */
    private void initialize() {
        JLabel label;
        JButton chooseFileButton;


        // Label
        label = new JLabel("Directory path :");


        // Text field
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(450, 25));
        textField.setEditable(false);


        // Choose file button
        chooseFileButton = new JButton();
        chooseFileButton.setText("Choose Directory");


        // Event listeners
        ActionListener chooseFileEvent = (_ -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getPath();
                textField.setText(fileChooser.getSelectedFile().getPath());
                Controller.getInstance().getParameters().setFilePath(path);
            }
        });

        textField.addActionListener(chooseFileEvent);
        chooseFileButton.addActionListener(chooseFileEvent);


        // Add components
        add(label);
        add(textField);
        add(chooseFileButton);
    }

    @Override
    public void onFilePathChange(String oldFilePath, String newFilePath) {
        if (!textField.getText().equals(newFilePath)) {
            textField.setText(newFilePath);
        }
    }

    @Override
    public void onTimePerImage(int oldTimePerImage, int newTimePerImage) {
        // Nothing to do
    }

    @Override
    public void onChange(Object source) {
        // Nothing to do
    }
}
