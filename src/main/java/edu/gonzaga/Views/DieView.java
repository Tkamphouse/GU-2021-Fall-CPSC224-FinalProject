package edu.gonzaga.Views;

import javax.swing.*;

public class DieView extends JPanel {

    private static final int IMAGE_XCOORDINATE = 0;
    private static final int IMAGE_YCOORDINATE = 0;
    private static final int IMAGE_WIDTH = 50;
    private static final int IMAGE_HEIGHT = 50;
    private static final int BUTTON_XCOORDINATE = 13;
    private static final int BUTTON_YCOORDINATE = 50;
    private static final int BUTTON_WIDTH = 25;
    private static final int BUTTON_HEIGHT = 25;
    private static final int PANEL_WIDTH = IMAGE_WIDTH;
    private static final int PANEL_HEIGHT = IMAGE_HEIGHT + BUTTON_HEIGHT;
    
    JLabel icon;
    JRadioButton button;
    ImageIcon d6Side1 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S1.png");
    ImageIcon d6Side2 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S2.png");
    ImageIcon d6Side3 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S3.png");
    ImageIcon d6Side4 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S4.png");
    ImageIcon d6Side5 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S5.png");
    ImageIcon d6Side6 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S6.png");
    ImageIcon[] icons = {d6Side1, d6Side2, d6Side3, d6Side4, d6Side5, d6Side6};

    public DieView(){
        this.setLayout(null);
        icon = new JLabel(icons[6]);
        button = new JRadioButton();

//        if(numSides == 6){
//            dieImage = new JLabel(sixSidedDiceSides[sideUp - 1]);
//        }

    }

    public void update(int currentSideUp){
        icon.setIcon(icons[currentSideUp - 1]);
    }

}
