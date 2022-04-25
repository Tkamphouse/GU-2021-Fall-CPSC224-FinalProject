package edu.gonzaga.ScoreCard;

import edu.gonzaga.Hand;

public class PlayerScoreCard extends ScoreCard{

    private static int cardCount = 0;
    private int id;
    private PlayerScoreCardView playerView;
    private ScoreCardView scoreTable;

    public PlayerScoreCard(String playerName, int numDiceSides){
        super(playerName + "'s ScoreCard", numDiceSides, 1);
        //System.out.println("1");
        cardCount++;
        id = cardCount;
        scoreTable = super.getView();
        playerView = new PlayerScoreCardView(scoreTable, id);
    }

    public PlayerScoreCardView getPlayerScoreCardView(){
        return playerView;
    }

    public void displayPossibleScores(Hand currentHand){
        for(int i = 0; i < 13; i++){
            super.getScoreLines().get(i).calculateScore(currentHand);
        }
    }

    public void resetPossibleScores(){
        for(int i = 0; i < 13; i++){
            super.getScoreLines().get(i).resetPossibleScore(1);
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
        for(int i = 0; i < 6; i++){
            super.getTotalLines().get(i).calculateScore(new Hand());
        }
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

    public int getFinalScore(){
        //return super.getScoreLines().get(12).getScores().get(0);
        return super.getTotalLines().get(5).getScore(1);
    }
    
}
