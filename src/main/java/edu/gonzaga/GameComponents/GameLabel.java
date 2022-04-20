/* (C)2022 */
package edu.gonzaga.GameComponents;

import javax.swing.*;
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

/** Class Customizing the java swing component JLabel */
public class GameLabel extends JLabel{

    private static final Color darkBrown = new Color(27, 25, 21);

    public GameLabel(){
        super();
        this.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
        this.setForeground(darkBrown);
    }

    public GameLabel(String text){
        super(text);
        this.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
        this.setForeground(darkBrown);
    }

    public GameLabel(String text, boolean bold){
        super(text);
        if(bold){
            this.setFont(new Font("Baskerville Old Face", Font.BOLD, 15));
        }else{
            this.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
        }
        this.setForeground(darkBrown);
    }

    public GameLabel(String text, int horizontalAlignment){
        super(text, horizontalAlignment);
        this.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
        this.setForeground(darkBrown);
    }

    /**
    * sets the size of the text in the Game Label
    *
    * @param textSize int value representing the new text size
    */
    public void setTextSize(int textSize){
        this.setFont(new Font("Baskerville Old Face", Font.PLAIN, textSize));
    }
}
