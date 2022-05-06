package edu.gonzaga.ScoreCard.ScoreLines;

import edu.gonzaga.*;

public class BonusLine extends ScoreLine {

    private int targetScore = 63;
    private ScoreLine lineToWatch;

    public BonusLine(String name, String scoringExplanation, ScoreLine lineToWatch) {
        super(name, scoringExplanation);
        this.lineToWatch = lineToWatch;
    }

    public BonusLine(String name, String scoringExplanation, int numScores, ScoreLine lineToWatch) {
        super(name, scoringExplanation, numScores);
        this.lineToWatch = lineToWatch;
    }

    public void calculateScore(Hand hand) {
        int finalScore;
        for (int i = 0; i < lineToWatch.getScores().size(); i++) {
            finalScore = 0;
            if (lineToWatch.getScores().get(i) >= targetScore) {
                finalScore = 35;
            }
            super.setScore(i + 1, finalScore);
        }
    }
    
}
