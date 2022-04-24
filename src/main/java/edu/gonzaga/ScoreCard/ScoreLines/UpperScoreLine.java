package edu.gonzaga.ScoreCard.ScoreLines;

import edu.gonzaga.*;

public class UpperScoreLine extends ScoreLine{
    
    private int rowNum;

    public UpperScoreLine(String name, String scoringExplanation, int rowNum, int numPlayers){
        super(name, scoringExplanation, numPlayers);
        //System.out.println("1");
        this.rowNum = rowNum;
    }

    public UpperScoreLine(String name, String scoringExplanation, Integer[] playerScores, int rowNum){
        super(name, scoringExplanation, playerScores);
        this.rowNum = rowNum;
    }

    public void calculateScore(Hand playerHand){
        if(!super.checkUsed()){
            int finalScore;
            int countPoints = 0;
            //record each occurance of specified side
            for(int i = 0; i < playerHand.getSize(); i++){
                if(playerHand.getDie(i).getSideUp() == rowNum){
                    countPoints++;
                }
            }
            finalScore = (countPoints *= rowNum);//multiply amount of occurances by value of dice side and return

            super.setPossibleScore(1, finalScore);
        }
    } 

}
