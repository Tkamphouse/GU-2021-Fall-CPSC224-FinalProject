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

/** Class customizing the java swing element JRadioButton */
public class GameRadioButton extends JRadioButton{

    private ImageIcon defaultIconImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\DefaultRadioButton.png");
    private ImageIcon rolloverIconImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\RolloverRadioButton.png");
    private ImageIcon pressedIconImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\SelectedRadioButton.png");
    private ImageIcon selectedIconImage = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\SelectedRadioButton.png");
    private Icon defaultIcon;
    private Icon rolloverIcon;
    private Icon pressedIcon;
    private Icon selectedIcon;

    public GameRadioButton(){
        resizeImages(17, 17);
        assignIcons();
        this.setIcon(defaultIcon);
        this.setRolloverIcon(rolloverIcon);
        this.setPressedIcon(pressedIcon);
        this.setSelectedIcon(selectedIcon);
    }

    public void resizeImages(int width, int height){
        //ImageIcon defaultIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\DefaultRadioButton.png"); // load the image to a imageIcon
        Image image = defaultIconImage.getImage(); // transform it 
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        defaultIconImage = new ImageIcon(newimg);  // transform it back

        image = rolloverIconImage.getImage(); // transform it 
        newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        rolloverIconImage = new ImageIcon(newimg);  

        image = pressedIconImage.getImage(); // transform it 
        newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        pressedIconImage = new ImageIcon(newimg);

        image = selectedIconImage.getImage(); // transform it 
        newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        selectedIconImage = new ImageIcon(newimg);
    }

    public void assignIcons(){
        defaultIcon = defaultIconImage;
        rolloverIcon = rolloverIconImage;
        pressedIcon = pressedIconImage;
        selectedIcon = selectedIconImage;
    }
    
}
