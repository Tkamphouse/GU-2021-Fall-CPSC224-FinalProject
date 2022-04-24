package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
// import java.util.ArrayList;
import edu.gonzaga.Windows.*;

/** Main program class for launching Yahtzee program. 
 * 
 * @author Jonathan Smoley
 * @version 2.0 4/24/2022
*/
public class Yahtzee {
    /**
     * Start point for the Yahtzee program.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Yahtzee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        TitleWindow titleWindow = new TitleWindow();
        frame.add(titleWindow, BorderLayout.CENTER);
        titleWindow.setSwitchButton(frame, titleWindow);
        frame.setVisible(true);

        /*
        JFrame frame = new JFrame("Yahtzee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        TitleWindow titleWindow = new TitleWindow();
        // function to get number of players rquested by user
        NameWindow nameWindow = new NameWindow(numPlayers);
        // function to get and arraylist of player names
        GameWindow gameWindow = new GameWindow(playerNames); // lizard-spock would pass config from settings page
        // function to run through a game of yahtzee with the players
        // function to get the player array stored in game window
        EndGameWindow endGameWindow = new EndGameWindow(players);
        frame.setVisible(true);
        */
    }
}
