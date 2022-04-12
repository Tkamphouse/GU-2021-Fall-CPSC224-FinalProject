/* (C)2022 */
package edu.gonzaga;

import javax.swing.*;
import edu.gonzaga.GameComponents.GameRadioButton;
import java.util.Random;

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

/** Class for creating a rollable die with a visual representation and a selection button */
public class Die implements Comparable<Die>{

    private final int DEFAULT_NUM_SIDES = 6;
    private final int DEFAULT_SIDE_UP = 1;
    private final int IMAGE_XCOORDINATE = 0;
    private final int IMAGE_YCOORDINATE = 0;
    private final int IMAGE_WIDTH = 50;
    private final int IMAGE_HEIGHT = 50;
    private final int BUTTON_XCOORDINATE = 13;
    private final int BUTTON_YCOORDINATE = 50;
    private final int BUTTON_WIDTH = 25;
    private final int BUTTON_HEIGHT = 25;
    private final int PANEL_WIDTH = IMAGE_WIDTH;
    private final int PANEL_HEIGHT = IMAGE_HEIGHT + BUTTON_HEIGHT;

    private JPanel toPresent = new JPanel();
    private JLabel dieImage;
    private GameRadioButton dieButton = new GameRadioButton();
    private Integer sideUp;
    private Integer numSides;
    private String directory = System.getProperty("user.dir") + "\\src\\media\\";
    private ImageIcon D6Side1 = new ImageIcon(directory + "D6S1.png");
    private ImageIcon D6Side2 = new ImageIcon(directory + "D6S2.png");
    private ImageIcon D6Side3 = new ImageIcon(directory + "D6S3.png");
    private ImageIcon D6Side4 = new ImageIcon(directory + "D6S4.png");
    private ImageIcon D6Side5 = new ImageIcon(directory + "D6S5.png");
    private ImageIcon D6Side6 = new ImageIcon(directory + "D6S6.png");
    private ImageIcon[] sixSidedDiceSides = {D6Side1, D6Side2, D6Side3, D6Side4, D6Side5, D6Side6};
    private ImageIcon D8Side1 = new ImageIcon(directory + "D8S1.png");
    private ImageIcon D8Side2 = new ImageIcon(directory + "D8S2.png");
    private ImageIcon D8Side3 = new ImageIcon(directory + "D8S3.png");
    private ImageIcon D8Side4 = new ImageIcon(directory + "D8S4.png");
    private ImageIcon D8Side5 = new ImageIcon(directory + "D8S5.png");
    private ImageIcon D8Side6 = new ImageIcon(directory + "D8S6.png");
    private ImageIcon D8Side7 = new ImageIcon(directory + "D8S7.png");
    private ImageIcon D8Side8 = new ImageIcon(directory + "D8S8.png");
    private ImageIcon[] eightSidedDiceSides = {D8Side1, D8Side2, D8Side3, D8Side4, D8Side5, D8Side6, D8Side7, D8Side8};
    private ImageIcon D12Side1 = new ImageIcon(directory + "D12S1.png");
    private ImageIcon D12Side2 = new ImageIcon(directory + "D12S2.png");
    private ImageIcon D12Side3 = new ImageIcon(directory + "D12S3.png");
    private ImageIcon D12Side4 = new ImageIcon(directory + "D12S4.png");
    private ImageIcon D12Side5 = new ImageIcon(directory + "D12S5.png");
    private ImageIcon D12Side6 = new ImageIcon(directory + "D12S6.png");
    private ImageIcon D12Side7 = new ImageIcon(directory + "D12S7.png");
    private ImageIcon D12Side8 = new ImageIcon(directory + "D12S8.png");
    private ImageIcon D12Side9 = new ImageIcon(directory + "D12S9.png");
    private ImageIcon D12Side10 = new ImageIcon(directory + "D12S10.png");
    private ImageIcon D12Side11 = new ImageIcon(directory + "D12S11.png");
    private ImageIcon D12Side12 = new ImageIcon(directory + "D12S12.png");
    private ImageIcon[] twelveSidedDiceSides = {D12Side1, D12Side2, D12Side3, D12Side4, D12Side5, D12Side6, 
                                        D12Side7, D12Side8, D12Side9, D12Side10, D12Side11, D12Side12};

    public Die(){
        numSides = DEFAULT_NUM_SIDES;
        sideUp = DEFAULT_SIDE_UP;
        configureView();
    } 

    public Die(int numSides){
        this.numSides = numSides;
        this.sideUp = DEFAULT_SIDE_UP;
        configureView();
    }

    public Die(int numSides, int numSideUp){
        this.numSides = numSides;
        this.sideUp = numSideUp;
        configureView();
    }

    /** sets up view settings*/
    private void configureView(){
        if(numSides == 6){
            dieImage = new JLabel(sixSidedDiceSides[sideUp - 1]);
        }else if(numSides == 8){
            dieImage = new JLabel(eightSidedDiceSides[sideUp - 1]);
        }else{
            dieImage = new JLabel(twelveSidedDiceSides[sideUp - 1]);
        }
        dieImage.setBounds(IMAGE_XCOORDINATE, IMAGE_YCOORDINATE, IMAGE_WIDTH, IMAGE_HEIGHT);
        dieButton.setBounds(BUTTON_XCOORDINATE, BUTTON_YCOORDINATE, BUTTON_WIDTH, BUTTON_HEIGHT);
        dieButton.setOpaque(false);
        toPresent.setLayout(null);
        toPresent.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        toPresent.setOpaque(false);
        toPresent.add(dieImage);
        toPresent.add(dieButton);
    }

    /** rolls the die if the radio button is not selected*/
    public void roll(){
        if(!dieButton.isSelected()){
            Random rand = new Random();
            this.sideUp = rand.nextInt(this.numSides) + 1;
            updateDieAppearance();
        }
        updateDieAppearance();
    }

    /** updates die appearance based on current side up value*/
    public void updateDieAppearance(){
        if(numSides == 6){
            dieImage.setIcon(sixSidedDiceSides[sideUp - 1]);
        }else if(numSides == 8){
            dieImage.setIcon(eightSidedDiceSides[sideUp - 1]);
        }else{
            dieImage.setIcon(twelveSidedDiceSides[sideUp - 1]);
        }
    }

    /**
    * Returns the die button
    *
    * @return JRadioButton representing the die button
    */
    public JRadioButton getDieButton(){
        return dieButton;
    }

    /**
    * Returns the number of sides on the die
    *
    * @return int value correspindng to the number of sides on the die
    */
    public int getNumSides(){
        return numSides;
    }

    /**
    * returns the current side up
    *
    * @return int value representing the current side up
    */
    public int getSideUp(){
        return sideUp;
    }

    /**
    * returns JPanel containing all view components
    *
    * @return JPanel containing view components
    */
    public JPanel getAppearance(){
        return toPresent;
    }

    /**
    * Makes two dice comparable using <, ==, >, etc. based on sideUp values.
    *
    * @param otherDie The die we're comparing to this one (two objects)
    * @return int -1, 0, 1 for less than, equal, greater than
    */
    @Override
    public int compareTo(Die otherDie){
        return this.sideUp.compareTo(otherDie.sideUp);
    }
    
}
