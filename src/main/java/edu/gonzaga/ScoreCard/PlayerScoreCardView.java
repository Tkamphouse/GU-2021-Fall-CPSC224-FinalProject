package edu.gonzaga.ScoreCard;

import javax.swing.*;
import java.awt.*;
import edu.gonzaga.GameComponents.*;

public class PlayerScoreCardView extends JPanel{

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
    private Color titleColor;
    private Color columnHeadersColor;
    private Color possibleScoreColor;
    private ScoreCardView scoreTable;
    private ScoringMenuView scoringMenu;
    private GameLabel possibleScoreExplanation;
    private int height;
    private int width;
    private int id;
 
    public PlayerScoreCardView(ScoreCardView scoreTableView, int id){
        this.setLayout(null);
        this.id = id;
        scoreTable = scoreTableView;
        scoringMenu = new ScoringMenuView(scoreTable.getNumScoringRows(), scoreTable.getHeight(), scoreTable.getRowHeight());
        possibleScoreExplanation = new GameLabel("Possible scores displayed in grey");
        configureView();
    }

    public void configureView(){
        setColorSettings();
        setComponentSizes();
        setComponentLocations();
        scoreTable.color(titleColor, columnHeadersColor, possibleScoreColor);
        height = scoreTable.getHeight() + possibleScoreExplanation.getHeight() + 10;
        width = scoreTable.getWidth() + scoringMenu.getWidth();
        this.setSize(width, height);
        this.add(scoreTable);
        this.add(scoringMenu);
        this.add(possibleScoreExplanation);
    }

    public void setColorSettings(){
        titleColor = possibleColors[id-1][2];
        columnHeadersColor = possibleColors[id-1][1];
        possibleScoreColor = possibleColors[id-1][0];
    }

    public void setComponentSizes(){
        possibleScoreExplanation.setSize(250, 20);
    }

    public void setComponentLocations(){
        scoreTable.setLocation(0, 0);
        scoringMenu.setLocation(scoreTable.getRowWidth(), 0);
        possibleScoreExplanation.setLocation(0, scoreTable.getHeight() + 10);
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
