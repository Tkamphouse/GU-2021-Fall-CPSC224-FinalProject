package edu.gonzaga.ScoreCard.ScoreLines;

import edu.gonzaga.*;

public class OfAKindLine extends ScoreLine{

    private int howManyOfAKind;

    public OfAKindLine(String name, String scoringExplanation, int howManyOfAKind, int numScores){
        super(name, scoringExplanation, numScores);
        this.howManyOfAKind = howManyOfAKind;
    }

    public OfAKindLine(String name, String scoringExplanation, Integer[] playerScores, int howManyOfAKind){
        super(name, scoringExplanation, playerScores);
        this.howManyOfAKind = howManyOfAKind;
    }

    public void calculateScore(Hand playerHand){
        if(!super.checkUsed()){
            int finalScore;
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
                finalScore = 0;
            }
            else if(howManyOfAKind == 5){ //score 50 if yahtzee
                finalScore = 50;
            }
            else{ //add up dice values if 3/4 of a kind ///////////////////////////////////////////////////////////to fix!!!!!!!!!!!!!!!!!!!
                finalScore = playerHand.sum();
                //finalScore = 0; 
            }
            super.setPossibleScore(1, finalScore);
        }

    }
    
}
