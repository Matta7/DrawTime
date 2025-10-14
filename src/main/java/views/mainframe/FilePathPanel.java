package views.mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FilePathPanel extends JPanel {

    public FilePathPanel() {
        initialize();
    }

    /**
     * Initialize Panel
     */
    private void initialize() {
        JLabel label;
        JTextField textField;
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
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fc.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                textField.setText(fc.getSelectedFile().getPath());
            }
        });

        textField.addActionListener(chooseFileEvent);
        chooseFileButton.addActionListener(chooseFileEvent);


        // Add components
        add(label);
        add(textField);
        add(chooseFileButton);
    }
}
