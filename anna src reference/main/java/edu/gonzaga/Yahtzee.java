/* (C)2022 */
package edu.gonzaga;

import javax.swing.*;
import edu.gonzaga.Screens.*;
import java.awt.*;

/*
*  This Program lets a user play single player yahtzee using a Graphical User Interface. Game settings are customizable, 
*  allowing the user to pick how many dice are in the game, how many sides the dice have, and how many rolls are allowed per turn
*  CPSC 224, Spring 2022
*  Homework 4
*  Sources: Yahtzee written by Aaron Crandall
*           https://whaa.dev/how-to-overwrite-a-file-in-java - how to use FileWriter
* 
*  @author Anna Cardinal
*  @version v1.4 4/10/2022
*/

/** Main program class for launching Yahtzee program. */
public class Yahtzee{

    public static void main(String[] args) throws Exception{
        
        Image image = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\D6S5.png").getImage();

        JFrame gameWindow = new JFrame("Yahtzee");
        gameWindow.setIconImage(image);
        gameWindow.setLayout(null);
        gameWindow.setSize(800, 800);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        IntroScreen introScreen = new IntroScreen(true);
        SettingsScreen settingsScreen = new SettingsScreen(false);
        GameScreen gameScreen = new GameScreen(false);
        introScreen.setSwitchButtons(settingsScreen, gameScreen);
        settingsScreen.setSwitchButtons(introScreen);
        gameScreen.setSwitchButtons(introScreen);
        
        gameWindow.add(introScreen);
        gameWindow.add(settingsScreen);
        gameWindow.add(gameScreen);

        gameWindow.setVisible(true);

    }

}
