package edu.gonzaga.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public TitleWindow(){
        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(800, 200));
        add(spacer, BorderLayout.NORTH);

        setCenterContent();
        add(contentCenter, BorderLayout.CENTER);

        setSouthContent();
        add(contentSouth, BorderLayout.SOUTH);
    }

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

    private void setSouthContent(){
        contentSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 90, 40));
        contentSouth.setPreferredSize(new Dimension(450, 150));

        toNameWindow = new JButton("Start");
        toNameWindow.setFont(new Font("Serif", Font.PLAIN, 30));
        
        contentSouth.add(toNameWindow);
    }

    public void setSwitchButton(JFrame main){
        window = main;
        toNameWindow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                NameWindow nameWindow = new NameWindow(window, numPlayerDropBox.getSelectedIndex() + 1);
                nameWindow.setSwitchButton(window);
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
