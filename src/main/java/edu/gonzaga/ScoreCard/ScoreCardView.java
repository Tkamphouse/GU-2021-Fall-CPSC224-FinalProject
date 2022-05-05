package edu.gonzaga.ScoreCard;

import javax.swing.JPanel;
import java.util.ArrayList;

import edu.gonzaga.GameComponents.*;
import edu.gonzaga.ScoreCard.ScoreLines.*;
import java.awt.*;
import javax.swing.*;

public class ScoreCardView extends JPanel{
    
    private GameLabel title;
    private ArrayList<ScoreLineView> scoreLines = new ArrayList<>();
    private ArrayList<ScoreLineView> totalLines = new ArrayList<>();
    private ArrayList<TitleLineView> titleLines = new ArrayList<>();
    private int rowHeight;
    private int width;
    private int height;

    public ScoreCardView(String titleText, ArrayList<ScoreLine> scoreLineModelsRef, ArrayList<ScoreLine> totalLinesRef, ArrayList<TitleLineView> titleLinesRef){
        this.title = new GameLabel(titleText, SwingConstants.CENTER);
        title.setTextBold();
        this.setLayout(null);
        for(int i = 0; i < scoreLineModelsRef.size(); i++){
            scoreLines.add(scoreLineModelsRef.get(i).getView());
        }
        for(int i = 0; i < totalLinesRef.size(); i++){
            totalLines.add(totalLinesRef.get(i).getView());
        }
        titleLines = titleLinesRef;
        this.rowHeight = scoreLines.get(0).getHeight();
        this.width = scoreLines.get(0).getWidth();
        this.height = rowHeight + (rowHeight * scoreLines.size()) + (rowHeight * totalLines.size()) + (rowHeight * titleLines.size());
        configureView();
    }

    public void configureView(){
        this.setSize(width, height);
        setComponentSizes();
        setComponentLocations();
        this.add(title);
        for(int i = 0; i < scoreLines.size(); i++){
            this.add(scoreLines.get(i));
        }
        for(int i = 0; i < totalLines.size(); i++){
            this.add(totalLines.get(i));
        }
        for(int i = 0; i < titleLines.size(); i++){
            this.add(titleLines.get(i));
        }
    }

    public void setComponentSizes(){
        title.setSize(width, rowHeight);
        title.setBorder(BorderFactory.createMatteBorder(3, 3, 1, 3, ColorPalette.textColor));
    }

    public void setComponentLocations(){
        int yCoordinate = 0;
        title.setLocation(0, yCoordinate);
        yCoordinate += rowHeight;
        titleLines.get(0).setLocation(0, yCoordinate);
        yCoordinate += rowHeight;
        for(int i = 0; i < 6; i++){
            scoreLines.get(i).setLocation(0, yCoordinate);
            yCoordinate += rowHeight;
        }
        for(int i = 0; i < 3; i++){
            totalLines.get(i).setLocation(0, yCoordinate);
            yCoordinate += rowHeight;
        }
        titleLines.get(1).setLocation(0, yCoordinate);
        yCoordinate += rowHeight;
        for(int i = 6; i < scoreLines.size(); i++){
            scoreLines.get(i).setLocation(0, yCoordinate);
            yCoordinate += rowHeight;
        }
        for(int i = 3; i < totalLines.size(); i++){
            totalLines.get(i).setLocation(0, yCoordinate);
            yCoordinate += rowHeight;
        }
    }

    public int getRowHeight(){
        return rowHeight;
    }

    public int getRowWidth(){
        return width;
    }

    public int getNumScoringRows(){
        return scoreLines.size();
    }

    public void setUpperScoreCardTitles(ArrayList<String> scoreHeaders){
        titleLines.get(0).setScoreHeaders(scoreHeaders);
    }

    public ArrayList<ScoreLineView> getScoreLineViews(){
        return scoreLines;
    }

    public ArrayList<ScoreLineView> getTotalLineViews(){
        return totalLines;
    }

    public void color(Color titleColor, Color columnHeadersColor, Color possibleScoreColor){
        title.setOpaque(true);
        title.setBackground(titleColor);
        titleLines.get(0).setLineBackground(columnHeadersColor);
        titleLines.get(1).setLineBackground(columnHeadersColor);
    }

    public void colorTitleCell(Color color, int titleRow, int column){
        if(titleRow == 1){
            titleLines.get(0).getCell(column).setBackground(color);
        } else{
            titleLines.get(1).getCell(column).setBackground(color);
        }
    }

    public void colorScoreCell(Color color, int row, int column){
        scoreLines.get(row - 1).getCell(column).setBackground(color);
        scoreLines.get(row - 1).getCell(column).setOpaque(true);
    }

    public void colorTotalCell(Color color, int row, int column){
        totalLines.get(row -1).getCell(column).setBackground(color);
        totalLines.get(row -1).getCell(column).setOpaque(true);
    }

}
