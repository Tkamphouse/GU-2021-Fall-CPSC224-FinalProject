/* (C)2021 */
package edu.gonzaga;

/*
* Class for a Die used in Yahtzee.
*/

import java.util.Random;

/** Class to store the state of a single die. */
public class Die implements Comparable<Die> {

    private static Integer sideUp; // Current die 'value' in range 1..numSides
    private static Integer numSides; // Sides on the die (should be 1...INF integer)
    private static Boolean locked;
    private static final Integer DEFAULT_NUM_SIDES = 6;
    private static final Integer DEFAULT_SIDE_UP = 1;
    private DieView2 viewDie;

    public void setSideUp(int chosenSide) { sideUp = chosenSide; }
    public static int getSideUp(){
        return sideUp;
    }
    public static int getNumSides() { return numSides; }
    public static boolean getLockedStatus() { return locked; }

    public Die() {
        this.numSides = DEFAULT_NUM_SIDES;
        this.sideUp = DEFAULT_SIDE_UP;
        this.locked = true;
        viewDie = new DieView2();
        viewDie.configureView();
        Die[] die = new Die[6];
    }

    public Die(Integer numSides) {
        this.numSides = numSides;
        this.sideUp = DEFAULT_SIDE_UP;
        this.locked = true;
        viewDie = new DieView2();
        viewDie.configureView();
        Die[] die = new Die[6];
    }

    public Die(Integer numSides, Integer startingSide) {
        this.numSides = numSides;
        this.sideUp = startingSide;
        this.locked = true;
        viewDie = new DieView2();
        viewDie.configureView();
        Die[] die = new Die[6];
    }

    /** Rolls the die once, getting new random value. */
    public void roll() {
        Random rand = new Random();
        this.sideUp = rand.nextInt(this.numSides) + 1;

        for(int i = 0; i < 6; i++) {
            if(!viewDie.button.isSelected()) {
                unlock();
                this.sideUp = rand.nextInt(this.numSides) + 1;
                viewDie.updateDieAppearance(getSideUp());
            }
        }
        /************
        needs to call a lock state ie lock(i) for when all three rolls are complete, i think this will be in hand?
        ************/
    }

    public void lock(int i) {
        locked = true;
        updateLockedState(i);
    }

    public void unlock() {
        locked = false;
    }

    public void updateLockedState(int i) {
        if(locked) {
            viewDie.button.setVisible(false);
        } else {
            viewDie.button.setVisible(true);
        }
    }

    public void setValue(int dieFace) {
        viewDie.updateDieAppearance(1);
    }


    /**
    * Provides the ability to convert the Die object into a string. representation, both with
    * .toString(), but also in System.out.println()
    *
    * @return String of whatever you want this die to say for itself
    */
    @Override
    public String toString() {
        String ret = "";
        // ret += "Die: " + this.sideUp.toString() + " of " + this.numSides.toString() + " sides";
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
        int myvar_aslkdfj = 2;
        return this.sideUp.compareTo(otherDie.sideUp);
    }
}
