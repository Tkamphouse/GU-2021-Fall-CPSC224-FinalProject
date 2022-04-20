/* (C)2022 */
package edu.gonzaga;

import javax.swing.*;
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

/** Class for representing scoring information in a graphical table */
public class ScoreTable{

    private static final Color backgroundColor = new Color(228, 216, 200);
    private static final Color darkBrown = new Color(27, 25, 21);
    private static final Color lightRed = new Color(177, 107, 73);
    private static final Color darkRed = new Color(150, 70, 47);
    private static final Color lightGreen = new Color(151, 158, 131);
    private static final Color darkGreen = new Color(100, 110, 86);
    private static final Color lightBlue = new Color(159, 188, 170);
    private static final Color darkBlue = new Color(111, 147, 131);

    private Color lightColor;
    private Color darkColor;
    private JPanel toPresent;
    private GameLabel scoreCardTitle = new GameLabel("Score Card", SwingConstants.CENTER);
    private GameLabel[] upperScoreCardTitles = {new GameLabel(" Upper Section"), new GameLabel("How to Score"), new GameLabel("Score")};
    private GameLabel[] acesRow = {new GameLabel(" Aces 1's"), new GameLabel("Count and Add Only Aces"), new GameLabel("-", true)};
    private GameLabel[] twosRow = {new GameLabel(" Twos 2's"), new GameLabel("Count and Add Only Twos"), new GameLabel("-", true)};
    private GameLabel[] threesRow = {new GameLabel(" Three 3's"), new GameLabel("Count and Add Only Threes"), new GameLabel("-", true)};
    private GameLabel[] foursRow = {new GameLabel(" Fours 4's"), new GameLabel("Count and Add Only Fours"), new GameLabel("-", true)};
    private GameLabel[] fivesRow = {new GameLabel(" Fives 5's"), new GameLabel("Count and Add Only Fives"), new GameLabel("-", true)};
    private GameLabel[] sixesRow = {new GameLabel(" Sixes 6's"), new GameLabel("Count and Add Only Sixes"), new GameLabel("-", true)}; 
    private GameLabel[] sevensRow = {new GameLabel(" Sevens 7's"), new GameLabel("Count and Add Only Sevens"), new GameLabel("-", true)};
    private GameLabel[] eightsRow = {new GameLabel(" Eights 8's"), new GameLabel("Count and Add Only Eights"), new GameLabel("-", true)};
    private GameLabel[] ninesRow = {new GameLabel(" Nine 9's"), new GameLabel("Count and Add Only Nines"), new GameLabel("-", true)};
    private GameLabel[] tensRow = {new GameLabel(" Tens 10's"), new GameLabel("Count and Add Only Tens"), new GameLabel("-", true)};
    private GameLabel[] elevensRow = {new GameLabel(" Elevens 11's"), new GameLabel("Count and Add Only Elevens"), new GameLabel("-", true)};
    private GameLabel[] twelvesRow = {new GameLabel(" Twelves 12's"), new GameLabel("Count and Add Only Twelves"), new GameLabel("-", true)};
    private GameLabel[] upperTotalScoreRow = {new GameLabel(" Total Score"), new GameLabel("--->"), new GameLabel("-", true)};
    private GameLabel[] upperBonusRow = {new GameLabel(" Bonus"), new GameLabel("If total score over 62, score 35"), new GameLabel("-", true)};
    private GameLabel[] upperFinalTotalScore = {new GameLabel(" Total"), new GameLabel("--->"), new GameLabel("-", true)};
    private GameLabel[][] upperScoreCardContents;
    private GameLabel[] lowerScoreCardTitles = {new GameLabel(" Lower Section"), new GameLabel("How to Score"), new GameLabel("Score")};
    private GameLabel[] threeOfAKindRow = {new GameLabel(" 3 of a Kind"), new GameLabel("Add Total of All Dice"), new GameLabel("-", true)};
    private GameLabel[] fourOfAKindRow = {new GameLabel(" 4 of a Kind"), new GameLabel("Add Total of All Dice"), new GameLabel("-", true)};
    private GameLabel[] fullHouseRow = {new GameLabel(" Full House"), new GameLabel("Score 25"), new GameLabel("-", true)};
    private GameLabel[] smStraightRow = {new GameLabel(" Sm. Straight"), new GameLabel("Score 30"), new GameLabel("-", true)};
    private GameLabel[] lgStraightRow = {new GameLabel(" Lg. Straight"), new GameLabel("Score 40"), new GameLabel("-", true)};
    private GameLabel[] yahtzee = {new GameLabel(" YAHTZEE"), new GameLabel("Score 50"), new GameLabel("-", true)};
    private GameLabel[] chance = {new GameLabel(" Chance"), new GameLabel("Score Total of All Dice"), new GameLabel("-", true)};
    private GameLabel[] lowerTotalRow = {new GameLabel(" Total (lower)"), new GameLabel("--->"), new GameLabel("-", true)};
    private GameLabel[] lowerUpperTotalRow = {new GameLabel(" Total (upper)"), new GameLabel("--->"), new GameLabel("-", true)};
    private GameLabel[] grandTotalRow = {new GameLabel(" Grand Total"), new GameLabel("--->"), new GameLabel("-", true)};
    private GameLabel[][] lowerScoreCardContents = {lowerScoreCardTitles, threeOfAKindRow, fourOfAKindRow, fullHouseRow, smStraightRow, lgStraightRow, yahtzee, chance, lowerTotalRow, lowerUpperTotalRow, grandTotalRow};
    private int rowWidth;
    private int totalLength;
    private int totalWidth = 400;

