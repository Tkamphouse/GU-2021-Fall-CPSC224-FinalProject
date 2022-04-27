
package edu.gonzaga;

import edu.gonzaga.GameComponents.GameButton;
import edu.gonzaga.GameComponents.GameLabel;
import edu.gonzaga.GameComponents.GameRadioButton;

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

    private static final int DEFAULT_COMPONENT_LENGTH = 30;
    private static final int ROLL_LABEL_X_COORDINATE = 0;
    private static final int ROLL_LABEL_Y_COORDINATE = 0;
    private static final int ROLL_LABEL_WIDTH = 160;
    private static final int EXPLANATION_ROLLING_X_COORDINATE = 0;
    private static final int EXPLANATION_ROLLING_Y_COORDINATE = 50;
    private static final int EXPLANATION_ROLLING_WIDTH = 160;
    private static final int ROLL_BUTTON_X_COORDINATE = 20;
    private static final int ROLL_BUTTON_WIDTH = 120;

    private Die[] dice;
    private int length = 550;
    private int numDice = NUM_DICE;
    private int rollCount;

    //handViewProperties
    private JPanel[] dicePanels;
    private JPanel curPanel;
    private GameLabel numRollsLabel;
    private GameLabel rollTextLabel;
    private GameButton rollButton;

    public Hand()
    {
        rollCount = 0;
        dice = new Die[NUM_DICE];
        for(int i = 0; i < NUM_DICE; i++){
            dice[i] = new Die();
        }
        configureView();
    }

    public Die[] returnHand()
    {
        return dice;
    }

    public int returnValue(int index)
    {
        int value = 0;
        if (index < numDice && index >= 0)
            value = dice[index].getSideUp();
        return value;
    }

    public int[] returnAllValues()
    {
        int[] values = new int[NUM_DICE];
        for(int i = 0; i < NUM_DICE; i++)
            values[i] = dice[i].getSideUp();
        return values;
    }

    public int rollCount()
    {
        return rollCount;
    }

    public void reset() 
    {
        rollCount = 0;
        setRollLabel(3, rollCount);
        for (int i = 0; i < this.numDice; ++i){
            dice[i].setValue(1);
            dice[i].deselectButton();
        }
        revealRollButton();
        //hideDiceButtons();
    }

    public void RollNewHand() 
    {
        rollCount++;
        setRollLabel(3, rollCount);
        //if (rollCount < 3)
        //{
        for (int i = 0; i < this.numDice; ++i)
        {
            dice[i].roll();
        }
        //rollCount++;
        //}
        if (rollCount == 1)
            revealDiceButtons();
        if (rollCount >= 3)
        {
            hideDiceButtons();
            hideRollButton();
        }
    }

    public Die[] getDice()
    {
        return dice;
    }

    public Die getDie(int index)
    {
        return dice[index];
    }

    public int getSize() 
    {
        return numDice;
    }

    public boolean isTurnOver()
    {
        if (rollCount >= 3)
            return true;
        else
            return false;
    }

    public String toString() 
    {
        String ret = "";
        ret += "Hand: ";
        for (Die die : dice)
            ret += die.toString() + " | ";
        return ret;
    }

    public int sum()
    {
        int sum = 0;
        for(int i = 0; i < numDice; i++) 
            sum += dice[i].getSideUp();
        return sum;
    }

    // ***** HandView stuff ***** //

    // Sets up the visual information for the hand
    private void configureView()
    {
        numRollsLabel = new GameLabel("Not Assigned", SwingConstants.CENTER);
        setRollLabel(3, rollCount);
        numRollsLabel.setTextSize(20);
        rollTextLabel = new GameLabel("Select Dice to Keep", SwingConstants.CENTER);
        rollTextLabel.setTextSize(20);
        rollTextLabel.setVisible(false);
        dicePanels = new JPanel[numDice];
        rollButton = new GameButton("Roll", dice[0].getNumSides());
        setComponentLocations();
        configurePanel();
    }

    // sets the component locations
    private void setComponentLocations()
    {
        numRollsLabel.setBounds(ROLL_LABEL_X_COORDINATE, ROLL_LABEL_Y_COORDINATE, ROLL_LABEL_WIDTH, DEFAULT_COMPONENT_LENGTH);
        rollTextLabel.setBounds(EXPLANATION_ROLLING_X_COORDINATE, EXPLANATION_ROLLING_Y_COORDINATE, EXPLANATION_ROLLING_WIDTH, DEFAULT_COMPONENT_LENGTH);
        rollButton.setBounds(ROLL_BUTTON_X_COORDINATE, length - 30, ROLL_BUTTON_WIDTH, DEFAULT_COMPONENT_LENGTH);
        setDiceLocations(dice[0].getNumSides());
    }

    // sets the dice locations
    public void setDiceLocations(int typeDice)
    {
        int rowNum = 0;
        for(int i = 0; i < numDice; i++){
            dicePanels[i] = dice[i].getView(); //NEEDS TO BE CHECK FOR COMBATIBILITY WITH DIE()
            if (i % 2 == 0)
                dicePanels[i].setLocation(20 + 0, 120 + (rowNum * 75));
            else
            {
                dicePanels[i].setLocation(20 + 70, 120 + (rowNum * 75));
                rowNum++;
            }
        }
    }

    // adds visual components to the JPanel
    public void configurePanel()
    {
        curPanel = new JPanel();
        curPanel.setLayout(null);
        curPanel.setOpaque(false);
        curPanel.setSize(160, length);
        numRollsLabel.setSize(150, 75);
        curPanel.add(numRollsLabel);
        curPanel.add(rollTextLabel);
        for(int i = 0; i < numDice; i++)
            curPanel.add(dicePanels[i]);
        curPanel.add(rollButton);
    }

    public void setRollLabel(int rollsPerTurn, int rollCount)
    {
        if ((rollsPerTurn - rollCount) == 1)
            numRollsLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Roll " + Integer.toString(rollCount) + " <br>(" + Integer.toString(rollsPerTurn - rollCount) + " Roll Left)<html>");
        else
            numRollsLabel.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Roll " + Integer.toString(rollCount) + " <br>(" + Integer.toString(rollsPerTurn - rollCount) + " Rolls Left)<html>");
    }

    // sets the explanation label to "Final Hand"
    public void setExplanationLabelScoringMode()
    {
        rollTextLabel.setText("Final Hand");
    }

    // resets the explanation label to "Select Dice to Keep"
    public void resetExplanationLabelText()
    {
        rollTextLabel.setText("Select Dice to Keep");
        rollTextLabel.setVisible(true);
    }

    // hides the roll button
    public void hideRollButton()
    {
        rollButton.setVisible(false);
    }

    // reveals roll button
    public void revealRollButton()
    {
        rollButton.setVisible(true);
    }

    // hides the die radio buttons that allow the player to select die to keep
    public void hideDiceButtons()
    {
        for(int i = 0; i < numDice; i++)
        {
            dice[i].setButtonInvisibility(); 
            //dice[i].getDieButton().setSelected(false);
        }
    }

    // sets the die radio button to visible
    public void revealDiceButtons()
    {
        for(int i = 0; i < numDice; i++)
            dice[i].setButtonVisibility();  
    }

    // hides the Rolling Explanation label
    public void hideRollText()
    {
        rollTextLabel.setVisible(false);
    }

    // sets the Rolling Explanation label to visible
    public void showRollText()
    {
        rollTextLabel.setVisible(true);
    }

    public JLabel getRollLabel()
    {
        return numRollsLabel;
    }

    public JButton getRollButton()
    {
        return rollButton;
    }

    public JPanel getAppearance()
    {
        return curPanel;
    }
}
