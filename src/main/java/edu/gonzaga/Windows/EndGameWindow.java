package edu.gonzaga.Windows;

import java.util.ArrayList;

import javax.swing.*;
import edu.gonzaga.*;
import edu.gonzaga.ScoreCard.*;
import java.awt.event.*;

public class EndGameWindow extends JPanel{

    LeaderBoard leaderBoard;
    JLabel winner;
    JButton toTitleScreen;
    ArrayList<Player> players;

    public EndGameWindow(){
        this.setLayout(null);
        toTitleScreen = new JButton("Return to Title");
    }

    //to be set after all screens created in Yahtzee
    public void setSwitchButton(JPanel titleWindow){
        toTitleScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                titleWindow.setVisible(true);
                setVisible(false);
            }
        });
    }
    
}
