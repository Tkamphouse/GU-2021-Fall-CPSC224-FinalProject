package edu.gonzaga.Views;

import javax.swing.*;

public class DieView extends JPanel{
    
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
        icon = new JLabel(icons[0]);
        button = new JRadioButton();
    }

    public void update(int currentSideUp){
        icon.setIcon(icons[currentSideUp - 1]);
    }

}
