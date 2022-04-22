package edu.gonzaga;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.*;
import edu.gonzaga.ScoreCard.PlayerScoreCard;
import edu.gonzaga.ScoreCard.PlayerScoreCardView;

public class Player {

    PlayerScoreCard playerScoreCard;
    String name;
    Boolean playing = false;

    public Player(String name){
        this.name = name;
        playerScoreCard = new PlayerScoreCard(name, 6);
        setNotPlaying();
        hideScoringMenu();
    }

    public Player(String name, JButton scoreButton){
        this.name = name;
        playerScoreCard = new PlayerScoreCard(name, 6);
        setNotPlaying();
        hideScoringMenu();
        attachScoreButton(scoreButton);
    }

    public Player(String name, JButton scoreButton, boolean playing){
        this.name = name;
        playerScoreCard = new PlayerScoreCard(name, 6);
        if(playing){
            setPlaying();
        }else{
            setNotPlaying();
        }
        hideScoringMenu();
        attachScoreButton(scoreButton);
    }

    public void attachScoreButton(JButton scoreButton){
        int numButtons = playerScoreCard.getPlayerScoreCardView().getScoringMenu().getButtons().size();
        JRadioButton tempButton;
        for(int i = 0; i < numButtons; i++){
            tempButton = playerScoreCard.getPlayerScoreCardView().getScoringMenu().getButtons().get(i);
            tempButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
                    scoreButton.setVisible(true);
                    //System.out.println("Score Button Pressed");
                }
            });
        }
    }

    public ArrayList<Integer> getScores(){
        return playerScoreCard.getScores();
    }

    public int getScore(int row){
        return playerScoreCard.getScore(row);
    }

    public int getTotalScore(int row){
        return playerScoreCard.getTotalScore(row);
    }

    public int getFinalScore(){
        int index = playerScoreCard.getScores().size() - 1;
        return playerScoreCard.getScores().get(index);
    }

    public PlayerScoreCardView getView(){
        return playerScoreCard.getPlayerScoreCardView();
    }
    
    public String getName(){
        return name;
    }

    public boolean checkScoreCardFull(){
        return playerScoreCard.checkFull();
    }

    public int checkScoreCardAvailableSpace(){
        return playerScoreCard.checkAvailableSpace();
    }

    public void setPlaying(){
        this.playing = true;
        this.playerScoreCard.getPlayerScoreCardView().setVisible(true);
    }

    public void setNotPlaying(){
        this.playing = false;
        this.playerScoreCard.getPlayerScoreCardView().setVisible(false);
    }

    public void showPossibleScores(Hand currentHand){
        playerScoreCard.displayPossibleScores(currentHand);
    }

    public void resetPossibleScores(){
        playerScoreCard.resetPossibleScores();
    }

    public void recordScore(){
        playerScoreCard.score();
    }

    public void hideScoringMenu(){
        playerScoreCard.hideScoringMenu();
    }

    public void revealScoringMenu(){
        playerScoreCard.revealScoringMenu();
    }

    public Boolean checkifPlaying(){
        return playing;
    }


}
