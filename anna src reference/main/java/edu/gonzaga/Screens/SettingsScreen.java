/* (C)2022 */
package edu.gonzaga.Screens;

import javax.swing.*;
import edu.gonzaga.FileCabinet;
import edu.gonzaga.GameComponents.GameButton;
import edu.gonzaga.GameComponents.GameLabel;
import java.awt.*;
import java.awt.event.*;

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

/** Class for creating a settings screen the user will use to customize game settings */
public class SettingsScreen extends JPanel{

    private static final Color backgroundColor = new Color(228, 216, 200);
    private static final Color lightBackgroundColor = new Color(237, 228, 215);
    private static final Color darkBrown = new Color(27, 25, 21);
    private static final Color lightRed = new Color(177, 107, 73);

    private FileCabinet gameData = new FileCabinet("\\src\\yahtzeeConfig.txt");
    private JLabel titleStrip = new GameLabel();;
    private GameLabel title = new GameLabel("Game Settings");
    private GameLabel numDiceLabel = new GameLabel("Number of Dice:");
    private Integer[] numDiceOptions = {5, 6, 7};
    private int numDice;
    private JComboBox<Integer> numDiceBox;
    private GameLabel numSidesLabel = new GameLabel("Number of Sides on Dice:");
    private Integer[] numSidesOptions = {6, 8, 12};
    private int numSides;
    private JComboBox<Integer> numSidesBox;
    private GameLabel numRollsLabel = new GameLabel("Number of Rolls per Turn:");
    private Integer[] numRollsOptions = {2, 3, 4};
    private int numRolls;
    private JComboBox<Integer> numRollsBox;
    private GameButton goToIntroScreen = new GameButton("Return to Title", 6);

    public SettingsScreen(Boolean visibility){
        this.setLayout(null);
        this.setBounds(0, 0, 800, 800);
        this.setBackground(backgroundColor);
        configureComponentViewSettings();
        addComponents();
        this.setVisible(visibility);
    }

    /** sets up component visual settings*/
    public void configureComponentViewSettings(){
        titleStrip.setOpaque(true);
        titleStrip.setBackground(lightRed);
        title.setTextSize(20);
        numDiceBox = new JComboBox<>(numDiceOptions);
        numDiceBox.setBackground(lightBackgroundColor);
        numDiceBox.setForeground(darkBrown);
        numSidesBox = new JComboBox<>(numSidesOptions);
        numSidesBox.setBackground(lightBackgroundColor);
        numSidesBox.setForeground(darkBrown);
        numRollsBox = new JComboBox<>(numRollsOptions);
        numRollsBox.setBackground(lightBackgroundColor);
        numRollsBox.setForeground(darkBrown);
        setComponentBounds();
    }

    /** sets component location and size*/
    public void setComponentBounds(){
        titleStrip.setBounds(0, 45, 800, 40);
        title.setBounds(50, 50, 200, 30);
        numDiceLabel.setBounds(50, 150, 200, 30);
        numDiceBox.setBounds(250, 150, 150, 30);
        numSidesLabel.setBounds(50, 200, 200, 30);
        numSidesBox.setBounds(250, 200, 150, 30);
        numRollsLabel.setBounds(50, 250, 200, 30);
        numRollsBox.setBounds(250, 250, 150, 30);
        numRollsBox.setBounds(250, 250, 150, 30);
        goToIntroScreen.setBounds(50, 350, 200, 30);
    }

    /** adds all components to object*/
    public void addComponents(){
        this.add(title);
        this.add(titleStrip);
        this.add(numDiceLabel);
        this.add(numDiceBox);
        this.add(numSidesLabel);
        this.add(numSidesBox);
        this.add(numRollsLabel);
        this.add(numRollsBox);
        this.add(goToIntroScreen);
    }

    /**
    * Makes two dice comparable using <, ==, >, etc. based on sideUp values.
    *
    * @param titleScreen JPanel value that represents the Title screen that the goToIntroScreen button navigates to
    */
    public void setSwitchButtons(JPanel titleScreen){
        goToIntroScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                updateValues();
                gameData.updateValues(numDice, numSides, numRolls);
                titleScreen.setVisible(true);
                setVisible(false);
            }
        });
    }

    /**
    * returns setting information stored in combo boxes
    *
    * @return Integer[] values that correspond to the game setting values in the following order: 
    * number of dice, number of sides on dice, number of rolls per turn
    */
    public Integer[] getGameData(){
        Integer[] gameData = new Integer[3];
        gameData[0] = numDiceBox.getItemAt(numDiceBox.getSelectedIndex());
        gameData[1] = numSidesBox.getItemAt(numSidesBox.getSelectedIndex());
        gameData[2] = numRollsBox.getItemAt(numRollsBox.getSelectedIndex());
        return gameData;
    }

    /** sets numDice, numSides, and numRolls values based off of the selected combo box values*/
    public void updateValues(){
        numDice = numDiceBox.getItemAt(numDiceBox.getSelectedIndex());
        numSides = numSidesBox.getItemAt(numSidesBox.getSelectedIndex());
        numRolls = numRollsBox.getItemAt(numRollsBox.getSelectedIndex());
    }

}