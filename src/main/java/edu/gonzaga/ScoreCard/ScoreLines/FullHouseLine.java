package edu.gonzaga.ScoreCard.ScoreLines;

import edu.gonzaga.*;

public class FullHouseLine extends ScoreLine{
    
    public FullHouseLine(String name, String scoringExplanation){
        super(name, scoringExplanation);
    }

    public FullHouseLine(String name, String scoringExplanation, int numPlayers){
        super(name, scoringExplanation, numPlayers);
    }

    public FullHouseLine(String name, String scoringExplanation, Integer[] playerScores){
        super(name, scoringExplanation, playerScores);
    }

    public void calculateScore(Hand playerHand){
        if(!super.checkUsed()){
            int finalScore;
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
                finalScore = 25;
            } 
            else{
                finalScore = 0;
            }
            super.setPossibleScore(1, finalScore);
        }
    }

}
