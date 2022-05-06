package edu.gonzaga.ScoreCard.ScoreLines;

import edu.gonzaga.*;
import java.util.Arrays;

public class StraightLine extends ScoreLine {

    private int targetLengthOfStraight;
    
    public StraightLine(String name, String scoringExplanation, int lengthOfStraight) {
        super(name, scoringExplanation);
        targetLengthOfStraight = lengthOfStraight;
    }

    public StraightLine(String name, String scoringExplanation, int lengthOfStraight, int numScores) {
        super(name, scoringExplanation, numScores);
        targetLengthOfStraight = lengthOfStraight;
    }

    public StraightLine(String name, String scoringExplanation, Integer[] playerScores, int lengthOfStraight) {
        super(name, scoringExplanation, playerScores);
        targetLengthOfStraight = lengthOfStraight;
    }

    public void calculateScore(Hand currHand) {
        if (!super.checkUsed()) {
            int finalScore;
            int lengthOfStraight = 1;
            int currentLength = 1;
            int[] dieValues = new int[currHand.getSize()];
            for (int i = 0; i < dieValues.length; i++) {
                dieValues[i] = currHand.getDie(i).getSideUp();
            }
            Arrays.sort(dieValues);
            for (int i = 0; i < dieValues.length - 1; i++) {
                if (dieValues[i] + 1 == dieValues[i + 1]) { //is current die less than next die
                    currentLength++;
                } else if (dieValues[i] + 1 < dieValues[i + 1]) {//is current die less than next die  
                    currentLength = 1;
                }
                if (currentLength > lengthOfStraight) { //is current length of straight count higher than stored length of straight
                    lengthOfStraight = currentLength;
                }
            }
            if (lengthOfStraight >= targetLengthOfStraight) { 
                if (targetLengthOfStraight == 4) { // return 30 for small straight
                    finalScore = 30;
                } else { //return 40 for large straight
                    finalScore = 40;
                }
            } else { //return 0 for no straight
                finalScore = 0;
            }
            super.setPossibleScore(1, finalScore);
        }
    }

}