    public ScoreTable(int numDiceSides){
        setViewSettings(numDiceSides);
        setUpperScoreCardRows(numDiceSides);
        totalLength = 30 + (upperScoreCardContents.length * rowWidth) + (lowerScoreCardContents.length * rowWidth);
        configureView();
    }

    /**
    * Sets row width and color settings based off of game type, or the number of sides on the game dice
    *
    * @param numDiceSides int value representing the number of sides on the game dice
    */
    private void setViewSettings(int numDiceSides){
        if(numDiceSides == 12){
            this.rowWidth = 18;
            lightColor = lightBlue;
            darkColor = darkBlue;
        }else if(numDiceSides == 8){
            this.rowWidth = 21;
            lightColor = lightGreen;
            darkColor = darkGreen;
        }else{
            this.rowWidth = 23;
            lightColor = lightRed;
            darkColor = darkRed;
        }
    }

    /**
    * assigns values to upperScoreCardContents array based on die type
    *
    * @param numRows int value corresponding to number of upperscorecard rowx
    */
    private void setUpperScoreCardRows(int numRows){
        if(numRows == 6){
            upperScoreCardContents = new GameLabel[][]{upperScoreCardTitles, acesRow, twosRow, threesRow, foursRow, fivesRow, sixesRow, 
                                                    upperTotalScoreRow, upperBonusRow, upperFinalTotalScore};
        } else if(numRows == 8){
            upperScoreCardContents = new GameLabel[][]{upperScoreCardTitles, acesRow, twosRow, threesRow, foursRow, fivesRow, sixesRow, sevensRow, eightsRow,
                                                    upperTotalScoreRow, upperBonusRow, upperFinalTotalScore};
        } else if(numRows == 12){
            upperScoreCardContents = new GameLabel[][]{upperScoreCardTitles, acesRow, twosRow, threesRow, foursRow, fivesRow, sixesRow, sevensRow, eightsRow,
                                                    ninesRow, tensRow, elevensRow, twelvesRow, upperTotalScoreRow, upperBonusRow, upperFinalTotalScore};
        } else{
            upperScoreCardContents = new GameLabel[][]{upperScoreCardTitles, acesRow, upperTotalScoreRow, upperBonusRow, upperFinalTotalScore};
        }
    }

    /** sets visual settings*/
    private void configureView(){
        configureUpperScoreCardViews();
        configureLowerScoreCardViews();
        scoreCardTitle.setBackground(darkColor);
        scoreCardTitle.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
        scoreCardTitle.setBorder(BorderFactory.createMatteBorder(3, 3, 2, 3, darkBrown));
        scoreCardTitle.setOpaque(true);
        scoreCardTitle.setBounds(0, 0, totalWidth, 30);
        setColumnCenterAligned(2);
        setColumnCenterAligned(3);
        setCellBorders();
        configurePanel();
    }

