package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import edu.gonzaga.ScoreCard.ScoreCard;

public class ScoreCardTest {
    
    @Test
    void testGetFinalScore(){
        ScoreCard dummyCard = new ScoreCard("Jane", 6, 1);
        int actualFinalScore = dummyCard.getTotalLines().get(5).getScore(1);
        int expectedFinalScore = 0;
        assertEquals(expectedFinalScore, actualFinalScore);
    }

    

}
