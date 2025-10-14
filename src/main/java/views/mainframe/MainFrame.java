package views.mainframe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        initialize();
    }

    /**
     * Initialize the frame
     */
    private void initialize() {
        setTitle("Draw Time");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(700, 300));
        setResizable(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

        // File path panel
        FilePathPanel filePathPanel = new FilePathPanel();
        filePathPanel.setBorder(new EmptyBorder(25, 0, 0, 0));

        // Time panel
        TimePanel timePanel = new TimePanel();

        // TODO : Number of images

        // Start button
        StartPanel startPanel = new StartPanel();

        // Add components
        contentPane.add(filePathPanel);
        contentPane.add(timePanel);
        contentPane.add(startPanel);

        setContentPane(contentPane);

        pack();
        validate();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
