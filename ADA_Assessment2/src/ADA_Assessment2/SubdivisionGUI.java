package ADA_Assessment2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @author Kieran
 */
public class SubdivisionGUI {
    
    public SubdivisionGUI() {
        JFrame frame = setUpJFrame();
        frame.getContentPane().add(new LandCreatorPanel());
        frame.pack();
    }
    
    private JFrame setUpJFrame() {
        JFrame frame = new JFrame("Land Subdivider");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    
    private class LandCreatorPanel extends JPanel {
        
        private final JTextField widthTextField;
        private final JTextField heightTextField;
        
        public LandCreatorPanel() {
            super(new BorderLayout());
            
            //Set up program name
            JLabel programName = new JLabel("Land Subdivider", JLabel.CENTER);
            
            //Set up text field area
            JPanel sizePanel = new JPanel(new GridLayout(2,2));
            JLabel widthLabel = new JLabel("Width", JLabel.CENTER);
            JLabel heightLabel = new JLabel("Height", JLabel.CENTER);
            this.widthTextField = new JTextField();
            this.heightTextField = new JTextField();
            //Add text field area element to size panel
            sizePanel.add(widthLabel);
            sizePanel.add(heightLabel);
            sizePanel.add(this.widthTextField);
            sizePanel.add(this.heightTextField);

            //Add elements to panel
            super.add(programName, BorderLayout.NORTH);
            super.add(sizePanel, BorderLayout.CENTER);
            //super.add(this, BorderLayout.LINE_END);
            //super.add(this, BorderLayout.SOUTH);
        }
    }
    
    private class LandDisplayPanel extends JPanel {
        
    }
}