package edu.gonzaga;

import java.util.ArrayList;

import edu.gonzaga.Views.*;

public class Hand {
    
    HandView view;
    int numDice;
    ArrayList<Die> dice;

    public Hand(int size){
        this.numDice = size;
        for(int i = 0; i < numDice; i++){
            dice.add(new Die());
        }
    }

}
