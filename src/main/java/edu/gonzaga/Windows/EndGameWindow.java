package edu.gonzaga.Windows;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import edu.gonzaga.*;
import edu.gonzaga.ScoreCard.*;
import java.awt.event.*;

/**
 * Class to make a final window with results of the game.
 * 
 * @author Jonathan Smoley
 * @version 2.0 4/24/2022
 */
public class EndGameWindow extends JPanel{

    LeaderBoard leaderBoard;
    JLabel winner;
    JButton toTitleScreen;
    ArrayList<Player> players;

    /**
     * Default constructor for the EndGameWindow class.
     * 
     * @param players arraylist of players after the game
     * @see GameWindow.java
     */
    public EndGameWindow(ArrayList<Player> players){
        this.players = players;
        this.setLayout(null);
        this.setSize(800, 800);

        leaderBoard = new LeaderBoard(players);
        add(leaderBoard.getLeaderBoardView(), BorderLayout.CENTER);

        toTitleScreen = new JButton("Return to Title");
        // add(toTitleScreen, BorderLayout.SOUTH);
    }

    /**
     * Method to set the behavior of a button to return to the title screen.
     * 
     * @param titleWindow reference to the title window
     */
    public void setSwitchButton(JPanel titleWindow){
        toTitleScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                titleWindow.setVisible(true);
                setVisible(false);
            }
        });
    }

    // getters for unit testing purposes
    public LeaderBoard getLeaderBoard(){ return leaderBoard; }
    public JButton getToTitleScreen(){ return toTitleScreen; }
    
}
