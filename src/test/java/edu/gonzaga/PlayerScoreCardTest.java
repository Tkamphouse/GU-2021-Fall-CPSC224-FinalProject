package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.gonzaga.ScoreCard.PlayerScoreCard;
import edu.gonzaga.ScoreCard.ScoreCard;

public class PlayerScoreCardTest {

    void testGetFinalScoreInitiation(){
        PlayerScoreCard dummyCard = new PlayerScoreCard("John", 6);
        int actualFinalScore = dummyCard.getTotalLines().get(5).getScore(1);
        int expectedFinalScore = 0;
        assertEquals(expectedFinalScore, actualFinalScore);
    }

    @Test
    void testGetFinalScore(){
        PlayerScoreCard dummyCard = new PlayerScoreCard("John", 6);
        for(int i = 1; i <= 13; i++){
            dummyCard.setScore(i, 1, 1);
        }
        //int actualFinalScore = dummyCard.getTotalLines().get(5).getScore(1);
        int actualFinalScore = dummyCard.getFinalScore();
        int expectedFinalScore = 13;
        assertEquals(expectedFinalScore, actualFinalScore);
    }
    
}
