package edu.gonzaga.ScoreCard.ScoreLines;

import java.awt.Color;
import java.util.ArrayList;
import edu.gonzaga.*;

public abstract class ScoreLine {
    
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<Integer> possibleScores = new ArrayList<>();
    private ScoreLineView view;

    public ScoreLine(String name, String scoringExplanation){
        scores.add(-1);
        possibleScores.add(-1);
        view = new ScoreLineView(name, scoringExplanation, scores);
    }

    public ScoreLine(String name, String scoringExplanation, int numScores){
        for(int i = 0; i < numScores; i++){
            scores.add(-1);
            possibleScores.add(-1);
        }
        view = new ScoreLineView(name, scoringExplanation, scores);
    }

    public ScoreLine(String name, String scoringExplanation, Integer[] playerScores){
        for(int i = 0; i < playerScores.length; i++){
            scores.add(playerScores[i]);
            possibleScores.add(-1);
        }
        view = new ScoreLineView(name, scoringExplanation, scores);
    }

    public abstract void calculateScore(Hand currentHand);

    public ScoreLineView getView(){
        return view;
    }

    public ArrayList<Integer> getScores(){
        return scores;
    }

    public void setPossibleScore(int scoreColumn, int newScore){
        possibleScores.set(scoreColumn - 1, newScore);
        view.updateScoreCell(newScore, scoreColumn, Color.GRAY);
    }

    public void resetPossibleScore(int scoreColumn){
        if(!checkUsed()){
            possibleScores.set(scoreColumn - 1, -1);
            view.updateScoreCell("-", scoreColumn);
        }
    }

    public void setScore(int scoreColumn, int newScore){
        scores.set(scoreColumn - 1, newScore);
        view.updateScoreCell(newScore, scoreColumn);
    }

    public int getPossibleScore(int scoreColumn){
        return possibleScores.get(scoreColumn - 1);
    }

    public int getScore(int scoreColumn){
        return scores.get(scoreColumn - 1);
    }

    public boolean checkUsed(){
        for(int i = 0; i < scores.size(); i++){
            if(scores.get(i) == -1){
                return false;
            }
        }
        return true;
    }

    public void setLineBackground(Color backgroundColor){
        view.setLineBackground(backgroundColor);
    }

    public void setBottomBorder(){
        view.setBottomBorder();
    }

}
