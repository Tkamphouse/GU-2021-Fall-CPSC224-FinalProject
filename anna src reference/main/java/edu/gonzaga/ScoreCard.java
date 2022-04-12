/* (C)2022 */
package edu.gonzaga;

import javax.swing.*;
import edu.gonzaga.GameComponents.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

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

/** Class for storing a ScoreTable and a scoring menu mechanic */
public class ScoreCard{

    private static final Color backgroundColor = new Color(228, 216, 200);
    private static final Color darkBrown = new Color(27, 25, 21);
    private static final Color lightRed = new Color(177, 107, 73);
    private static final Color darkGreen = new Color(100, 110, 86);
    private static final Color darkBlue = new Color(111, 147, 131);
    private Color possibleScoreColor;

    private JPanel toPresent;
    private ScoreTable scoreTable;
    private JPanel scoreTablePanel;
    private GameRadioButton[] scoringMenuButtons;
    private ButtonGroup scoringMenuButtonsGroup = new ButtonGroup();
    private Integer[] scores;
    private GameLabel explanationScoringMenu1 = new GameLabel();
    private GameLabel explanationScoringMenu2 = new GameLabel();
    private int explanationScoringMenuWidth;

    public ScoreCard(int numDiceSides){
        setColorSettings(numDiceSides);
        scoreTable = new ScoreTable(numDiceSides);
        scores = new Integer[scoreTable.getTotalNumScoringRows()];
        for(int i = 0; i < numDiceSides; i++){
            scores[i] = null;
        }
        scoringMenuButtons = new GameRadioButton[scoreTable.getTotalNumScoringRows()];
        configureView();
    }

    /**
    * sets the color scheme based of of game type or the number of sides on the game dice
    *
    * @param numDiceSides int value corresponding to the number of sides on the game dice
    */
    private void setColorSettings(int numDiceSides){
        if(numDiceSides == 12){
            possibleScoreColor = darkBlue;
        }else if(numDiceSides == 8){
            possibleScoreColor = darkGreen;
        }else{
            possibleScoreColor = lightRed;
        }
    }

    /** sets up visual settings of components*/
    private void configureView(){
        scoreTablePanel = scoreTable.getAppearance();
        scoreTablePanel.setBorder(BorderFactory.createLineBorder(darkBrown, 3));
        scoreTablePanel.setLocation(0, 0);
        configureScoringMenuView();
        configurePanel();
    }

    /** sets up visual information of the scoring menu components*/
    private void configureScoringMenuView(){
        explanationScoringMenuWidth = 100;
        explanationScoringMenu1.setText("Select Line to");
        explanationScoringMenu1.setBounds(410, 0, explanationScoringMenuWidth, 20);
        explanationScoringMenu2.setText("Record Score");
        explanationScoringMenu2.setBounds(410, 20, explanationScoringMenuWidth, 20);
        for(int i = 0; i < scoreTable.getTotalNumScoringRows(); i++){
            scoringMenuButtons[i] = new GameRadioButton();
            scoringMenuButtons[i].setSize(20, 15);
            scoringMenuButtons[i].setBackground(backgroundColor);
            scoringMenuButtonsGroup.add(scoringMenuButtons[i]);
        }
        setScoringMenuButtonLocations();
        setScoreMenuVisibility(false);
    }

    /** sets locations of the radio button in the scoring menu*/
    private void setScoringMenuButtonLocations(){
        int numUpperScoringRows = scoreTable.getNumUpperScoringRows();
        int rowHeight = scoreTable.getRowHeight();
        int xPosition = 410;
        int yUpperStartingPosition = (30 + rowHeight) + (rowHeight / 6);
        int yLowerStartingPosition = yUpperStartingPosition + (numUpperScoringRows * rowHeight) + (4 * rowHeight);
        for (int i = 0; i < scoringMenuButtons.length; i++){
            for(int j = 0; j < numUpperScoringRows; j++){
                scoringMenuButtons[j].setLocation(xPosition, yUpperStartingPosition + (j * rowHeight));
            }
            for(int j = numUpperScoringRows; j < scoringMenuButtons.length; j++){
                scoringMenuButtons[j].setLocation(xPosition, yLowerStartingPosition + ((j - numUpperScoringRows) * rowHeight));
            }
        }
    }

