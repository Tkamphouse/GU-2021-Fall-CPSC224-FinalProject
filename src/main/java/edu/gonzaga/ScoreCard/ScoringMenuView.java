package edu.gonzaga.ScoreCard;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.util.ArrayList;
import edu.gonzaga.GameComponents.GameLabel;

public class ScoringMenuView extends JPanel{

    private ArrayList<JRadioButton> buttons = new ArrayList<>();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private GameLabel menuExplanation;
    private int rowHeight;
    private int width = 150;
    private int height;

    public ScoringMenuView(int numScoringRows, int scoreCardLength, int rowHeight){
        this.setLayout(null);
        this.height = scoreCardLength;
        this.rowHeight = rowHeight;
        this.setSize(width, height);
        for(int i = 0; i < numScoringRows; i++){
            buttons.add(new JRadioButton());
            buttonGroup.add(buttons.get(i));
        }
        menuExplanation = new GameLabel("<html>Select Row to<br>Record Score<html>");
        configurePanel();
    }

    public void configurePanel(){
        setComponentSizes();
        setComponentLocations();
        AddComponents();
    }

    public void setComponentSizes(){
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setSize(20, 15);
        }
        menuExplanation.setSize(100, rowHeight * 2);
    }

    public void setComponentLocations(){
        menuExplanation.setLocation(10, 0);
        for(int i = 0; i < 6; i++){
            buttons.get(i).setLocation(10, (5 + menuExplanation.getHeight() + rowHeight * i));
        }
        for(int i = 6; i < buttons.size(); i++){
            buttons.get(i).setLocation(10, (5 + menuExplanation.getHeight() + (4 * rowHeight) + rowHeight * i));
        }
    }

    public void AddComponents(){
        this.add(menuExplanation);
        for(int i = 0; i < buttons.size(); i++){
            this.add(buttons.get(i));
        }
    }

    public int getSelectedIndex(){
        for(int i = 0; i < buttons.size(); i++){
            if(buttons.get(i).isSelected()){
                return i;
            }
        }
        return -1;
    }

    public void deleteScoringOption(int indexToHide){
        buttons.get(indexToHide).setVisible(false);
    }

    public ArrayList<JRadioButton> getButtons(){
        return buttons;
    }
    
}
