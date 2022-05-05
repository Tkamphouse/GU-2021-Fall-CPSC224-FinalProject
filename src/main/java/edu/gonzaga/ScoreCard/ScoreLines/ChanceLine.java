package edu.gonzaga.ScoreCard.ScoreLines;

import edu.gonzaga.*;

public class ChanceLine extends ScoreLine{

    public ChanceLine(String name, String scoringExplanation){
        super(name, scoringExplanation);
    }

    public ChanceLine(String name, String scoringExplanation, Integer score){
        super(name, scoringExplanation, score);
    }

    public ChanceLine(String name, String scoringExplanation, Integer[] playerScores){
        super(name, scoringExplanation, playerScores);
    }

    public void calculateScore(Hand playerHand){
        if(!super.checkUsed()){
            super.setPossibleScore(1, playerHand.sum());
        }
    }
    
}
