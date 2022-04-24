/* (C)2022 */
package edu.gonzaga.GameComponents;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.border.BevelBorder;

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

/** Class customizing java swing component JButton */
public class GameButton extends JButton{

    /*private static final Color darkBrown = new Color(27, 25, 21);
    private static final Color lightRed = new Color(177, 107, 73);
    private static final Color darkRed = new Color(150, 70, 47);
    private static final Color lightGreen = new Color(151, 158, 131);
    private static final Color darkGreen = new Color(100, 110, 86);
    private static final Color lightBlue = new Color(159, 188, 170);
    private static final Color darkBlue = new Color(111, 147, 131);*/

    //FormatSample palette = new FormatSample();
    private Color mainColor = ColorPalette.red;
    private Color clickedColor = ColorPalette.darkRed;

    public GameButton(String text){
        super(text);
        this.setColorSettings();
        this.setFont(new Font("Candara", Font.PLAIN, 20));
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    public GameButton(String text, int gameType){
        super(text);
        this.setColorSettings();
        this.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    /**
    * sets color scheme of Game button based on the game type, or the number of sides on the game dice
    *
    * @param gameType int value representing the number of sides on the dice in the game
    */
    public void setColorSettings(){
        /*if(gameType == 12){
            mainColor = lightBlue;
            clickedColor = darkBlue; 
        } else if(gameType == 8){
            mainColor = lightGreen;
            clickedColor = darkGreen;
        } else{
            mainColor = lightRed;
            clickedColor = darkRed;
        }*/
        this.setBackground(mainColor);
        this.setForeground(ColorPalette.textColor);
        addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent event){
                if (getModel().isPressed()){
                    setBackground(clickedColor);
                    setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                } else if (getModel().isRollover()){
                    setBackground(mainColor);
                    setBorder(BorderFactory.createLineBorder(clickedColor, 2));
                } else{
                    setBackground(mainColor);
                    setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                }
            }
        });
    }

    public void setColors(Color mainColor, Color clickedColor){
        this.mainColor = mainColor;
        this.clickedColor = clickedColor;
        setColorSettings();
    }

    /**
    * Returns the color of the game buttono when clicked
    *
    * @return Color value representing the color of the button when clicked
    */
    public Color getClickedColor(){
        return this.clickedColor;
    }
    
}
