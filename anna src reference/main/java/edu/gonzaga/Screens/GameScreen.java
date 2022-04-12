/* (C)2022 */
package edu.gonzaga.Screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.gonzaga.*;
import edu.gonzaga.GameComponents.GameButton;
import edu.gonzaga.GameComponents.GameLabel;

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

/**  Class creating a game play screen which player will use to play yahtzee*/
public class GameScreen extends JPanel{

    private static final Color backgroundColor = new Color(228, 216, 200);
    private static final Color lightRed = new Color(177, 107, 73);
    private static final Color darkGreen = new Color(100, 110, 86);
    private static final Color darkBlue = new Color(111, 147, 131);
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_LENGTH = 800;
    private static final int DEFAULT_GAME_TYPE = 6;
    private static final int DEFAULT_COMPONENT_LENGTH = 30;

    private static final int SCORECARD_X_COORDINATE = 50;
    private static final int SCORECARD_X_COORDINATE_END_GAME = SCORECARD_X_COORDINATE + 120;
    private static final int SCORECARD_Y_COORDINATE = 100;
    private static final int TURN_LABEL_X_COORDINATE = 0;
    private static final int TURN_LABEL_Y_COORDINATE = 25;
    private static final int TURN_LABEL_WIDTH = DEFAULT_LENGTH;
    private static final int TURN_LABEL_FONT_SIZE = 20;
    private static final int HOME_SCREEN_BUTTON_X_COORDINATE = 300;
    private static final int HOME_SCREEN_BUTTON_Y_COORDINATE = 700;
    private static final int HOME_SCREEN_BUTTON_WIDTH = 150;
    private static final int FINISH_GAME_BUTTON_X_COORDINATE = 300;
    private static final int FINISH_GAME_BUTTON_Y_COORDINATE = 650;
    private static final int FINISH_GAME_BUTTON_WIDTH = 150;
    private static final int HAND_Y_COORDINATE = SCORECARD_Y_COORDINATE;
    private static final int EXPLANATION_POS_SCORES_X_COORDINATE = SCORECARD_X_COORDINATE;
    private static final int EXPLANATION_POS_SCORES_WIDTH = 180;
    private static final int POS_SCORE_COLOR_X_COORDINATE = SCORECARD_X_COORDINATE + 180;
    private static final int POS_SCORE_COLOR_WIDTH = 50;

    private GameLabel turnLabel;
    private JButton rollButton;
    private Hand hand;
    private JPanel handPanel;
    private ScoreCard scoreCard;
    private JPanel scoreCardPanel;
    private GameButton goToHomeScreen;
    private int turnCount;
    private int rollsPerTurn;
    private int rollCount;
    private boolean scoringMode;
    private FileCabinet gameInfo;
    private GameButton finishGame;
    private GameLabel explanationPossibleScores;
    private GameLabel possibleScoreColor;
    private int explanationPossibleScoreYCoordinate;

