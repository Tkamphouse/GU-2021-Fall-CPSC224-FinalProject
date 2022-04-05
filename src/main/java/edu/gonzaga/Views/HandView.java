package edu.gonzaga.Views;

import javax.swing.*;
import java.util.ArrayList;

public class HandView extends JPanel{

    ArrayList<DieView> dice;
    JButton roll;

    public HandView(){
        this.setLayout(null);
        roll = new JButton("Roll");
    }
    
}
