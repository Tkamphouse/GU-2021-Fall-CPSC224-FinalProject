package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class for the introduction window. That is,
 * the first window that appears when starting
 * the game.
 * 
 * @author Jonathan Smoley
 * @version 2.0 4/24/2022
 */
public class TitleWindow extends JPanel{
    JFrame window;
    NameWindow nextPanel;
    JPanel contentCenter;
    JPanel contentSouth;
    JLabel yahtzeeTitle;
    JLabel boxLabel;
    Integer[] numPlayerOptions = {1, 2, 3, 4, 5, 6, 7, 8};
    JComboBox<Integer> numPlayerDropBox = new JComboBox<>(numPlayerOptions);
    JButton toNameWindow;
    DefaultListCellRenderer renderer;

    /**
     * Default constructor for the TitleWindow class.
     * Creates and adds components to the panel.
     * 
     * @see Yahtzee.java
     */
    public TitleWindow(){
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(800, 200));
        add(spacer, BorderLayout.NORTH);

        setCenterContent();
        add(contentCenter, BorderLayout.CENTER);

        setSouthContent();
        add(contentSouth, BorderLayout.SOUTH);
    }

    /**
     * Creates and add components to the center panel
     * of a title window.
     * 
     * @see TitleWindow constructor
     */
    private void setCenterContent(){
        contentCenter = new JPanel();
        contentCenter.setLayout(new BoxLayout(contentCenter, BoxLayout.Y_AXIS));
        contentCenter.setPreferredSize(new Dimension(450, 300));

        yahtzeeTitle = new JLabel("Yahtzee");
        yahtzeeTitle.setFont(new Font("Serif", Font.PLAIN, 120));
        yahtzeeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentCenter.add(yahtzeeTitle);
    
        numPlayerDropBox.setFont(new Font("Serif", Font.PLAIN, 30));
        numPlayerDropBox.setMaximumSize(new Dimension(150, 30));
        renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        numPlayerDropBox.setRenderer(renderer);
        numPlayerDropBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentCenter.add(numPlayerDropBox);

        boxLabel = new JLabel("Players");
        boxLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        boxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentCenter.add(boxLabel);
    }

    /**
     * Creates and add components to the south panel
     * of a title window.
     * 
     * @see TitleWindow constructor
     */
    private void setSouthContent(){
        contentSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 40));
        contentSouth.setPreferredSize(new Dimension(450, 150));

        toNameWindow = new JButton("Start");
        toNameWindow.setFont(new Font("Serif", Font.PLAIN, 30));
        
        contentSouth.add(toNameWindow);
    }

    /**
     * Sets the behavior when clicking the start button.
     * 
     * @param main the frame that the next panel is in
     * @param titleWindow a reference to this object panel to switch to
     * @see Yahtzee.java
     */
    public void setSwitchButton(JFrame main, TitleWindow titleWindow){
        window = main;
        toNameWindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                NameWindow nameWindow = new NameWindow(window, numPlayerDropBox.getSelectedIndex() + 1);
                nameWindow.setSwitchButton(window, titleWindow);
                nextPanel = nameWindow;
                main.add(nameWindow);
                nameWindow.setVisible(true);
                setVisible(false);
            }
        });
    }

    // to be used for unit testing purposes
    public JPanel getContentCenter() { return contentCenter; }
    public JPanel getContentSouth() { return contentSouth; }
    public JLabel getTitle() { return yahtzeeTitle; }
    public JLabel getBoxLabel() { return boxLabel; }
    public JComboBox<Integer> getNumPlayerDropBox() { return numPlayerDropBox; }
    public JButton getToNameWindow() { return toNameWindow; }
    public JPanel getNamePanel() { return nextPanel; }
}
