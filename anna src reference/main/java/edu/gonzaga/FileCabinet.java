/* (C)2022 */
package edu.gonzaga;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*
*  This Program lets a user play single player yahtzee using a Graphical User Interface. Game settings are customizable, 
*  allowing the user to pick how many dice are in the game, how many sides the dice have, and how many rolls are allowed per turn
*  CPSC 224, Spring 2022
*  Homework 4
*  Sources: Yahtzee written by Aaron Crandall
*           https://whaa.dev/how-to-overwrite-a-file-in-java - how to use FileWriter
* 
*  @author Anna Cardinal
*  @version v1.4 4/10/2022
*/

/** Class for opening a file and handling contained data */ 
public class FileCabinet{

    private int numSides = 0;
    private int numDice = 0;
    private int numRolls = 0;
    private File file;
    private boolean fileOpen = false;
    
    public FileCabinet (String fileName){
        file = new File(System.getProperty("user.dir") + fileName);
        try{
            Scanner sc = new Scanner(file);
            fileOpen = true;
            numDice = sc.nextInt();
            numSides = sc.nextInt();
            numRolls = sc.nextInt();
            sc.close();
        }catch(FileNotFoundException e){
            System.out.print("no file found"); 
        }
    }

    /**
    * checks to see if file opened successfully
    *
    * @return boolean for if file was successfully opened or not
    */
    public boolean checkIfFileOpened(){
        return fileOpen;
    }

    /** Updates game settings values based on inputs and rewrites data to the opened file */
    public void updateValues(int newNumDice, int newNumSides, int newNumRolls){
        String storedUserInfo = newNumDice + "\n" + newNumSides + "\n" + newNumRolls; // make a string with new values to put in file
        //put new values in file
        try{
            FileWriter writer = new FileWriter(file, false);
            writer.write(storedUserInfo);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        this.internalUpdateValues(newNumSides, newNumDice, newNumRolls); //update FileCabinet object with new data
    }

    /**
    * updates class values based on parsed variables
    *
    * @param newNumSides an int holding new number of sides value
    * @param newNumDice an int holding new number of dice value
    * @param newNumRolls an int holding new nummber of rolls value
    */
    public void internalUpdateValues(int newNumSides, int newNumDice, int newNumRolls){
        this.numSides = newNumSides;
        this.numDice = newNumDice;
        this.numRolls = newNumRolls;
    }

    /**
    * grabs value stored in numSides
    *
    * @return int value stored in numSides
    */
    public int getNumSides(){
        return numSides;
    }

    /**
    * grabs value stored in numDice
    *
    * @return int value stored in numDice
    */
    public int getNumDice(){
        return numDice;
    }

    /**
    * grabs value stored in numRolls
    *
    * @return int value stored in numRolls
    */
    public int getNumRolls(){
        return numRolls;
    }
    
}
