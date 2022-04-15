
package edu.gonzaga;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/*
*  This is the player's hand class for the Yahtzee project.
*  It is basially an arrayList of different die.roll()s
*   has most of the methods Yahtzee.java calls in main
*/
public class Hand
{
    private static final int NUM_DICE = 5;

    /*
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
    */

    //private JPanel toPresent;
    //private GameLabel numRollsLabel;
    //private GameLabel rollingExplanationLabel;
    private Die[] hand;
    //private JPanel[] dicePanels;
    //private GameButton rollButton;
    private int length = NUM_DICE;
    private int numDice = NUM_DICE;


    public Hand()
    {
        hand = new Die[NUM_DICE];
        for(int i = 0; i < NUM_DICE; i++){
            hand[i] = new Die();
        }
        // IMLEMENT WHEN VIEWS START BEING DONE
        //configureView();
    }

    public Die[] returnHand()
    {
        return hand;
    }

    public int ruturnValue(int index)
    {
        int value = 0;
        if (index < numDice && index >= 0)
            value = hand[index].getSideUp();
        return value;
    }

    public int[] returnAllValues()
    {
        int[] values = new int[NUM_DICE];
        for(int i = 0; i < NUM_DICE; i++){
            values[i] = hand[i].getSideUp();
        }
        return values;
    }

    public void reset() 
    {
        for (int i = 0; i < this.numDice; ++i)
        {
            hand[i].setValue(1);
        }
    }

    public void RollNewHand() 
    {
        for (int i = 0; i < this.numDice; ++i)
        {
            if (!hand[i].islocked())
                hand[i].roll();
        }
    }

    public int sum()
    {
        int sum = 0;
        for(int i = 0; i < numDice; i++) 
            sum += hand[i].getSideUp();
        return sum;
    }

    public Die[] getDice()
    {
        return hand;
    }

    public Die getDie(int index)
    {
        return hand[index];
    }

    public int getSize() 
    {
        return numDice;
    }

    public String toString() 
    {
        String ret = "";
        ret += "Hand: ";
        for (Die die : hand)
            ret += die.toString() + " | ";
        return ret;
    }

    // SCORING STUFF
    // Totals the number of die with the same number in hand
    // Sums the amount of die that have the same number
    // Returns an int of the largest number of dice with the same number rolled
    public int maxOfAKindFound()
    {
        int maxCount = 0;
        int currentCount = 0;
        for (int dieValue = 1; dieValue <= hand[0].numSides(); dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < numDice; diePosition++)
            {
                if (hand[diePosition].getSideUp() == dieValue)
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
            }
        return maxCount;
    }

    // Totals the largest stright found in hand
    // Sums the the largest stright (subsequent numbers) in the hand
    // Returns an int of the largest straight
    public int maxStraightFound()
    {
        int STRAIGHT_LENGTH = 4;
        int maxLength = 1;
        int curLength = 1;
        for(int counter = 0; counter < STRAIGHT_LENGTH || counter + 1 > numDice; counter++)
        {
            if (hand[counter].getSideUp() + 1 == hand[counter + 1].getSideUp()) //jump of 1
                curLength++;
            else if (hand[counter].getSideUp() + 1 < hand[counter + 1].getSideUp()) //jump of >= 2
                curLength = 1;
            if (curLength > maxLength)
                maxLength = curLength;
        }
        return maxLength;
    }

    // Checks if a full house is in the hand
    // Sums the dice to check if there are 3 of one number and 2 of another
    // Returns true if a full house is found, false otherwise
    public boolean fullHouseFound()
    {
        boolean foundFH = false;
        boolean found3K = false;
        boolean found2K = false;
        int currentCount;
        for (int dieValue = 1; dieValue <= hand[0].numSides(); dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < numDice - 1; diePosition++)
            {
                if (hand[diePosition].getSideUp() == dieValue)
                    currentCount++;
            }
            if (currentCount == 2)
                found2K = true;
            if (currentCount == 3)
                found3K = true;
        }
        if (found2K && found3K)
            foundFH = true;
        return foundFH;
    }

    // Finds the number of Die showing the same number
    // Takes in a counter and a Die value to check how many die in hand are showing that value
    // Returns an int that sums the amount of die with said value
    public int numOfDieValue(int dieValue, int currentCount)
    {
        for (int diePosition = 0; diePosition < numDice; diePosition++)
        {
            if (hand[diePosition].getSideUp() == dieValue)
                currentCount++;
        }
        return currentCount;
    }


    // APPEARENCE AND DISPLAY STUFF
    /*

    public JPanel getAppearance(){
        return toPresent;
    }

    // Sets up the visual information for the hand
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

    // sets the component locations
    private void setComponentLocations(){
        numRollsLabel.setBounds(ROLL_LABEL_X_COORDINATE, ROLL_LABEL_Y_COORDINATE, ROLL_LABEL_WIDTH, DEFAULT_COMPONENT_LENGTH);
        rollingExplanationLabel.setBounds(EXPLANATION_ROLLING_X_COORDINATE, EXPLANATION_ROLLING_Y_COORDINATE, EXPLANATION_ROLLING_WIDTH, DEFAULT_COMPONENT_LENGTH);
        rollButton.setBounds(ROLL_BUTTON_X_COORDINATE, totalLength - 30, ROLL_BUTTON_WIDTH, DEFAULT_COMPONENT_LENGTH);
        setDiceLocations(dice[0].getNumSides());
    }

    // sets the dice locations
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

    // adds visual components to the JPanel
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

    // Updated roll label text to display number of rolls taken and number of rolls left
    //
    // @param rollsPerTurn int value representing the number of rolls per turm
    // @param rollCount int value represent the number of rolls the player has made that turn
    
    public void setRollLabel(int rollsPerTurn, int rollCount){
        if((rollsPerTurn - rollCount) == 1){
            numRollsLabel.setText("Roll " + Integer.toString(rollCount) + " (" + Integer.toString(rollsPerTurn - rollCount) + " Roll Left)");
        }else{
            numRollsLabel.setText("Roll " + Integer.toString(rollCount) + " (" + Integer.toString(rollsPerTurn - rollCount) + " Rolls Left)");
        }
    }

    // sets the explanation label to "Final Hand"
    public void setExplanationLabelScoringMode(){
        rollingExplanationLabel.setText("Final Hand");
    }

    // resets the explanation label to "Select Dice to Keep"
    public void resetExplanationLabelText(){
        rollingExplanationLabel.setText("Select Dice to Keep");
        rollingExplanationLabel.setVisible(true);
    }

    // hides the die radio buttons that allow the player to select die to keep
    public void hideDiceButtons(){
        for(int i = 0; i < dice.length; i++)
        {
            dice[i].getDieButton().setVisible(false);
            dice[i].getDieButton().setSelected(false);
        }
    }

    // sets the die radio button to visible
    public void revealDiceButtons(){
        for(int i = 0; i < hand.length; i++)
            hand[i].getDieButton().setVisible(true);
    }

    // hides the Rolling Explanation label
    public void hideRollingExplanation(){
        rollingExplanationLabel.setVisible(false);
    }

    // sets the Rolling Explanation label to visible
    public void revealRollingExplanation(){
        rollingExplanationLabel.setVisible(true);
    }
    */
}
