package views.mainframe;

import javax.swing.*;
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

        setPreferredSize(new Dimension(800, 600));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(new TimePanel(), BorderLayout.CENTER);

        setContentPane(contentPane);

        pack();
        validate();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
