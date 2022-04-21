package edu.gonzaga.ScoreCard;

import javax.swing.*;

import edu.gonzaga.GameComponents.GameLabel;

public class PlayerScoreCardView extends JPanel{

    private ScoreCardView scoreTable;
    private ScoringMenuView scoringMenu;
    private GameLabel possibleScoreExplanation;
    private int height;
    private int width;
 
    public PlayerScoreCardView(ScoreCardView scoreTableView){
        this.setLayout(null);
        scoreTable = scoreTableView;
        scoringMenu = new ScoringMenuView(scoreTable.getNumScoringRows(), scoreTable.getHeight(), scoreTable.getRowHeight());
        possibleScoreExplanation = new GameLabel("Possible scores displayed in grey");
        configureView();
    }

    public void configureView(){
        setComponentSizes();
        setComponentLocations();
        height = scoreTable.getHeight() + possibleScoreExplanation.getHeight();
        width = scoreTable.getWidth() + scoringMenu.getWidth();
        this.setSize(width, height);
        this.add(scoreTable);
        this.add(scoringMenu);
        this.add(possibleScoreExplanation);
    }

    public void setComponentSizes(){
        possibleScoreExplanation.setSize(200, 20);
    }

    public void setComponentLocations(){
        scoreTable.setLocation(0, 0);
        scoringMenu.setLocation(scoreTable.getRowWidth(), 0);
        possibleScoreExplanation.setLocation(0, scoreTable.getHeight());
    }

    public int getSelectedScoringIndex(){
        return scoringMenu.getSelectedIndex();
    }

    public void deleteScoringOption(int rowToDelete){
        scoringMenu.deleteScoringOption(rowToDelete - 1);
    }

    public void hideScoringMenu(){
        scoringMenu.setVisible(false);
    }

    public void revealScoringMenu(){
        scoringMenu.setVisible(true);
    }
    
    public ScoringMenuView getScoringMenu(){
        return scoringMenu;
    }

}