    /** sets size and location of upper score card cells*/
    private void configureUpperScoreCardViews(){
        for(int i = 0; i < upperScoreCardContents.length; i++){
            for(int j = 0; j < 3; j++){
                if(j == 0){
                    upperScoreCardContents[i][j].setSize(100, rowWidth);
                    upperScoreCardContents[i][j].setLocation(0, 30 + (i * rowWidth)); 
                }
                if(j == 1){
                    upperScoreCardContents[i][j].setSize(250, rowWidth);
                    upperScoreCardContents[i][j].setLocation(100, 30 + (i * rowWidth));
                }
                if(j == 2){
                    upperScoreCardContents[i][j].setSize(50, rowWidth);
                    upperScoreCardContents[i][j].setLocation(350, 30 + (i * rowWidth));
                }
            }
        }
    }

    /** sets size and location of lower score card cells*/
    private void configureLowerScoreCardViews(){
        for(int i = 0; i < lowerScoreCardContents.length; i++){
            for(int j = 0; j < 3; j++){
                if(j == 0){
                    lowerScoreCardContents[i][j].setSize(100, rowWidth);
                    lowerScoreCardContents[i][j].setLocation(0, 30 + (upperScoreCardContents.length * rowWidth) + (i * rowWidth));
                }
                if(j == 1){
                    lowerScoreCardContents[i][j].setSize(250, rowWidth);
                    lowerScoreCardContents[i][j].setLocation(100, 30 + (upperScoreCardContents.length * rowWidth) + (i * rowWidth));
                }
                if(j == 2){
                    lowerScoreCardContents[i][j].setSize(50, rowWidth);
                    lowerScoreCardContents[i][j].setLocation(350, 30 + (upperScoreCardContents.length * rowWidth) + (i * rowWidth));
                }
            }
        }
    }

    /**
    * Sets text in a given column to center aligned
    *
    * @param columnNum int value representing the column to be aligned
    */
    public void setColumnCenterAligned(int columnNum){
        for(int i = 0; i < lowerScoreCardContents.length; i++){
            lowerScoreCardContents[i][columnNum - 1].setHorizontalAlignment(SwingConstants.CENTER);
            lowerScoreCardContents[i][columnNum - 1].setVerticalAlignment(SwingConstants.CENTER);
        }
        for(int i = 0; i < upperScoreCardContents.length; i++){
            upperScoreCardContents[i][columnNum - 1].setHorizontalAlignment(SwingConstants.CENTER);
            upperScoreCardContents[i][columnNum - 1].setVerticalAlignment(SwingConstants.CENTER);
        }
    }

