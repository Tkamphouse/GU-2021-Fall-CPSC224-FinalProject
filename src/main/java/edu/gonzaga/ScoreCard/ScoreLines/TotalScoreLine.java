package edu.gonzaga.ScoreCard.ScoreLines;

import java.util.ArrayList;
import edu.gonzaga.*;

public class TotalScoreLine extends ScoreLine {

    private ArrayList<ScoreLine> linesToTotal = new ArrayList<>();

    public TotalScoreLine(String name, String scoringExplanation, ArrayList<ScoreLine> linesToTotal) {
        super(name, scoringExplanation);
        this.linesToTotal = linesToTotal;
    }

    public TotalScoreLine(String name, String scoringExplanation, int numScores, ArrayList<ScoreLine> linesToTotal) {
        super(name, scoringExplanation, numScores);
        this.linesToTotal = linesToTotal;
    }

    public int getScore(int scoreColumn) {
        int total = 0;
        for (int i = 0; i < linesToTotal.size(); i++) {
            total += linesToTotal.get(i).getScores().get(scoreColumn - 1);
        }
        return total;
    }

    public void calculateScore(Hand hand) {
        int total = 0;
        int lineToTotalScore;
        for (int i = 0; i < linesToTotal.size(); i++) {
            lineToTotalScore = linesToTotal.get(i).getScores().get(0);
            if (lineToTotalScore == -1) {
                total += 0;
            } else {
                total += lineToTotalScore;
            }
        }
        getScores().set(0, total);
        super.setScore(1, total); 
    }
    
}
