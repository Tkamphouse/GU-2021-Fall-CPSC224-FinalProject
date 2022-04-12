/* (C)2022 */
package edu.gonzaga;

import javax.swing.*;
import edu.gonzaga.GameComponents.GameButton;
import edu.gonzaga.GameComponents.GameLabel;
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

/** Class for storing dice and handling collective die actions */
public class Hand{

    private static final Color backgroundColor = new Color(228, 216, 200);
    private static final int DEFAULT_COMPONENT_LENGTH = 30;
    private static final int ROLL_LABEL_X_COORDINATE = 0;
    private static final int ROLL_LABEL_Y_COORDINATE = 0;
    private static final int ROLL_LABEL_WIDTH = 160;
    private static final int EXPLANATION_ROLLING_X_COORDINATE = 0;
    private static final int EXPLANATION_ROLLING_Y_COORDINATE = 50;
    private static final int EXPLANATION_ROLLING_WIDTH = 160;
    private static final int ROLL_BUTTON_X_COORDINATE = 20;
    private static final int ROLL_BUTTON_WIDTH = 120;

    private JPanel toPresent;
    private GameLabel numRollsLabel;
    private GameLabel rollingExplanationLabel;
    private Die[] dice;
    private JPanel[] dicePanels;
    private GameButton rollButton;
    private int numDice;
    private int totalLength;

    public Hand(int numDice, int typeDice, int length){
        this.numDice = numDice;
        this.totalLength = length;
        dice = new Die[numDice];
        for(int i = 0; i < numDice; i++){
            dice[i] = new Die(typeDice);
        }
        configureView();
    }

    public Hand(Die[] inputDice, int length){
        this.numDice = inputDice.length;
        this.totalLength = length;
        dice = inputDice;
        configureView();
    }

    /** Sets up the visual information for the hand*/
    private void configureView(){
        numRollsLabel = new GameLabel("", SwingConstants.CENTER);
        numRollsLabel.setTextSize(20);
        rollingExplanationLabel = new GameLabel("Select Dice to Keep", SwingConstants.CENTER);
        rollingExplanationLabel.setTextSize(20);
        rollingExplanationLabel.setVisible(false);
        dicePanels = new JPanel[numDice];
        rollButton = new GameButton("Roll", dice[0].getNumSides());
        setComponentLocations();
        configurePanel();
    }

    /** sets the component locations*/
    private void setComponentLocations(){
        numRollsLabel.setBounds(ROLL_LABEL_X_COORDINATE, ROLL_LABEL_Y_COORDINATE, ROLL_LABEL_WIDTH, DEFAULT_COMPONENT_LENGTH);
        rollingExplanationLabel.setBounds(EXPLANATION_ROLLING_X_COORDINATE, EXPLANATION_ROLLING_Y_COORDINATE, EXPLANATION_ROLLING_WIDTH, DEFAULT_COMPONENT_LENGTH);
        rollButton.setBounds(ROLL_BUTTON_X_COORDINATE, totalLength - 30, ROLL_BUTTON_WIDTH, DEFAULT_COMPONENT_LENGTH);
        setDiceLocations(dice[0].getNumSides());
    }

    /** sets the dice locations*/
    public void setDiceLocations(int typeDice){
        int rowNum = 0;
        for(int i = 0; i < numDice; i++){
            dicePanels[i] = dice[i].getAppearance();
            if(i % 2 == 0){
                dicePanels[i].setLocation(20 + 0, 120 + (rowNum * 75));
            } else{
                dicePanels[i].setLocation(20 + 70, 120 + (rowNum * 75));
                rowNum++;
            }
        }
    }

    /** adds visual components to the JPanel*/
    public void configurePanel(){
        toPresent = new JPanel();
        toPresent.setLayout(null);
        toPresent.setBackground(backgroundColor);
        toPresent.setSize(160, totalLength);
        toPresent.add(numRollsLabel);
        toPresent.add(rollingExplanationLabel);
        for(int i = 0; i < numDice; i++){
            toPresent.add(dicePanels[i]);
        }
        toPresent.add(rollButton);
    }

    /**
    * Updated roll label text to display number of rolls taken and number of rolls left
    *
    * @param rollsPerTurn int value representing the number of rolls per turm
    * @param rollCount int value represent the number of rolls the player has made that turn
    */
    public void setRollLabel(int rollsPerTurn, int rollCount){
        if((rollsPerTurn - rollCount) == 1){
            numRollsLabel.setText("Roll " + Integer.toString(rollCount) + " (" + Integer.toString(rollsPerTurn - rollCount) + " Roll Left)");
        }else{
            numRollsLabel.setText("Roll " + Integer.toString(rollCount) + " (" + Integer.toString(rollsPerTurn - rollCount) + " Rolls Left)");
        }
    }

    /** sets the explanation label to "Final Hand"*/
    public void setExplanationLabelScoringMode(){
        rollingExplanationLabel.setText("Final Hand");
    }

    /** resets the explanation label to "Select Dice to Keep"*/
    public void resetExplanationLabelText(){
        rollingExplanationLabel.setText("Select Dice to Keep");
        rollingExplanationLabel.setVisible(true);
    }

    /** hides the die radio buttons that allow the player to select die to keep*/
    public void hideDiceButtons(){
        for(int i = 0; i < dice.length; i++){
            dice[i].getDieButton().setVisible(false);
            dice[i].getDieButton().setSelected(false);
        }
    }

    /** sets the die radio button to visible*/
    public void revealDiceButtons(){
        for(int i = 0; i < dice.length; i++){
            dice[i].getDieButton().setVisible(true);
        }
    }

    /** hides the Rolling Explanation label*/
    public void hideRollingExplanation(){
        rollingExplanationLabel.setVisible(false);
    }

    /** sets the Rolling Explanation label to visible*/
    public void revealRollingExplanation(){
        rollingExplanationLabel.setVisible(true);
    }

    /**
    * Calculates the total sum of a hand
    *
    * @return int value representing the total sum of the hand
    */
    public int calcHandSum(){
        int points = 0;
        for(int i = 0; i < numDice; i++){ //add all dice values up
            points += dice[i].getSideUp();
        }
        return points;
    }

    /**
    * return the numRollLabel
    *
    * @return JLabel value representing the numRollLabel
    */
    public JLabel getRollLabel(){
        return numRollsLabel;
    }

    /**
    * return the rollButton
    *
    * @return JButton value corresponding the rollButton
    */
    public JButton getRollButton(){
        return rollButton;
    }

    /**
    * return the array of dice
    *
    * @return Die[] value representing the dice array
    */
    public Die[] getDice(){
        return dice;
    }

    /**
    * Returns a specific die at the given index
    *
    * @param index int value representing the desired index of dice
    * @return Die value at a given index of dice
    */
    public Die getDie(int index){
        return dice[index];
    }

    /**
    * Returns the number of dice in the hand
    *
    * @return int value representing the number of dice in the hand
    */
    public int getSize(){
        return numDice;
    }

    /**
    * returns the JPanel containing all visual components
    *
    * @return JPanel value that contains visual components
    */
    public JPanel getAppearance(){
        return toPresent;
    }

}