    /** gives all the cells in the scoretable a border*/
    public void setCellBorders(){
        for(int i = 0; i < upperScoreCardContents.length; i++){
            for(int j = 0; j < 3; j++){
                if(i == 0){
                    upperScoreCardContents[i][j].setOpaque(true);
                    upperScoreCardContents[i][j].setBackground(lightColor);
                }
                if(j == 0){
                    upperScoreCardContents[i][j].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, darkBrown));
                } else if(j == 2){
                    upperScoreCardContents[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, darkBrown));
                } else{
                    upperScoreCardContents[i][j].setBorder(BorderFactory.createLineBorder(darkBrown));
                }
            }
        }
        for(int i = 0; i < lowerScoreCardContents.length; i++){
            for(int j = 0; j < 3; j++){
                if(i == 0){
                    lowerScoreCardContents[i][j].setOpaque(true);
                    lowerScoreCardContents[i][j].setBackground(lightColor);
                }
                if(j == 0){
                    lowerScoreCardContents[i][j].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, darkBrown));
                } else if(j == 2){
                    lowerScoreCardContents[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, darkBrown));
                } else{
                    lowerScoreCardContents[i][j].setBorder(BorderFactory.createLineBorder(darkBrown));
                }
            }
        }
    }

    /** adds visual components to the JPanel*/
    private void configurePanel(){
        toPresent = new JPanel();
        toPresent.setLayout(null);
        toPresent.setSize(totalWidth, totalLength);
        toPresent.setBackground(backgroundColor);
        toPresent.add(scoreCardTitle);
        for(int i = 0; i < upperScoreCardContents.length; i++){
            for(int j = 0; j < 3; j++){
                toPresent.add(upperScoreCardContents[i][j]);
            }
        }
        for(int i = 0; i < lowerScoreCardContents.length; i++){
            for(int j = 0; j < 3; j++){
                toPresent.add(lowerScoreCardContents[i][j]);
            }
        }
    }

    /**
    * sets an upper score card cell to a given value in a given column and row
    *
    * @param row int value representing the row of value to be changed
    * @param column int value representing the column of the value to be changed
    * @param value String value representing the value the cell will be changed to
    */
    public void setUpperCellValue(int row, int column, String value){
        upperScoreCardContents[row][column - 1].setText(value);
    }

    /**
    * sets an upper score card cell to a given value in a given column and row
    *
    * @param row int value representing the row of value to be changed
    * @param column int value representing the column of the value to be changed
    * @param value int value representing the value the cell will be changed to
    */
    public void setUpperCellValue(int row, int column, int value){
        upperScoreCardContents[row][column - 1].setText(Integer.toString(value));
    }

    /**
    * sets an upper score card cell to a given value in a given column and row in a given color
    *
    * @param row int value representing the row of value to be changed
    * @param column int value representing the column of the value to be changed
    * @param value String value representing the value the cell will be changed to
    * @param color Color value representing the color of the new value
    */
    public void setUpperCellValue(int row, int column, String value, Color color){
        upperScoreCardContents[row][column - 1].setText(value);
        upperScoreCardContents[row][column - 1].setForeground(color);
    }

    /**
    * sets a lower score card cell to a given value in a given column and row
    *
    * @param row int value representing the row of value to be changed
    * @param column int value representing the column of the value to be changed
    * @param value String value representing the value the cell will be changed to
    */
    public void setLowerCellValue(int row, int column, String value){
        lowerScoreCardContents[row][column - 1].setText(value);
    }

    /**
    * sets a lower score card cell to a given value in a given column and row
    *
    * @param row int value representing the row of value to be changed
    * @param column int value representing the column of the value to be changed
    * @param value int value representing the value the cell will be changed to
    */
    public void setLowerCellValue(int row, int column, int value){
        lowerScoreCardContents[row][column - 1].setText(Integer.toString(value));
    }

    /**
    * sets a lower score card cell to a given value in a given column and row to a given color
    *
    * @param row int value representing the row of value to be changed
    * @param column int value representing the column of the value to be changed
    * @param value String value representing the value the cell will be changed to
    * @param color Color value representing the color of the new value
    */
    public void setLowerCellValue(int row, int column, String value, Color color){
        lowerScoreCardContents[row][column - 1].setText(value);
        lowerScoreCardContents[row][column - 1].setForeground(color);
    }

    /**
    * Gets the score value on a given row on the upper score card
    *
    * @param row int value representing the row to retrieve score info from
    * @return String value of score on given row
    */
    public String getUpperScoreValue(int row){
        return upperScoreCardContents[row][2].getText();
    }

    /**
    * Gets the score value on a given row on the lower score card
    *
    * @param row int value representing the row to retrieve score info from
    * @return String value of score on given row
    */
    public String getLowerScoreValue(int row){
        return lowerScoreCardContents[row][2].getText();
    }

    /**
    * return the number of scorelines on the upperscorecard
    *
    * @return int value representing the number of upper scoring rows
    */
    public int getNumUpperScoringRows(){
        int nonScoringRows = 4;
        return upperScoreCardContents.length - nonScoringRows;
    }

    /**
    * return the number of scorelines on the lowerscorecard
    *
    * @return int value representing the number of lower scoring rows
    */
    public int getNumLowerScoringRows(){
        int nonScoringRows = 4;
        return lowerScoreCardContents.length - nonScoringRows;
    }

    /**
    * return the number of scorelines on the scorcard
    *
    * @return int value representing the number scoring rows
    */
    public int getTotalNumScoringRows(){
        return getNumUpperScoringRows() + getNumLowerScoringRows();
    }

    /**
    * returns the length of the JPanel
    *
    * @return int value representing the length of the JPanel
    */
    public int getTotalLength(){
        return totalLength;
    }

    /**
    * returns the width of the JPanel
    *
    * @return int value representing the width of the JPanel
    */
    public int getTotalWidth(){
        return totalWidth;
    }

    /**
    * returns the height of each row on the scorecard
    *
    * @return int value representing the height of each row on the scorecard
    */
    public int getRowHeight(){
        return rowWidth;
    }

    /**
    * returns the JPanel containing all view components
    *
    * @return JPanel value containing the view components
    */
    public JPanel getAppearance(){
        return toPresent;
    }
}
