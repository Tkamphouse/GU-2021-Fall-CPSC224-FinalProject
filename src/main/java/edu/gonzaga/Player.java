package edu.gonzaga;

public class Player {

    ScoreCard playerScoreCard;
    String name;
    Boolean playing = false;

    public Player(String name){
        playerScoreCard = new ScoreCard();
        this.name = name;
    }
    
}
