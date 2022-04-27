package edu.gonzaga;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DieViewTest {

    @Test
    void testImageCoordinates() {

    }

    @Test
    void testIcon() {
        DieView dieView = new DieView(1);
        ImageIcon testImage;
        testImage = (ImageIcon) dieView.icon.getIcon();
        assertTrue(testImage == dieView.icons[0]);
    }

    @Test
    void testUpdateAppearance() {

    }

    @Test
    void testRadioButton() {

    }

    @Test
    void testFileImport() {
        DieView dieView = new DieView(3);
        dieView.updateDieAppearance(2);
        //?
    }


}