    /** adds components to the JPanel*/
    private void configurePanel(){
        toPresent = new JPanel();
        toPresent.setLayout(null);
        toPresent.setSize(getTotalWidth(), scoreTable.getTotalLength());
        toPresent.setBackground(backgroundColor);
        toPresent.add(scoreTablePanel);
        for(int i = 0; i < scoringMenuButtons.length; i++){
            toPresent.add(scoringMenuButtons[i]);
        }
        toPresent.add(explanationScoringMenu1);
        toPresent.add(explanationScoringMenu2);
    }

    /**
    * Returns the length of the JPanel
    *
    * @return int value representing the total length of the JPanel
    */
    public int getTotalLength(){
        return scoreTable.getTotalLength();
    }

    /**
    * returns the width of the JPanel
    *
    * @return int value representing the width of the JPanel
    */
    public int getTotalWidth(){
        return scoreTable.getTotalWidth() + explanationScoringMenuWidth;
    }

    /** sets all score values in scores to null and resets ScoreTable values*/
    public void clearScores(){
        for(int i = 0; i < scores.length; i++){
            scores[i] = null;
        }
        for(int i = 0; i < scores.length; i++){
            resetScore(i + 1);
        }
    }

    /**
    * sets action listeners for each scoring menu button so that when pressed, the roll button or end game button apears depending
    * on the part of the game the player is in
    *
    * @param rollButton JButton value corresponding to the button in hand that rolls the dice
    * @param endGame JButton value corresponding to the button that ends the game
    */
    public void setScoringMenuMechanics(JButton rollButton, JButton endGame){
        for(int i = 0; i < scoringMenuButtons.length; i++){
            scoringMenuButtons[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    if(checkAvailableSpaces() == 1){
                        endGame.setVisible(true);
                    }else{
                        rollButton.setVisible(true);
                    }
                }
            });
        }
    }

    /**
    * sets scoring menu components to specified visibility
    *
    * @param visibility Boolean value that coresponds to the desired visibility of the score menu
    */
    public void setScoreMenuVisibility(Boolean visibility){
        if(visibility == true){
            scoringMenuButtonsGroup.clearSelection();
        }
        explanationScoringMenu1.setVisible(visibility);
        explanationScoringMenu2.setVisible(visibility);
        for(int i = 0; i < scoringMenuButtons.length; i++){
            if(scores[i] == null && visibility == true){
                scoringMenuButtons[i].setVisible(visibility);
            } else if(visibility == false){
                scoringMenuButtons[i].setVisible(visibility);
            }
        }
    }

    /**
    * sets score on the scoretable and the scores array
    *
    * @param row int value corresponding to the row the score is to be recorded on
    */
    public void setScore(int row){
        int numUpperScoringRows = scoreTable.getNumUpperScoringRows();
        String possibleScore;
        if(row <= numUpperScoringRows){
            possibleScore = scoreTable.getUpperScoreValue(row);
            scoreTable.setUpperCellValue(row, 3, possibleScore, darkBrown);
        }else{
            possibleScore = scoreTable.getLowerScoreValue(row - numUpperScoringRows);
            scoreTable.setLowerCellValue(row - numUpperScoringRows, 3, possibleScore, darkBrown);
        }
        scores[row - 1] = Integer.parseInt(possibleScore);
    }

    /**
    * sets score on the scoretable and the scores array
    *
    * @param row int value corresponding to the row the score is to be recorded on
    * @param score int value corresponding to the specific score to be set
    */
    public void setScore(int row, int score){
        int numUpperScoringRows = scoreTable.getNumUpperScoringRows();
        if(row < numUpperScoringRows){
            scoreTable.setUpperCellValue(row, 3, Integer.toString(score), darkBrown);
        }else{
            scoreTable.setLowerCellValue(row - numUpperScoringRows, 3, Integer.toString(score), darkBrown);
        }
        scores[row - 1] = score;
    }

    /**
    * resets value on a given scoreline
    *
    * @param row int value corresponding to the row to be reset
    */
    public void resetScore(int row){
        int numUpperScoringRows = scoreTable.getNumUpperScoringRows();
        if(row < numUpperScoringRows){
            scoreTable.setUpperCellValue(row, 3, "-", darkBrown);
        }else{
            scoreTable.setLowerCellValue(row - numUpperScoringRows, 3, "-", darkBrown);
        }
    }

    /**
    * Calculates all possible scores and displays them in the scorecard
    *
    * @param playerHand Hand value correponding to the hand to be calculated for scoring
    */
    public void showScoringOptions(Hand playerHand){
        int numUpperScoringRows = scoreTable.getNumUpperScoringRows();
        for(int i = 0; i < numUpperScoringRows; i++){
            if(scores[i] == null){
                int score = calcUpperScoreLinePoints(playerHand, i + 1);
                scoreTable.setUpperCellValue(i + 1, 3, Integer.toString(score), possibleScoreColor);
            }
        }
        for(int i = numUpperScoringRows; i < scoreTable.getTotalNumScoringRows(); i++){
            int lowerScoreCardIndex = i - numUpperScoringRows;
            if(scores[i] == null){
                int score = 999;
                if(lowerScoreCardIndex == 0 || lowerScoreCardIndex == 1){
                    score = calcOfAKindPoints(playerHand, lowerScoreCardIndex + 3);
                }
                if(lowerScoreCardIndex == 2){
                    score = calcFullHousePoints(playerHand);
                }
                if(lowerScoreCardIndex == 3 || lowerScoreCardIndex == 4){
                    score = calcStraightPoints(playerHand, lowerScoreCardIndex + 1);
                }
                if(lowerScoreCardIndex == 5){
                    score = calcOfAKindPoints(playerHand, 5);
                }
                if(lowerScoreCardIndex == 6){
                    score = playerHand.calcHandSum();
                }
                scoreTable.setLowerCellValue(lowerScoreCardIndex + 1, 3, Integer.toString(score), possibleScoreColor);
            }
        }
    }

    /** calculates bonuses and final score values and sets these scores in the scoretable*/
    public void calcFinalPoints(){
        int upperScoreCardTotal = 0;
        int upperScoreCardBonus = 0;
        int upperScoreCardTotalWithBonus = 0;
        int lowerScoreCardTotal = 0;
        int grandTotal = 0;
        for(int i = 0; i < scoreTable.getNumUpperScoringRows(); i++){
            upperScoreCardTotal += scores[i];
        }
        if(upperScoreCardTotal >= 63){
            upperScoreCardBonus = 35;
        }
        upperScoreCardTotalWithBonus = upperScoreCardTotal + upperScoreCardBonus;
        for(int i = scoreTable.getNumUpperScoringRows(); i < scoreTable.getTotalNumScoringRows(); i++){
            lowerScoreCardTotal += scores[i];
        }
        grandTotal = upperScoreCardTotalWithBonus + lowerScoreCardTotal;
        scoreTable.setUpperCellValue(scoreTable.getNumUpperScoringRows() + 1, 3, upperScoreCardTotal);
        scoreTable.setUpperCellValue(scoreTable.getNumUpperScoringRows() + 2, 3, upperScoreCardBonus);
        scoreTable.setUpperCellValue(scoreTable.getNumUpperScoringRows() + 3, 3, upperScoreCardTotalWithBonus);
        scoreTable.setLowerCellValue(scoreTable.getNumLowerScoringRows() + 1, 3, lowerScoreCardTotal);
        scoreTable.setLowerCellValue(scoreTable.getNumLowerScoringRows() + 2, 3, upperScoreCardTotalWithBonus);
        scoreTable.setLowerCellValue(scoreTable.getNumLowerScoringRows() + 3, 3, grandTotal);
    }

    /**
    * calculates possible score on a given upper score line with a given hand
    *
    * @param playerHand Hand value corresponding to the hand to be calculated
    * @param rowNum int value corresponding to the row the score should be calculated for
    * @return int value corresponding to the possible score if hand is recorded on a given upperscoreline
    */
    public int calcUpperScoreLinePoints(Hand playerHand, int rowNum){
        int countPoints = 0;
        //record each occurance of specified side
        for(int i = 0; i < playerHand.getSize(); i++){
            if(playerHand.getDie(i).getSideUp() == rowNum){
                countPoints++;
            }
        }
        return countPoints *= rowNum;//multiply amount of occurances by value of dice side and return
    }

    /**
    * calculates possible score on a given of a kind line with a given hand
    *
    * @param playerHand Hand value corresponding with the hand to be calculated
    * @param howManyOfAKind int value corresponding to how many of a kind to be calculating for
    * @return int value corresponding with possible score on a given of a kind line
    */
    public int calcOfAKindPoints(Hand playerHand, int howManyOfAKind){
        boolean matchFound = false;
        int count;
        for(int i = 1; i <= playerHand.getDie(0).getNumSides(); i++){ //loop through sides
            count = 0;
            for(int j = 0; j < playerHand.getSize(); j++){ //loop through all dice
                if(playerHand.getDie(j).getSideUp() == i){ //counts each occurance of current side vlaue
                    count++;
                }
            }
            if(count >= howManyOfAKind){ //checks to see if specified amount of one kind has been found
                matchFound = true;
            }
        }
        if(matchFound == false){ //score 0 if didn't find specified amount of one kind
            return 0;
        }
        else if(howManyOfAKind == 5){ //score 50 if yahtzee
            return 50;
        }
        else{ //add up dice values if 3/4 of a kind
            return playerHand.calcHandSum(); 
        }
    }

    /**
    * Calculates the possible score on the full house line with a given hand
    *
    * @param playerHand Hand value corresponding to the hand to be calculated
    * @return int value corresponding to the possible score on the fullhouse line
    */
    public int calcFullHousePoints(Hand playerHand){
        boolean matchOfThree = false;
        boolean matchOfTwo = false;
        int count = 0;
        for(int i = 1; i <= playerHand.getDie(0).getNumSides(); i++){ //loop through sides
            count = 0;
            for(int j = 0; j < playerHand.getSize(); j++){ //loop through dice
                if(playerHand.getDie(j).getSideUp() == i){ // count occurances of current specified side
                    count++;
                }
            }
            if(count >= 3 && matchOfThree == false){
                matchOfThree = true;
            } 
            else if(count >= 2){
                matchOfTwo = true;
            }
        }
        if(matchOfThree == true && matchOfTwo == true){
            return 25;
        } 
        else{
            return 0;
        }
    }

    /**
    * Calculates the possible score on a given straight line with a given hand
    *
    * @param playerHand Hand value corresponding to the hand to be calculated
    * @param targetLengthOfStraight int value corresponding to the length of the straight to be calculated
    * @return int value corresponding to the possible score on a straight line
    */
    public int calcStraightPoints(Hand playerHand, int targetLengthOfStraight){
        int lengthOfStraight = 1;
        int currentLength = 1;
        int[] dieValues = new int[playerHand.getSize()];
        for(int i = 0; i < dieValues.length; i++){
            dieValues[i] = playerHand.getDie(i).getSideUp();
        }
        Arrays.sort(dieValues);
        for(int i = 0; i < dieValues.length - 1; i++){
            if(dieValues[i] + 1 == dieValues[i + 1]){ //is current die less than next die
                currentLength++;
            } 
            else if(dieValues[i] + 1 < dieValues[i + 1]){//is current die less than next die
                currentLength = 1;
            }
            if(currentLength > lengthOfStraight){ //is current length of straight count higher than stored length of straight
                lengthOfStraight = currentLength;
            }
        }
        if(lengthOfStraight >= targetLengthOfStraight){ 
            if(targetLengthOfStraight == 4){ // return 30 for small straight
                return 30;
            } 
            else{ //return 40 for large straight
                return 40;
            }
        } 
        else{ //return 0 for no straight
            return 0;
        }
    }

    /**
    * return the number of scorable line
    *
    * @return int value corresponding to the number of scoring rows
    */
    public int getTotalNumScoringRows(){
        return scoreTable.getTotalNumScoringRows();
    }

    /**
    * Return the array of scoring menu buttons
    *
    * @return GameRadioButton[] value corresponding to the array of scoring menu button
    */
    public GameRadioButton[] getScoringMenu(){
        return scoringMenuButtons;
    }

    /**
    * Checks the number of available spaces on the in the scorecard
    *
    * @return int value corresponding to the number of available spaces
    */
    public int checkAvailableSpaces(){
        int toReturn = 0;
        for(int i = 0; i < scores.length; i++){
            if(scores[i] == null){
                toReturn++;
            }
        }
        return toReturn;
    }

    /**
    * Returns JPanel containg view information for the scorecard
    *
    * @return JPanel value corresponding to the panel containing visual components
    */
    public JPanel getAppearance(){
        return toPresent;
    }

    /**
    * returns the color used to display possible scores in the scorecard
    *
    * @return Color value corresponding to the color of the possible scores displayed in the scorecard
    */
    public Color getPossibleScoreColor(){
        return possibleScoreColor;
    }

    /**
    * returns the score on a given row of scorecard
    *
    * @param row int value representing the row of desired score
    * @return int value representing score value on a given row
    */
    public int getScore(int row){
        return scores[row - 1];
    }

}