    public GameScreen(Boolean visibility){
        this.setLayout(null);
        this.setSize(DEFAULT_WIDTH, DEFAULT_LENGTH);
        this.setBackground(backgroundColor);
        turnLabel = new GameLabel("Turn: " + Integer.toString(turnCount), SwingConstants.CENTER);
        turnLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, TURN_LABEL_FONT_SIZE));
        turnLabel.setBounds(TURN_LABEL_X_COORDINATE, TURN_LABEL_Y_COORDINATE, TURN_LABEL_WIDTH, DEFAULT_COMPONENT_LENGTH);
        explanationPossibleScores = new GameLabel("Possible Scores Displayed in");
        goToHomeScreen = new GameButton("Exit Game", DEFAULT_GAME_TYPE);
        goToHomeScreen.setBounds(HOME_SCREEN_BUTTON_X_COORDINATE, HOME_SCREEN_BUTTON_Y_COORDINATE, HOME_SCREEN_BUTTON_WIDTH, DEFAULT_COMPONENT_LENGTH);
        this.setVisible(visibility);
    }

    /**
    * Sets action listener on the goToHomeScreenButton that switches which window is visible when clicked
    *
    * @param titleScreen JPanel value that corresponds to the window the goToHomeScreen button will navigate to 
    */
    public void setSwitchButtons(JPanel titleScreen){
        goToHomeScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                titleScreen.setVisible(true);
                setVisible(false);
            }
        });
    }

    /** sets all game stats and components to the states they need to be in for the beginning of a game*/
    public void setBeginningGameStats(){
        this.removeAll();
        turnCount = 1;
        rollCount = 0;
        scoringMode = false;
        gameInfo = new FileCabinet("\\src\\yahtzeeConfig.txt");
        initializeComponents();
        setComponentLocations();
        setColorSettings();
        setMechanics();
        configureView();
    }

    /** Initializes game components that need to be initialized upon navigating to the game screen*/
    private void initializeComponents(){
        goToHomeScreen.setGameType(gameInfo.getNumSides());
        finishGame = new GameButton("Finish Game", gameInfo.getNumSides());
        finishGame.setVisible(false);
        rollsPerTurn = gameInfo.getNumRolls();
        scoreCard = new ScoreCard(gameInfo.getNumSides());
        scoreCardPanel = scoreCard.getAppearance();
        hand = new Hand(gameInfo.getNumDice(), gameInfo.getNumSides(), scoreCard.getTotalLength());
        hand.hideDiceButtons();
        handPanel = hand.getAppearance();
        rollButton = hand.getRollButton();
        scoreCard.setScoringMenuMechanics(rollButton, finishGame); 
        explanationPossibleScoreYCoordinate = SCORECARD_Y_COORDINATE + scoreCard.getTotalLength() + 5;
        explanationPossibleScores.setVisible(false);
        possibleScoreColor = new GameLabel("Not Assigned");
        possibleScoreColor.setVisible(false);
        updateLabels();
        hand.hideRollingExplanation();
    }

    /** sets the location of components that need to be set upon navigation the the game screen*/
    private void setComponentLocations(){
        finishGame.setBounds(FINISH_GAME_BUTTON_X_COORDINATE, FINISH_GAME_BUTTON_Y_COORDINATE, FINISH_GAME_BUTTON_WIDTH, DEFAULT_COMPONENT_LENGTH);
        scoreCardPanel.setLocation(SCORECARD_X_COORDINATE, SCORECARD_Y_COORDINATE);
        handPanel.setLocation(SCORECARD_X_COORDINATE + scoreCard.getTotalWidth() + 30, HAND_Y_COORDINATE);
        explanationPossibleScores.setBounds(EXPLANATION_POS_SCORES_X_COORDINATE, explanationPossibleScoreYCoordinate, EXPLANATION_POS_SCORES_WIDTH, DEFAULT_COMPONENT_LENGTH);
        possibleScoreColor.setBounds(POS_SCORE_COLOR_X_COORDINATE, explanationPossibleScoreYCoordinate, POS_SCORE_COLOR_WIDTH, DEFAULT_COMPONENT_LENGTH);
    }

    /** sets the color scheme of the Game Screen based off of the game type or the number of sides on the game dice*/
    private void setColorSettings(){
        if(gameInfo.getNumSides() == 12){
            possibleScoreColor.setText("Blue");
            possibleScoreColor.setForeground(darkBlue);
        }else if(gameInfo.getNumSides() == 8){
            possibleScoreColor.setText("Green");
            possibleScoreColor.setForeground(darkGreen);
        }else{
            possibleScoreColor.setText("Red");
            possibleScoreColor.setForeground(lightRed);
        }
    }

    /**adds all components to the object*/
    private void configureView(){
        this.add(turnLabel);
        this.add(handPanel);
        this.add(finishGame);
        this.add(scoreCardPanel);
        this.add(explanationPossibleScores);
        this.add(possibleScoreColor);
        this.add(goToHomeScreen);
    }

    /** adds action listeners to the roll button and finish game button that roll the hand and do endgame calculations respectively*/
    private void setMechanics(){
        rollButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(scoringMode){
                    scoreTurn();
                }
                for(int i = 0; i < hand.getSize(); i++){
                    hand.getDie(i).roll();
                }
                scoreCard.showScoringOptions(hand);
                rollCount++;
                if(rollCount == 1){
                    hand.revealDiceButtons();
                    explanationPossibleScores.setVisible(true);
                    possibleScoreColor.setVisible(true);
                }
                updateLabels();
                if(rollCount == rollsPerTurn){
                    setScoringMode();
                }
            }
        });
        finishGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                scoreTurn();
                explanationPossibleScores.setVisible(false);
                possibleScoreColor.setVisible(false);
                handPanel.setVisible(false);
                turnLabel.setVisible(false);
                scoreCard.calcFinalPoints();
                scoreCardPanel.setLocation(SCORECARD_X_COORDINATE_END_GAME, SCORECARD_Y_COORDINATE);
                finishGame.setVisible(false);
            }
        });
    }

    /** sets game cmponents to the states they must be in while player is scoring a turn*/
    private void setScoringMode(){
        scoringMode = true;
        hand.setExplanationLabelScoringMode();
        hand.hideDiceButtons();
        scoreCard.setScoreMenuVisibility(true);
        rollButton.setVisible(false);
    }

    /** records the selected score in the scorecard and resets components edited by scoring mode*/
    private void scoreTurn(){
        for(int i = 0; i < scoreCard.getScoringMenu().length; i++){
            if(scoreCard.getScoringMenu()[i].isSelected()){
                scoreCard.setScore(i + 1);
            }
        }
        scoringMode = false;
        scoreCard.setScoreMenuVisibility(false);
        turnCount++;
        rollCount = 0;
    }

    /** updates the turn label text based on the current turn, as well as the roll label*/
    public void updateLabels(){
        turnLabel.setText("Turn: " + Integer.toString(turnCount));
        hand.resetExplanationLabelText();
        hand.setRollLabel(rollsPerTurn, rollCount);
    }
    
}