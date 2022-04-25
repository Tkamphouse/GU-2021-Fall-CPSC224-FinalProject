package edu.gonzaga;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


public class HandTest {

    @Test
    void testHandConstructor() {
        int expectedSize = 5;
        int expectedFill = 1;
        Hand hand = new Hand();
        assertEquals(hand.getSize(), expectedSize);
        for (int i = 0; i < hand.getSize(); ++i)
            assertEquals(hand.returnValue(i), expectedFill);
    }

    // @Test
    // void testHandSize() {
    //     Hand hand = new Hand();
    //     int expectedSize = 5;
    //     assertEquals(expectedSize, hand.getSize());
    // }

    @Test
    void testHandRollNew() {
        Hand hand = new Hand();
        Hand hand2 = new Hand();
        int expectedFill = 1;
        int[] expectedArrayFill = {1,1,1,1,1};

        //Test keep all
        for (int i = 0; i < hand.getSize(); ++i)
            hand.getDie(i).changeLockedState();
        hand.RollNewHand();
        for (int i = 0; i < hand.getSize(); ++i)
            assertEquals(hand.returnValue(i), expectedFill);
        //Test keep none
        for (int i = 0; i < hand.getSize(); ++i)
            hand.getDie(i).changeLockedState();
        hand.RollNewHand();
        int[] returnVals = hand.returnAllValues();
        for (int i = 0; i < hand.getSize(); ++i)
            assertNotEquals(returnVals[i], expectedArrayFill[i]);
        //Test keep one
        hand2.getDie(0).changeLockedState();
        for (int i = 0; i < 50; ++i)
        {
            hand2.RollNewHand();
            if (hand2.getDie(0).getSideUp() != 1)
                break;
        }
        assertNotEquals(hand2.returnValue(0), expectedFill);
        for (int i = 1; i < hand2.getSize(); ++i)
            assertEquals(hand2.returnValue(i), expectedFill);
    }


    @Test
    void testHandSum() {
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        Hand hand3 = new Hand();
        Integer expectedSum1 = 15;
        Integer expectedSum2 = 10;
        Integer expectedSum3 = 25;
        for (int i = 0; i < hand1.getSize(); ++i)
            hand1.getDie(i).setValue(3);
        assertEquals(hand1.sum(), expectedSum1);
        for (int i = 0; i < hand1.getSize(); ++i)
            hand2.getDie(i).setValue(2);
        assertEquals(hand2.sum(), expectedSum2);
        for (int i = 0; i < hand1.getSize(); ++i)
            hand3.getDie(i).setValue(5);
        assertEquals(hand3.sum(), expectedSum3);
    }

    @Test
    void testHandToString() {
        Hand hand = new Hand();
        String expectedOutput = "Hand: 1 | 1 | 1 | 1 | 1 | ";
        String output = hand.toString();
        assertEquals(output, expectedOutput);
    }

    @Test
    void testHandIsTurnOver() {
        Hand hand = new Hand();
        hand.RollNewHand();
        assertEquals(false, hand.isTurnOver());
        hand.RollNewHand();
        assertEquals(false, hand.isTurnOver());
        hand.RollNewHand();
        assertEquals(true, hand.isTurnOver());
    }

    @Test
    void testHandSize() {
        Hand hand = new Hand();
        int expectedSize = 5;
        assertEquals(expectedSize, hand.getSize());
    }

    @Test
    void testHandReset() {
        Hand hand = new Hand();
        Hand expectedHand = new Hand();
        int expectedFill = 3;
        for (int i = 0; i < hand.getSize(); ++i)
            hand.getDie(i).setValue(3);
        for (int i = 0; i < hand.getSize(); ++i)
            assertEquals(hand.returnValue(i), expectedFill);
        hand.reset();
        for (int i = 0; i < hand.getSize(); ++i)
            assertEquals(hand.returnValue(i), expectedHand.returnValue(i));
    }


}


