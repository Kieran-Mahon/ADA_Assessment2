package ADA_Assessment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SubdivisionGUI {
    
    private final JFrame frame;
    
    public SubdivisionGUI() {
        this.frame = setUpJFrame();
        changePanel(new LandCreatorPanel(this));
    }
    
    //Set up frame
    private JFrame setUpJFrame() {
        JFrame tempFrame = new JFrame("Land Subdivider");
        tempFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tempFrame.pack();
        tempFrame.setResizable(false);
        tempFrame.setVisible(true);
        return tempFrame;
    }
    
    //Change panel
    private void changePanel(JPanel panel) {
        this.frame.getContentPane().removeAll();
        this.frame.add(panel);
        this.frame.pack();
    }
    
    //Panel to create land
    private class LandCreatorPanel extends JPanel {
        
        private final JTextField widthTextField;
        private final JTextField heightTextField;
        private int width;
        private int height;
        
        private final JLabel errorLabel;
        
        private final SubdivisionGUI gui;
        
        public LandCreatorPanel(SubdivisionGUI gui) {
            super(new BorderLayout(10,10));
            this.gui = gui;
            
            //Set up program name
            JLabel programName = new JLabel("Land Subdivider", JLabel.CENTER);
            programName.setFont(new Font("Dialog", Font.BOLD, 20));
            
            //Set up text field area
            JPanel sizePanel = new JPanel(new GridLayout(2, 2, 10, 0));
            JLabel widthLabel = new JLabel("Width", JLabel.CENTER);
            JLabel heightLabel = new JLabel("Height", JLabel.CENTER);
            this.widthTextField = new JTextField();
            this.heightTextField = new JTextField();
            //Add text field area element to size panel
            sizePanel.add(widthLabel);
            sizePanel.add(heightLabel);
            sizePanel.add(this.widthTextField);
            sizePanel.add(this.heightTextField);
            sizePanel.setBorder(new EmptyBorder(0, 10, 0, 10));
            
            //Set up button area (make button then add its listener)
            JPanel methodPanel = new JPanel(new GridLayout(1, 5, 10, 0));
            JButton bruteForceButton = new JButton("Brute Force");
            bruteForceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkForErrors() == false) {
                        gui.changePanel(new LandDisplayPanel(gui, new BruteForce(width, height)));
                    }
                }
            });
            JButton greedyButton = new JButton("Greedy");
            greedyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkForErrors() == false) {
                        gui.changePanel(new LandDisplayPanel(gui, new Greedy(width, height)));
                    }
                }
            });
            JButton exactButton = new JButton("Exact");
            exactButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkForErrors() == false) {
                        gui.changePanel(new LandDisplayPanel(gui, new Exact(width, height)));
                    }
                }
            });
            
            //Add button area elements to method panel
            methodPanel.add(bruteForceButton);
            methodPanel.add(greedyButton);
            methodPanel.add(exactButton);
            methodPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
            
            //Add the text field area and button area to a temp panel
            JPanel tempPanel = new JPanel(new BorderLayout());
            tempPanel.add(sizePanel, BorderLayout.CENTER);
            tempPanel.add(methodPanel, BorderLayout.SOUTH);
            
            //Set up error label
            this.errorLabel = new JLabel("", JLabel.CENTER);
            this.errorLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
            
            //Add elements to panel
            super.add(programName, BorderLayout.NORTH);
            super.add(tempPanel, BorderLayout.CENTER);
            super.add(this.errorLabel, BorderLayout.SOUTH);
        }
        
        //Checks for errors in text fields
        private boolean checkForErrors() {
            //Check if either are empty
            if (this.widthTextField.getText().isEmpty() || this.heightTextField.getText().isEmpty()) {
                changeErrorMessage("One or more text fields are empty!");
                return true;
            }
            
            //Try to convert text fields from string to int
            try {
                this.width = Integer.parseInt(this.widthTextField.getText());
                this.height = Integer.parseInt(this.heightTextField.getText());
            } catch (NumberFormatException ex){
                changeErrorMessage("Value is not an integer!");
                return true;
            }
            
            //Using boolean here because Java does not like me returning true
            //on the if statement below if there is an error
            boolean error = false;
            
            //Make sure the values are more than 0
            if ((this.width) <= 0 || (this.height <= 0)) {
                changeErrorMessage("One or more values are less or equal to 0!");
                error = true;
            }
            
            return error;
        }
        
        private void changeErrorMessage(String text) {
            //Set text to nothing
            this.errorLabel.setText("");
            this.errorLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
            this.gui.frame.pack();
            
            //Return if null or empty
            if (text == null) {
                return;
            }
            if (text.isEmpty()) {
                return;
            }
            
            //Set real text
            this.errorLabel.setText(text);
            this.errorLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
            this.gui.frame.pack();
        }
    }
    
    private class LandDisplayPanel extends JPanel {
        
        public LandDisplayPanel(SubdivisionGUI gui, Subdivision subdivision) {
            super(new BorderLayout(10, 10));
            
            //Create pre division land area
            Land preDivision = new Land(0, 0, subdivision.width, subdivision.height);
            JLabel preNameLabel = new JLabel("Original Area", JLabel.CENTER);
            LandArea beforeSubdivsion = new LandArea(preDivision);
            JLabel preCostLabel = new JLabel("$" + Integer.toString(subdivision.getLandPrice(preDivision)), JLabel.CENTER);
            
            JPanel preDivisionPanel = new JPanel(new BorderLayout(10,10));
            preDivisionPanel.add(preNameLabel, BorderLayout.NORTH);
            preDivisionPanel.add(beforeSubdivsion, BorderLayout.CENTER);
            preDivisionPanel.add(preCostLabel, BorderLayout.SOUTH);
            
            JLabel postNameLabel = new JLabel("Subdivided Area", JLabel.CENTER);
            LandArea afterSubdivsion = new LandArea(subdivision.calculate(), subdivision.width, preDivision.height);
            JLabel postCostLabel = new JLabel("$" + Integer.toString(subdivision.getPrice()), JLabel.CENTER);
            
            JPanel postDivisionPanel = new JPanel(new BorderLayout(10,10));
            postDivisionPanel.add(postNameLabel, BorderLayout.NORTH);
            postDivisionPanel.add(afterSubdivsion, BorderLayout.CENTER);
            postDivisionPanel.add(postCostLabel, BorderLayout.SOUTH);
            
            //Combine land areas into one panel
            JPanel landAreas = new JPanel();
            landAreas.add(preDivisionPanel, BorderLayout.WEST);
            landAreas.add(postDivisionPanel, BorderLayout.EAST);
            
            //Land information
            JLabel infoLabel = new JLabel("Each colour is a different subdivision", JLabel.CENTER);
            
            //New area button
            JButton newAreaButton = new JButton("New Area");
            newAreaButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gui.changePanel(new LandCreatorPanel(gui));
                }
            });
            
            //Add elements to panel
            super.add(landAreas, BorderLayout.NORTH);
            super.add(infoLabel, BorderLayout.CENTER);
            super.add(newAreaButton, BorderLayout.SOUTH);
        }
        
        private class LandArea extends JPanel {
            
            private final int width;
            private final int height;
            private int scale;
            private final ArrayList<Land> landList;
            
            public LandArea(Land land) {
                this.landList = new ArrayList<>();
                this.landList.add(land);
                this.width = land.width;
                this.height = land.height;
                setUpPanel();
            }
            
            public LandArea(ArrayList<Land> landList, int width, int height) {
                this.landList = landList;
                this.width = width;
                this.height = height;
                setUpPanel();
            }
            
            private void setUpPanel() {
                this.scale = 95;
                super.setPreferredSize(new Dimension(this.width * this.scale, this.height * this.scale));
                super.setBackground(Color.lightGray);
            }
            
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                //Convert the land into an array format
                Color[][] displayArray  = new Color[this.width][this.height];
                int num = 0;
                for (Land land : this.landList) {
                    Color landColor = getRandomColor();
                    for (int w = 0; w < land.width; w++) {
                        for (int h = 0; h < land.height; h++) {
                            displayArray[land.x + w][land.y + h] = landColor;
                        }
                    }
                    num++;
                }
                
                //Draw land on screen
                for (int x = 0; x < displayArray.length; x++) {
                    for (int y = 0; y < displayArray[0].length; y++) {
                        g.setColor(displayArray[x][y]);
                        g.fillRect(x * this.scale + 2, y * this.scale + 2, this.scale - 4, this.scale - 4);
                    }
                }
            }
            
            //Used to color land
            private Color getRandomColor() {
                Random rand = new Random();
                int r;
                int g;
                int b;
                //Make sure no colours are white to make the screen clearer
                while (true) {
                    r = rand.nextInt(255);
                    g = rand.nextInt(255);
                    b = rand.nextInt(255);
                    if ((r < 180) && (g < 180) && (b < 180)) {
                        return new Color(r, g, b);
                    }
                }
            }
        }
    }
}