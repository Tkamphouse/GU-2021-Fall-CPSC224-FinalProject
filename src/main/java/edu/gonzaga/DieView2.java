package edu.gonzaga;

import javax.swing.*;

public class DieView2 extends JPanel {

    private static int IMAGE_XCOORDINATE = 0;
    private static int IMAGE_YCOORDINATE = 0;
    private static int IMAGE_WIDTH = 50;
    private static int IMAGE_HEIGHT = 50;
    private static int BUTTON_XCOORDINATE = 13;
    private static int BUTTON_YCOORDINATE = 50;
    private static int BUTTON_WIDTH = 25;
    private static int BUTTON_HEIGHT = 25;
    private static final int PANEL_WIDTH = IMAGE_WIDTH;
    private static final int PANEL_HEIGHT = IMAGE_HEIGHT + BUTTON_HEIGHT;

    //GETTERS
    public static int getImageXcoordinate() { return IMAGE_XCOORDINATE; }
    public static int getImageYcoordinate() { return IMAGE_YCOORDINATE; }
    public static int getImageWidth() { return IMAGE_WIDTH; }
    public static int getImageHeight() { return IMAGE_HEIGHT; }
    public static int getButtonXcoordinate() {return BUTTON_XCOORDINATE; }
    public static int getButtonYcoordinate() { return BUTTON_YCOORDINATE; }
    public static int getButtonWidth() { return BUTTON_WIDTH; }
    public static int getButtonHeight() { return BUTTON_HEIGHT; }
    //SETTERS
    public void setImageXcoordinate(int imgXCoordinate) { IMAGE_XCOORDINATE = imgXCoordinate; }
    public void setImageYcoordinate(int imgYCoordinate) { IMAGE_XCOORDINATE = imgYCoordinate; }
    public void setImageWidth(int imgWidth) { IMAGE_WIDTH = imgWidth; }
    public void setImageHeight(int imgHeight) { IMAGE_HEIGHT = imgHeight; }
    public void setButtonXcoordinate(int buttonXcoordinate) { BUTTON_XCOORDINATE = buttonXcoordinate; }
    public void setButtonYcoordinate(int buttonYcoordinate) { BUTTON_YCOORDINATE = buttonYcoordinate; }
    public void setButtonWidth(int buttonWidth) { BUTTON_WIDTH = buttonWidth; }
    public void setButtonHeight(int buttonHeight) { BUTTON_HEIGHT = buttonHeight; }

    Die die = new Die();

    JLabel icon;
    JRadioButton button;
    ImageIcon d6Side1 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S1.png");
    ImageIcon d6Side2 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S2.png");
    ImageIcon d6Side3 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S3.png");
    ImageIcon d6Side4 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S4.png");
    ImageIcon d6Side5 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S5.png");
    ImageIcon d6Side6 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S6.png");
    ImageIcon[] icons = {d6Side1, d6Side2, d6Side3, d6Side4, d6Side5, d6Side6};

    public DieView2(){
        this.setLayout(null);
        icon = new JLabel(icons[6]);
    }

    public void updateDieAppearance(int sideUp) {
        if (Die.getNumSides() == 6) {
            die.setSideUp(sideUp);
            icon.setIcon(icons[sideUp - 1]);
        }
    }

    public void configureView(){
        int sideUp = die.getSideUp();

        if(die.getNumSides() == 6) {
            icon = new JLabel(sideUp - 1 + "");
        }
        icon.setBounds(IMAGE_XCOORDINATE, IMAGE_YCOORDINATE, IMAGE_WIDTH, IMAGE_HEIGHT);
        icon.setBounds(BUTTON_XCOORDINATE, BUTTON_YCOORDINATE, BUTTON_WIDTH, BUTTON_HEIGHT);
        icon.setOpaque(false);
        icon.setLayout(null);
        icon.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        icon.setOpaque(false);
        icon.add(icon);
    }
}