/* (C)2022 */
package edu.gonzaga.GameComponents;

import javax.swing.*;

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

/** Class customizing the java swing element JRadioButton */
public class GameRadioButton extends JRadioButton{

    private Icon defaultIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\RadioButtonDefaultIcon.png");
    private Icon rolloverIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\RadioButtonRolloverIcon.png");
    private Icon pressedIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\RadioButtonPressedIcon.png");
    private Icon selectedIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\RadioButtonSelectedIcon.png");

    public GameRadioButton(){
        this.setIcon(defaultIcon);
        this.setRolloverIcon(rolloverIcon);
        this.setPressedIcon(pressedIcon);
        this.setSelectedIcon(selectedIcon);
    }
    
}
