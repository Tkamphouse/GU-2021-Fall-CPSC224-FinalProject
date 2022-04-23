package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import edu.gonzaga.Windows.*;

/** Main program class for launching Yahtzee program. */
public class Yahtzee {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Yahtzee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        TitleWindow titleWindow = new TitleWindow();
        frame.add(titleWindow, BorderLayout.CENTER);
        titleWindow.setSwitchButton(frame);
        //JTextField textField1 = new JTextField("Jane");
        //JTextField textField2 = new JTextField("John");
        /*ArrayList<JTextField> nameCollectors = new ArrayList<>() {{
            add(new JTextField("Jane"));
            add(new JTextField("John"));
        }};
        GameWindow gameWindow = new GameWindow(nameCollectors);
        frame.add(gameWindow);*/
        frame.setVisible(true);
    }
}
