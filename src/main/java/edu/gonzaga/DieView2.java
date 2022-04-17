package edu.gonzaga;

import javax.swing.*;

public class DieView2 extends JPanel {

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

    Die die = new Die();

    JLabel icon;
    JRadioButton button;
    JRadioButton button1, button2, button3, button4, button5, button6;
    ImageIcon d6Side1 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S1.png");
    ImageIcon d6Side2 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S2.png");
    ImageIcon d6Side3 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S3.png");
    ImageIcon d6Side4 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S4.png");
    ImageIcon d6Side5 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S5.png");
    ImageIcon d6Side6 = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S6.png");
    ImageIcon[] icons = {d6Side1, d6Side2, d6Side3, d6Side4, d6Side5, d6Side6};
    JRadioButton[] buttons = {button1, button2, button3, button4, button5, button6};
    //Die[][] dice = new Die[0][0];

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
        icon.add(buttons[0]);
        icon.add(buttons[1]);
        icon.add(buttons[2]);
        icon.add(buttons[3]);
        icon.add(buttons[4]);
        icon.add(buttons[5]);

    }
}