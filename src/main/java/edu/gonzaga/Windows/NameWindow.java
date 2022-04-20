package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    private void setNorthContent(){
        contentNorth = new JPanel();
        contentNorth.setPreferredSize(new Dimension(450, 100));

        instructions = new JLabel("Enter Player Names");
        instructions.setFont(new Font("Serif", Font.PLAIN, 50));
        
        contentNorth.add(instructions);
    }

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

    //to be set after all screens created in Yahtzee
    public void setSwitchButton(JFrame main){
        toGameWindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                GameWindow gameWindow = new GameWindow();
                gameWindow.initPlayers(playerNameCollectors);
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
