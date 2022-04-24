package edu.gonzaga;

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
    private int sideUp;
    private int numSides;

    JLabel icon;
    JRadioButton button;
    //src\media\D6S1.png
    ImageIcon d6Side1 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S1.png");
    ImageIcon d6Side2 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S2.png");
    ImageIcon d6Side3 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S3.png");
    ImageIcon d6Side4 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S4.png");
    ImageIcon d6Side5 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S5.png");
    ImageIcon d6Side6 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S6.png");
    ImageIcon[] icons = {d6Side1, d6Side2, d6Side3, d6Side4, d6Side5, d6Side6};

    public DieView(int sideUp, int numSides){
        this.setLayout(null);
        this.sideUp = sideUp;
        this.numSides = numSides;
        icon = new JLabel(icons[sideUp - 1]);
        button = new JRadioButton();
        configureView();
    }

    public void updateDieAppearance(int sideUp) {
        //this.sideUp = sideUp;
        icon.setIcon(icons[sideUp - 1]);
    }

    public void configureView(){
        //icon = new JLabel(sideUp - 1 + "");
        icon.setBounds(IMAGE_XCOORDINATE, IMAGE_YCOORDINATE, IMAGE_WIDTH, IMAGE_HEIGHT);
        button.setBounds(BUTTON_XCOORDINATE, BUTTON_YCOORDINATE, BUTTON_WIDTH, BUTTON_HEIGHT);
        icon.setOpaque(false);
        //icon.setLayout(null);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        this.setOpaque(false);
        //icon.add(icon);
        this.add(icon);
        this.add(button);
    }

    public JRadioButton getRadioButton() {
        return button;
    }


}