/* (C)2021 */
package edu.gonzaga;

/*
* Class for a Die used in Yahtzee.
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/** Class to store the state of a single die. */
public class Die implements Comparable<Die> {

    private Integer sideUp; // Current die 'value' in range 1..numSides
    private Integer numSides;// Sides on the die (should be 1...INF integer)
    private Boolean locked;
    private static final Integer DEFAULT_NUM_SIDES = 6;
    private static final Integer DEFAULT_SIDE_UP = 1;
    private DieView dieView;

    public Die() {
        this.numSides = DEFAULT_NUM_SIDES;
        this.sideUp = DEFAULT_SIDE_UP;
        this.locked = false;
        dieView = new DieView(sideUp);
        dieView.getRadioButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLockedState();
            }
        });
    }

    public Die(Integer numSides) {
        this.numSides = numSides;
        this.sideUp = DEFAULT_SIDE_UP;
        this.locked = false;
        dieView = new DieView(sideUp);
        dieView.getRadioButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLockedState();
            }
        });
    }

    public Die(Integer numSides, Integer startingSide) {
        this.numSides = numSides;
        this.sideUp = startingSide;
        this.locked = true;
        dieView = new DieView(sideUp);
        dieView.getRadioButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateLockedState();
            }
        });
    }

    /** Rolls the die once, getting new random value. */
    public void roll() {
        if(!locked) {
            Random rand = new Random();
            this.sideUp = rand.nextInt(this.numSides) + 1;
            dieView.updateDieAppearance(this.sideUp);
        }
    }

    public boolean updateLockedState() {
        if(dieView.button.isSelected()) {
            locked = true;
        }
        else {
            locked = false;
        }
        return locked;
    }
    
    public void changeLockedState() {
        if (locked == true)
            locked = false;
        else locked = true;
    }

    public void setValue(int newSideUp) {
        this.sideUp = newSideUp;
        dieView.updateDieAppearance(newSideUp);
    }

    public void deselectButton() {
        dieView.button.setSelected(false);
        updateLockedState();
    }

    public void setButtonInvisibility() { dieView.button.setVisible(false); }

    public void setButtonVisibility() { dieView.button.setVisible(true); }

    public JPanel getView() {
        return dieView;
    }

    public int getSideUp() {return sideUp; }

    public int getNumSides() { return numSides; }

    /**
    * Provides the ability to convert the Die object into a string. representation, both with
    * .toString(), but also in System.out.println()
    *
    * @return String of whatever you want this die to say for itself
    */
    @Override
    public String toString() {
        String ret = "";
        ret += this.sideUp.toString();
        return ret;
    }

    /**
    * Makes two dice comparable using <, ==, >, etc. based on sideUp values.
    *
    * @param otherDie The die we're comparing to this one (two objects)
    * @return int -1, 0, 1 for less than, equal, greater than
    */
    @Override
    public int compareTo(Die otherDie) {
        return this.sideUp.compareTo(otherDie.sideUp);
    }
}
