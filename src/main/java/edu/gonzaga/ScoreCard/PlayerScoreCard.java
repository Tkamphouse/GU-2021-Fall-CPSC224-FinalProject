package edu.gonzaga.ScoreCard;

import edu.gonzaga.Hand;

public class PlayerScoreCard extends ScoreCard{

    private PlayerScoreCardView playerView;
    private ScoreCardView scoreTable;

    public PlayerScoreCard(String playerName, int numDiceSides){
        super(playerName + "'s ScoreCard", numDiceSides, 1);
        scoreTable = super.getView();
        playerView = new PlayerScoreCardView(scoreTable);
    }

    public PlayerScoreCardView getPlayerScoreCardView(){
        return playerView;
    }

    public void displayPossibleScores(Hand currentHand){
        for(int i = 0; i < 13; i++){
            super.getScoreLines().get(i).calculateScore(currentHand);
        }
    }

    public void score(){
        int scoringRow = playerView.getSelectedScoringIndex() + 1;
        int score = super.getScoreLines().get(scoringRow - 1).getPossibleScore(0);
        manualSetScore(scoringRow, score);
        playerView.deleteScoringOption(scoringRow);
        for(int i = 0; i < 6; i++){
            super.getTotalLines().get(i).calculateScore(new Hand());
        }
    }

    public void manualSetScore(int scoringRow, int score){
        super.setScore(scoringRow, 1, score);
    }

    public void hideScoringMenu(){
        playerView.hideScoringMenu();
    }

    public void revealScoringMenu(){
        playerView.revealScoringMenu();
    }

    public boolean checkFull(){
        if(checkAvailableSpace() == 0){
            return true;
        }else{
            return false;
        }
    }

    public int checkAvailableSpace(){
        int notUsedCount = 0;
        for (int i = 0; i < super.getScoreLines().size(); i++){
            if(!super.getScoreLines().get(i).checkUsed()){
                notUsedCount++;
            }
        }
        return notUsedCount;
    }

    public int getScore(int row){
        return super.getScoreLines().get(row - 1).getScores().get(0);
    }

    public int getTotalScore(int row){
        return super.getTotalLines().get(row - 1).getScores().get(0);
    }
    
}
