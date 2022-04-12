/* (C)2022 */
package edu.gonzaga.Screens;

import javax.swing.*;
import edu.gonzaga.GameComponents.GameButton;
import java.awt.*;
import java.awt.event.*;

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

/** Class for creating an intro screen the user will use to navigate to gameplay screen and settings screen */
public class IntroScreen extends JPanel{

    private static final Color backgroundColor = new Color(228, 216, 200);
    private static final Color lightRed = new Color(177, 107, 73);
    private static final Color darkRed = new Color(150, 70, 47);
    private static final Image wallpaper = new ImageIcon(System.getProperty("user.dir") + "\\src\\media\\Background.png").getImage();

    private JPanel introScreenContent;
    private JLabel yahtzeeTitle;
    private JPanel footer;
    private GameButton goToSettings;
    private GameButton goToGame;

    public IntroScreen(Boolean visibility){
        super(new BorderLayout());
        this.setBounds(0, 0, 800, 800);
        introScreenContent = new JPanel();
        introScreenContent.setLayout(null);
        introScreenContent.setOpaque(false);
        yahtzeeTitle = new JLabel("Yahtzee", SwingConstants.CENTER);
        yahtzeeTitle.setBounds(0, 265, 800, 110);
        yahtzeeTitle.setFont(new Font("Castellar", Font.PLAIN, 105));
        yahtzeeTitle.setOpaque(true);
        yahtzeeTitle.setBackground(lightRed);
        yahtzeeTitle.setForeground(backgroundColor);
        goToSettings = new GameButton("Settings", 6);
        goToSettings.setPreferredSize(new Dimension(150, 30));
        goToGame = new GameButton("Play", 6);
        goToGame.setPreferredSize(new Dimension(150, 30));
        footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 40));
        footer.setPreferredSize(new Dimension(800, 150));
        footer.setBackground(darkRed);
        footer.add(goToSettings);
        footer.add(goToGame);
        introScreenContent.add(yahtzeeTitle);
        this.add(introScreenContent);
        this.add(footer, BorderLayout.SOUTH);
        this.setVisible(visibility);
    }

    /**
    * uses wallpaper to paint Screen background
    *
    * @param g Graphics value to use graphics
    */
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(wallpaper, 0, 0, null);
    }

    /**
    * sets up goToSettings button and goToGame button so that the user can use them to navigate to the settings Screen
    * and game screen respectively
    *
    * @param settings JPanel value that represents the settings window that the goToSettings button can navigate to 
    * @param game GameScreem vale that represents the game window that the goToGame button can navigate to 
    */
    public void setSwitchButtons(JPanel settings, GameScreen game){
        goToSettings.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                settings.setVisible(true);
                setVisible(false);
            }
        });
        goToGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                game.setBeginningGameStats();
                game.setVisible(true);
                setVisible(false);
            }
        });
    }
    
}
