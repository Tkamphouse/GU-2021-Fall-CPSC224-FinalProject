package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Class defining the window to add names for the number of 
 * players requested by the user.
 * 
 * @author Jonathan Smoley
 * @version 2.0 4/24/2022
 */
public class NameWindow extends JPanel{
    JFrame window;
    JPanel contentNorth;
    JPanel contentCenter;
    JPanel contentSouth;
    JLabel instructions;
    JLabel constraintNotice;
    ArrayList<JLabel> playerNumLabels;
    ArrayList<JTextField> playerNameCollectors;
    JButton toGameWindow;
    int numPlayers;

    /**
     * Default constructor for the NameWindow class.
     * 
     * @param main the frame object of the program
     * @param numPlayers number of players requested by the user
     * @see TitleWindow.java
     */
    public NameWindow(JFrame main, Integer numPlayers){
        window = main;
        this.numPlayers = numPlayers;

        initPlayerLists();

        setNorthContent();
        add(contentNorth, BorderLayout.NORTH);

        setCenterContent(numPlayers);
        add(contentCenter, BorderLayout.CENTER);

        setSouthContent();
        add(contentSouth, BorderLayout.SOUTH);
    }

    /**
     * Creates a list of components to gather player names.
     * 
     * @see NameWindow constructor
     */
    private void initPlayerLists(){
        playerNumLabels = new ArrayList<>();
        playerNameCollectors = new ArrayList<>();

        for(int i = 0; i < numPlayers; i++){
            JLabel tempJLabel = new JLabel("Player " + (i + 1) + ": ");
            JTextField tempTextField = new JTextField(10);
            playerNumLabels.add(i, tempJLabel);
            playerNameCollectors.add(i, tempTextField);
        }
    }

    /**
     * Creates and adds components to the north panel.
     * 
     * @see NameWindow constructor
     */
    private void setNorthContent(){
        contentNorth = new JPanel();
        contentNorth.setPreferredSize(new Dimension(450, 100));

        instructions = new JLabel("Enter Player Names");
        instructions.setFont(new Font("Serif", Font.PLAIN, 50));
        
        contentNorth.add(instructions);
    }

    /**
     * Creates and adds components to the panel going
     * in the center of the window.
     * 
     * @param playerCount
     * @see NameWindow constructor
     */
    private void setCenterContent(Integer playerCount){
        contentCenter = new JPanel();
        contentCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        contentCenter.setPreferredSize(new Dimension(600, 500));

        for (int i = 0; i < numPlayers; i++) {
            playerNumLabels.get(i).setFont(new Font("Serif", Font.PLAIN, 30));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.insets = new Insets(5, 0, 5, 5);

            contentCenter.add(playerNumLabels.get(i), gbc);

            playerNameCollectors.get(i).setFont(new Font("Serif", Font.PLAIN, 30));

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.insets = new Insets(5, 5, 5, 0);

            contentCenter.add(playerNameCollectors.get(i), gbc);
        }
    }

    /**
     * Sets ups the content at the bottom of the window.
     * 
     * @see NameWindow constructor
     */
    private void setSouthContent(){
        contentSouth = new JPanel();
        BoxLayout layout = new BoxLayout(contentSouth , BoxLayout.Y_AXIS);
        contentSouth.setPreferredSize(new Dimension(450, 150));

        toGameWindow = new JButton("Start Game");
        toGameWindow.setFont(new Font("Serif", Font.PLAIN, 30));

        contentSouth.add(toGameWindow, layout);

        constraintNotice = new JLabel("*must enter a name for each player*");
        constraintNotice.setFont(new Font("Serif", Font.PLAIN, 20));

        contentSouth.add(constraintNotice, layout);
    }

    /**
     * Sets the behavior of the button pressed to start the game.
     * 
     * @param main the frame object of the program
     * @param titleWindow intro panel reference passed to game window
     * @see TitleWindow.java
     */
    public void setSwitchButton(JFrame main, TitleWindow titleWindow){
        toGameWindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                GameWindow gameWindow = new GameWindow(main, titleWindow, playerNameCollectors);
                main.add(gameWindow);
                gameWindow.setVisible(true);
                setVisible(false);
            }
        });
    }

    // to be used for unit testing purposes
    public ArrayList<JLabel> getPlayerNumLabels(){ return playerNumLabels; }
    public ArrayList<JTextField> getPlayerNameCollectors() { return playerNameCollectors; }
    public JLabel getInstructions() { return instructions; }
    public JLabel getConstraintNotice() { return constraintNotice; }
    public JButton getToGameWindow() { return toGameWindow; }
    public JPanel getContentNorth() { return contentNorth; }
    public JPanel getContentCenter() { return contentCenter; }
    public JPanel getContentSouth() { return contentSouth; }
}
