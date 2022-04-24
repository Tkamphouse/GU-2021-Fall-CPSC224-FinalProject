package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.event.*;
import edu.gonzaga.*;
import edu.gonzaga.ScoreCard.*;
import java.util.ArrayList;

public class GameWindow extends JPanel{

    JLabel currentPlayerName = new JLabel();
    JPanel currentPlayerScoreCardView = new JPanel();
    JButton toEndScreen;
    JButton endTurn;
    Hand gameHand;
    ArrayList<Player> players;
    Player currentPlayer;

    public GameWindow(ArrayList<JTextField> nameCollectors){
        //System.out.println("1");
        this.setLayout(null);
        this.setSize(800, 800);
        gameHand = new Hand();
        //System.out.println("2");
        toEndScreen = new JButton("Finish Game");
        endTurn = new JButton("End Turn");
        initPlayers(nameCollectors);
        //System.out.println("3");
        players.get(0).setPlaying();
        setRollButtonMechanics();
        //System.out.println("4");
        setEndTurnButton();
        //System.out.println("5");
        configureView();
    }

    public void initPlayers(ArrayList<JTextField> nameCollectors){
        players = new ArrayList<>();
        for (int i = 0; i < nameCollectors.size(); i++){
            Player newPlayer = new Player(nameCollectors.get(i).getText(), endTurn);
            players.add(newPlayer);
        }
        currentPlayer = players.get(0);
        currentPlayerName.setText(currentPlayer.getName() + "'s Turn");
    }

    //to be set after all screens created in Yahtzee
    public void setSwitchButton(JPanel endGame){
        toEndScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                endGame.setVisible(true);
                setVisible(false);
            }
        });
    }

    /*public void updatePlayerLabel(Player currentPlayer){
        currentPlayerName.setText(currentPlayer.getName() + "'s Turn");
    }*/

    public void getPlayerScoreCards(){
        int scoreCardWidth = players.get(0).getView().getWidth();
        int scoreCardHeight = players.get(0).getView().getHeight();
        currentPlayerScoreCardView.setSize(scoreCardWidth, scoreCardHeight);
        currentPlayerScoreCardView.setLayout(null);
        for(int i = 0; i < players.size(); i++){
            players.get(i).getView().setLocation(0, 0);
            currentPlayerScoreCardView.add(players.get(i).getView());
        }
    }

    public void incrementCurrentPlayer(){
        //System.out.println(players.size());
        if(players.size() > 1){
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).checkifPlaying()){
                    if(checkCurrentPlayerLastPlayer()){
                        players.get(0).setPlaying();
                        currentPlayer = players.get(0);
                    }else{
                        players.get(i + 1).setPlaying();
                        currentPlayer = players.get(i + 1);
                    }
                    players.get(i).setNotPlaying();
                    i = players.size() - 1;
                }
            }
            currentPlayerName.setText(currentPlayer.getName() + "'s Turn");
            currentPlayer.resetPossibleScores();
        }
    }

    public void setRollButtonMechanics(){
        gameHand.getRollButton().addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                gameHand.RollNewHand();
                currentPlayer.showPossibleScores(gameHand);
                if(gameHand.isTurnOver()){
                    currentPlayer.revealScoringMenu();
                }
            }
        });
    }

    public void setEndTurnButton(){
        endTurn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                currentPlayer.recordScore();
                currentPlayer.hideScoringMenu();
                if(checkCurrentPlayerLastPlayer() && currentPlayer.checkScoreCardFull()){
                    toEndScreen.setVisible(true);
                }else{
                    incrementCurrentPlayer();
                    gameHand.reset();
                }
                endTurn.setVisible(false);
            }
        });
    }

    public void setToEndScreen(){
        toEndScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                EndGameWindow endGameWindow = new EndGameWindow();
                //to do
            }
        });
    }

    public boolean checkCurrentPlayerLastPlayer(){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).checkifPlaying()){
                if(i == players.size() - 1){
                    return true;
                }
            }
        }
        return false;
    }

    public void setComponentSizes(){
        currentPlayerName.setSize(150, 30);
        toEndScreen.setSize(150, 30);
        endTurn.setSize(150, 30);
    }

    public void setComponentLocations(){
        currentPlayerName.setLocation(10, 10);
        currentPlayerScoreCardView.setLocation(10, currentPlayerName.getHeight() + 20);
        gameHand.getAppearance().setLocation(currentPlayerScoreCardView.getWidth() + 20, currentPlayerName.getHeight() + 20);
        toEndScreen.setLocation(10, currentPlayerName.getHeight() + currentPlayerScoreCardView.getHeight() + 30);
        endTurn.setLocation(170, currentPlayerName.getHeight() + currentPlayerScoreCardView.getHeight() + 30);
    }

    public void addComponents(){
        this.add(currentPlayerName);
        this.add(currentPlayerScoreCardView);
        this.add(gameHand.getAppearance());
        this.add(toEndScreen);
        this.add(endTurn);
    }

    public void configureView(){
        toEndScreen.setVisible(false);
        endTurn.setVisible(false);
        gameHand.hideDiceButtons();
        getPlayerScoreCards();
        setComponentSizes();
        setComponentLocations();
        addComponents();
    }
    
}

/*
 Roll Button Pressed: 
 - show possible scores
 - tell hand to roll
 - check if final roll, if so, then tell player to make scoring menu visible

 Line Button Pressed:
 - tell scoreline to record possible score in actual score
 - make finish turn button visible

 Finish Turn Button Pressed: (part of GameWindow,pass to player when a possible score is pressed)
 - record current player's scorecard
 - switch to next player (if there is one and if scores still need to be filled) which 
    could be the first player in the list (so set to players.get(0)), otherwise, switch to
    end screen
- reset the hand (roll count) to 0 and hide the scoring menu
 */