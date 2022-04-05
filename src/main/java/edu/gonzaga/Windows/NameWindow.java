package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NameWindow extends JPanel{
    
    JLabel intructions;
    ArrayList<JLabel> playerNumLabels;
    ArrayList<JTextField> playerNameCollectors;
    JButton toGameWindow;
    int numPlayers;

    public NameWindow(){
        this.setLayout(null);
        intructions = new JLabel("Enter Player Names");
        toGameWindow = new JButton("Start Game");
    }

    //to be set after title screen gets numPlayer info
    public void setNumPlayers(int numPlayers){
        this.numPlayers = numPlayers;
        for(int i = 0; i < numPlayers; i++){
            JLabel tempJLabel = new JLabel("Player " + (i + 1) + ": ");
            JTextField tempTextField = new JTextField();
            playerNumLabels.add(tempJLabel);
            playerNameCollectors.add(tempTextField);
        }
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
