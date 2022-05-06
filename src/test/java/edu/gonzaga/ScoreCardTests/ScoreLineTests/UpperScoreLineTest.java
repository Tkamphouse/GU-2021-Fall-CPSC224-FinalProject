package edu.gonzaga.ScoreCardTests.ScoreLineTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.gonzaga.*;
import edu.gonzaga.ScoreCard.ScoreLines.UpperScoreLine;

public class UpperScoreLineTest {
    
    @Test
    void testOnesRowAllOnesCalcScore(){
        UpperScoreLine dummyLine = new UpperScoreLine("Dummy Name", "Dummy Explanation", 1, 1);
        Hand dummyHand = new Hand();
        dummyLine.calculateScore(dummyHand);
        int expectedScore = 5;
        int actualScore = dummyLine.getPossibleScore(1);
        assertEquals(expectedScore, actualScore);
    }

}
