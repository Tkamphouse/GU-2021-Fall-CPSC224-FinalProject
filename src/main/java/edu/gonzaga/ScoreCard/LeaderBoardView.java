package edu.gonzaga.ScoreCard;

import javax.swing.*;
import java.util.ArrayList;
import edu.gonzaga.Player;
import edu.gonzaga.GameComponents.*;
import java.awt.*;

public class LeaderBoardView extends JPanel{
    
    private Color[][] possibleColors = {
        {ColorPalette.lightRed, ColorPalette.red, ColorPalette.darkRed},
        {ColorPalette.lightGreen, ColorPalette.green, ColorPalette.darkGreen},
        {ColorPalette.lightBlue, ColorPalette.blue, ColorPalette.darkBlue},
        {ColorPalette.lightGrey, ColorPalette.grey, ColorPalette.darkGrey},
        {ColorPalette.lightPurple, ColorPalette.purple, ColorPalette.darkPurple},
        {ColorPalette.lightTeal, ColorPalette.teal, ColorPalette.darkTeal},
        {ColorPalette.lightBrown, ColorPalette.brown, ColorPalette.darkBrown},
        {ColorPalette.lightOrange, ColorPalette.orange, ColorPalette.darkOrange}
    };
    private ScoreCardView scoreTableView;
    private GameLabel winnerLabel;
    private ArrayList<Player> players;

    public LeaderBoardView(ScoreCardView scoreTableRef, ArrayList<Player> players){
        this.setLayout(null);
        scoreTableView = scoreTableRef;
        winnerLabel = new GameLabel("Not Assigned", SwingConstants.CENTER);
        this.players = players;
        this.setSize(scoreTableView.getWidth(), scoreTableView.getHeight() + 30);
        configureView();
    }

    public void setWinnerLabel(){
        Player winner = players.get(0);
        for(int i = 0; i < players.size(); i++){
            if(winner.getFinalScore() < players.get(i).getFinalScore()){
                System.out.println(winner.getFinalScore() + ", " + players.get(i).getFinalScore());
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
        scoreTableView.color(ColorPalette.darkRed, ColorPalette.lightRed, ColorPalette.red);
        //scoreTableView.color(titleColor, columnHeadersColor, possibleScoreColor);
        colorPlayerColumns();
        setWinnerLabel();
        this.add(scoreTableView);
        this.add(winnerLabel);
    }

    public void colorPlayerColumns(){///////////////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0; i < players.size(); i++){
            scoreTableView.colorTitleCell(possibleColors[i][1], 1, i + 3);
            scoreTableView.colorTitleCell(possibleColors[i][1], 2, i + 3);
            for(int j = 1; j <= 13; j++){
                scoreTableView.colorScoreCell(possibleColors[i][0], j, i + 3);
            }
            for(int j = 1; j <= 6; j++){
                scoreTableView.colorTotalCell(possibleColors[i][0], j, i + 3);
            }
        }
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

    public GameLabel getWinnerLabel(){
        return winnerLabel;
    }

}
