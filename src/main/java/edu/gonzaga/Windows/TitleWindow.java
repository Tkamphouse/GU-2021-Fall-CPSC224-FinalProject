package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.event.*;

public class TitleWindow extends JPanel{
    
    JLabel yahtzeeTitle;
    Integer[] numPlayerOptions = {1, 2, 3, 4, 5, 6, 7, 8};
    JComboBox<Integer> numPlayerDropBox = new JComboBox<>(numPlayerOptions);
    JButton toGameWindow;
    //JButton toSettingsWindow;

    public TitleWindow(){
        this.setLayout(null);
        yahtzeeTitle = new JLabel("Yahtzee");
        toGameWindow = new JButton("Play");
        //toSettingsWindow = new JButton("Settings");
    }

    //to be set after all screens created in Yahtzee
    public void setSwitchButton(JPanel gameWindow){
        toGameWindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                gameWindow.setVisible(true);
                setVisible(false);
            }
        });
    }

}
