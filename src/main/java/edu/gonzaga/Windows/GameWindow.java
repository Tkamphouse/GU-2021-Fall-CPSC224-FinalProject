package edu.gonzaga.Windows;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import edu.gonzaga.*;

public class GameWindow extends JPanel{

    JLabel currentPlayerName;
    JLabel turnLabel;
    JLabel scoringInstructions;
    ArrayList<JRadioButton> scoringButtons;
    JButton toEndScreen;
    JButton exitGame;
    Hand gameHand;
    ScoreCard currentPlayerScoreCard;
    ArrayList<Player> players;

    public GameWindow(){
        this.setLayout(null);
        scoringInstructions = new JLabel("Select line to record score");
        toEndScreen = new JButton("Finish Game");
        exitGame = new JButton("Exit Game");
    }

    //to be set after all screens created in Yahtzee
    public void setSwitchButton(JPanel endGame){
        toEndScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                endGame.setVisible(true);
                setVisible(false);
            }
        });
    }
    
}
