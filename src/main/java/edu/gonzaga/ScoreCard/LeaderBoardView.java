package edu.gonzaga.ScoreCard;

import javax.swing.*;
import java.util.ArrayList;
import edu.gonzaga.Player;
import edu.gonzaga.GameComponents.*;

public class LeaderBoardView extends JPanel{
    
    private ScoreCardView scoreTableView;
    private GameLabel winnerLabel;
    private ArrayList<Player> players;

    public LeaderBoardView(ScoreCardView scoreTableRef, ArrayList<Player> players){
        this.setLayout(null);
        scoreTableView = scoreTableRef;
        winnerLabel = new GameLabel("Not Assigned", SwingConstants.CENTER);
        this.players = players;
        this.setSize(scoreTableView.getWidth(), scoreTableView.getHeight() + 30);
        setWinnerLabel();
        configureView();
    }

    public void setWinnerLabel(){
        Player winner = players.get(0);
        for(int i = 0; i < players.size(); i++){
            if(winner.getFinalScore()< players.get(i).getFinalScore()){
                winner = players.get(i);
            }
        }
        winnerLabel.setText(winner.getName() + " is the winner!");
        winnerLabel.setSize(scoreTableView.getWidth(), 30);
    }

    public void configureView(){
        scoreTableView.setLocation(0, 0);
        winnerLabel.setLocation(0, scoreTableView.getHeight());
        scoreTableView.setUpperScoreCardTitles(new ArrayList<String>(){{
            for(int i = 0; i < players.size(); i++){
                add(players.get(i).getName());
            }
        }});
        addPlayerScores();
        this.add(scoreTableView);
        this.add(winnerLabel);
    }

    public void addPlayerScores(){
        for(int currentPlayer = 0; currentPlayer < players.size(); currentPlayer++){
            for(int rowIndex = 0; rowIndex < scoreTableView.getNumScoringRows(); rowIndex++){
                int newScore = players.get(currentPlayer).getScore(rowIndex + 1);
                int scoreColumn = currentPlayer + 1;
                scoreTableView.getScoreLineViews().get(rowIndex).updateScoreCell(newScore, scoreColumn);
            }
            for(int rowIndex = 0; rowIndex < 6; rowIndex++){
                int newScore = players.get(currentPlayer).getTotalScore(rowIndex + 1);
                int scoreColumn = currentPlayer + 1;
                scoreTableView.getTotalLineViews().get(rowIndex).updateScoreCell(newScore, scoreColumn);
            }
        }
    }

}
