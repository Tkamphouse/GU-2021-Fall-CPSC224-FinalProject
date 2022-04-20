package edu.gonzaga.ScoreCard.ScoreLines;

import java.util.ArrayList;

public class TitleLineView extends ScoreLineView{

    public TitleLineView(String nameHeader, String scoringExplanationHeader, ArrayList<String> scoreHeaders){
        super(nameHeader, scoringExplanationHeader, scoreHeaders.size());
        setScoreHeaders(scoreHeaders);
    }

    public void setScoreHeaders(ArrayList<String> scoreHeaders){
        for(int i = 0; i < scoreHeaders.size(); i++){
            updateScoreCell(scoreHeaders.get(i), i + 1);
        }
    }
    
}
