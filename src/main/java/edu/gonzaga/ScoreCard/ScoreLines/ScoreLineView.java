package edu.gonzaga.ScoreCard.ScoreLines;

import javax.swing.JPanel;
import java.util.ArrayList;
import edu.gonzaga.GameComponents.*;
import java.awt.*;
import javax.swing.*;

public class ScoreLineView extends JPanel{

    private static final int NAME_CELL_WIDTH = 100;
    private static final int SCORING_EXPLANATION_CELL_WIDTH = 250;
    private static final int SCORE_CELL_WIDTH = 50;

    private GameLabel nameCell;
    private GameLabel scoringExplanationCell;
    private ArrayList<GameLabel> scoreCells = new ArrayList<>();
    private int height = 25;
    private int width;

    public ScoreLineView(String name, String scoringExplanations, ArrayList<Integer> scores){
        nameCell = new GameLabel(name);
        scoringExplanationCell = new GameLabel(scoringExplanations, SwingConstants.CENTER);
        GameLabel tempLabel;
        for(int i = 0; i < scores.size(); i++){
            if(scores.get(i) == -1){
                tempLabel = new GameLabel("-", SwingConstants.CENTER);
            } else{
                tempLabel = new GameLabel(Integer.toString(scores.get(i)), true);
                tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
                tempLabel.setVerticalAlignment(SwingConstants.CENTER);
            }
            tempLabel.setTextBold();
            scoreCells.add(tempLabel);
        }
        this.width = NAME_CELL_WIDTH + SCORING_EXPLANATION_CELL_WIDTH + (SCORE_CELL_WIDTH * scoreCells.size());
        configureView();
    }

    public ScoreLineView(String name, String scoringExplanations, int numScores){
        nameCell = new GameLabel(name);
        scoringExplanationCell = new GameLabel(scoringExplanations, SwingConstants.CENTER);
        GameLabel tempLabel;
        for(int i = 0; i < numScores; i++){
            tempLabel = new GameLabel("-", SwingConstants.CENTER);
            scoreCells.add(tempLabel);
        }
        this.width = NAME_CELL_WIDTH + SCORING_EXPLANATION_CELL_WIDTH + (SCORE_CELL_WIDTH * scoreCells.size());
        configureView();
    }

    public ScoreLineView(String name, String scoringExplanations){
        nameCell = new GameLabel(name);
        scoringExplanationCell = new GameLabel(scoringExplanations, SwingConstants.CENTER);
        scoreCells.add(new GameLabel("-", SwingConstants.CENTER));
        this.width = NAME_CELL_WIDTH + SCORING_EXPLANATION_CELL_WIDTH + (SCORE_CELL_WIDTH * scoreCells.size());
        configureView();
    }

    public void configureView(){
        this.setLayout(null);
        this.setSize(width, height);
        setCellDimensions();
        setCellLocations();
        setCellBorders();
        this.add(nameCell);
        this.add(scoringExplanationCell);
        for(int i = 0; i < scoreCells.size(); i++){
            this.add(scoreCells.get(i));
        }
    }
    
    public void setCellDimensions(){
        nameCell.setSize(NAME_CELL_WIDTH, height);
        scoringExplanationCell.setSize(SCORING_EXPLANATION_CELL_WIDTH, height);
        for(int i = 0; i < scoreCells.size(); i++){
            scoreCells.get(i).setSize(SCORE_CELL_WIDTH, height);
        }
    }

    public void setCellLocations(){
        int scoreCellXCoordinate;
        nameCell.setLocation(0, 0);
        scoringExplanationCell.setLocation(NAME_CELL_WIDTH, 0);
        for(int i = 0; i < scoreCells.size(); i++){
            scoreCellXCoordinate = NAME_CELL_WIDTH + SCORING_EXPLANATION_CELL_WIDTH + (SCORE_CELL_WIDTH * i);
            scoreCells.get(i).setLocation(scoreCellXCoordinate, 0);
        }
    }

    public void setCellBorders(){
        nameCell.setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, ColorPalette.textColor));
        scoringExplanationCell.setBorder(BorderFactory.createLineBorder(ColorPalette.textColor));
        for(int i = 0; i < scoreCells.size(); i++){
            scoreCells.get(i).setBorder(BorderFactory.createLineBorder(ColorPalette.textColor));
            if(i == scoreCells.size() - 1){
                scoreCells.get(i).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, ColorPalette.textColor));
            }
        }
    }

    public void updateScoreCell(int newScore){
        scoreCells.get(0).setText(Integer.toString(newScore));
        scoreCells.get(0).setForeground(Color.BLACK);
    }

    public void updateScoreCell(int newScore, int scoreColumn){
        scoreCells.get(scoreColumn - 1).setText(Integer.toString(newScore));
        scoreCells.get(0).setForeground(Color.BLACK);
    }

    public void updateScoreCell(String newScoreText, int scoreColumn){
        scoreCells.get(scoreColumn - 1).setText(newScoreText);
        scoreCells.get(0).setForeground(Color.BLACK);
    }

    public void updateScoreCell(int newScore, Color scoreColor){
        scoreCells.get(0).setText(Integer.toString(newScore));
        scoreCells.get(0).setForeground(scoreColor);
    }

    public void updateScoreCell(int newScore, int scoreColumn, Color scoreColor){
        scoreCells.get(scoreColumn - 1).setText(Integer.toString(newScore));
        scoreCells.get(scoreColumn - 1).setForeground(scoreColor);
    }

    public void setLineBackground(Color backgroundColor){
        nameCell.setOpaque(true);
        nameCell.setBackground(backgroundColor);
        scoringExplanationCell.setOpaque(true);
        scoringExplanationCell.setBackground(backgroundColor);
        for(int i = 0; i < scoreCells.size(); i++){
            scoreCells.get(i).setOpaque(true);;
            scoreCells.get(i).setBackground(backgroundColor);
        }
    }

    public GameLabel getCell(int column){
        if(column == 1){
            return nameCell;
        } else if(column == 2){
            return scoringExplanationCell;
        } else{
            return scoreCells.get(column - 3);
        }
    }

    public void setBottomBorder(){
        nameCell.setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, ColorPalette.textColor));
        scoringExplanationCell.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, ColorPalette.textColor));
        for(int i = 0; i < scoreCells.size(); i++){
            scoreCells.get(i).setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, ColorPalette.textColor));
            if(i == scoreCells.size() - 1){
                scoreCells.get(i).setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, ColorPalette.textColor));
            }
        }
    }

}
