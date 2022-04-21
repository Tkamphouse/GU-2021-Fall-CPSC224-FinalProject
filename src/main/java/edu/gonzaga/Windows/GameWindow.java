package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.event.*;
import edu.gonzaga.*;
import edu.gonzaga.ScoreCard.*;
import java.util.ArrayList;

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

    public void initPlayers(ArrayList<JTextField> nameCollectors){
        players = new ArrayList<>();
        for (int i = 0; i < nameCollectors.size(); i++){
            Player newPlayer = new Player(nameCollectors.get(i).getText());
            players.add(newPlayer);
        }
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
