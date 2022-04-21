package edu.gonzaga;

import javax.swing.*;
import java.awt.*;
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
        frame.setVisible(true);
    }
}
