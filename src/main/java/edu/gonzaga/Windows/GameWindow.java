package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.event.*;
import edu.gonzaga.*;
import java.util.ArrayList;

/**
 * Class to create and update the actual game mechanics
 * while user(s) are playing the game.
 * 
 * @author Anna Cardinal
 * @author Jonathan Smoley
 * @version 2.0 4/24/2022
 */
public class GameWindow extends JPanel{

    JLabel currentPlayerName = new JLabel();
    JPanel currentPlayerScoreCardView = new JPanel();
    JButton toEndScreen;
    JButton endTurn;
    Hand gameHand;
    ArrayList<Player> players;
    Player currentPlayer;

    /**
     * Default constructor for the GameWindow class.
     * 
     * @param window the frame object of the program
     * @param titleWindow reference to the intro window
     * @param nameCollectors an arraylist of textfields holding player names
     */
    public GameWindow(JFrame window, TitleWindow titleWindow, ArrayList<JTextField> nameCollectors){
        this.setLayout(null);
        this.setSize(800, 800);
        gameHand = new Hand();
        toEndScreen = new JButton("Finish Game");
        endTurn = new JButton("End Turn");
        initPlayers(nameCollectors);
        players.get(0).setPlaying();
        setRollButtonMechanics();
        setEndTurnButton();
        setToEndScreen(window, titleWindow, players);
        configureView();
    }

    /**
     * Method to create player objects using the names collected.
     * 
     * @param nameCollectors an arraylist of textfields holding player names
     * @see GameWindow constructor
     */
    public void initPlayers(ArrayList<JTextField> nameCollectors){
        players = new ArrayList<>();
        for (int i = 0; i < nameCollectors.size(); i++){
            Player newPlayer = new Player(nameCollectors.get(i).getText(), endTurn);
            players.add(newPlayer);
        }
        currentPlayer = players.get(0);
        currentPlayerName.setText(currentPlayer.getName() + "'s Turn");
    }

    /*public void updatePlayerLabel(Player currentPlayer){
        currentPlayerName.setText(currentPlayer.getName() + "'s Turn");
    }*/

    /**
     * Method to initialize the scorecard components
     * 
     * @see configureView method
     */
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

    /**
     * Method to "loop" through players and update playing status
     */
    public void incrementCurrentPlayer(){
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

    /**
     * Method to set the behavior of the roll button
     * belonging to the hand
     * 
     * @see GameWindow constructor
     */
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

    /**
     * Method to set the behavior of the end turn button
     * 
     * @see GameWindow constructor
     */
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

    /**
     * Method to set the screen switching behavior once scorecards are full.
     * 
     * @param main the frame object of the program
     * @param titleWindow reference to the intro window
     * @param players an arraylist of player objects
     */
    public void setToEndScreen(JFrame main, TitleWindow titleWindow, ArrayList<Player> players){
        toEndScreen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                EndGameWindow endGameWindow = new EndGameWindow(players);
                endGameWindow.setSwitchButton(titleWindow);
                main.add(endGameWindow);
                endGameWindow.setVisible(true);
                setVisible(false);
            }
        });
    }

    /**
     * Method to check for the last player in the loop.
     * 
     * @return boolean true if last, false if not
     * @see setEndTurnButton method, incrementCurrentPlayer method
     */
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

    /**
     * Method to configure sizes of the components in this panel
     * 
     * @see configureView method
     */
    public void setComponentSizes(){
        currentPlayerName.setSize(150, 30);
        toEndScreen.setSize(150, 30);
        endTurn.setSize(150, 30);
    }

    /**
     * Method to configure where in the panel each component will go
     * 
     * @see configureView method
     */
    public void setComponentLocations(){
        currentPlayerName.setLocation(10, 10);
        currentPlayerScoreCardView.setLocation(10, currentPlayerName.getHeight() + 20);
        gameHand.getAppearance().setLocation(currentPlayerScoreCardView.getWidth() + 20, currentPlayerName.getHeight() + 20);
        toEndScreen.setLocation(10, currentPlayerName.getHeight() + currentPlayerScoreCardView.getHeight() + 30);
        endTurn.setLocation(170, currentPlayerName.getHeight() + currentPlayerScoreCardView.getHeight() + 30);
    }

    /**
     * Method to finally add all components to the current panel
     * 
     * @see configureView method
     */
    public void addComponents(){
        this.add(currentPlayerName);
        this.add(currentPlayerScoreCardView);
        this.add(gameHand.getAppearance());
        this.add(toEndScreen);
        this.add(endTurn);
    }

    /**
     * Method to do most of setting up the game window panel
     * 
     * @see GameWindow constructor
     */
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
