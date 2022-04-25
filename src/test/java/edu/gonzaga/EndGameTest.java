package edu.gonzaga;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.swing.*;
import edu.gonzaga.Windows.*;
import java.util.ArrayList;

public class EndGameTest {
    @Test
    public void testEndPanelInit() {
        ArrayList<Player> players = new ArrayList<>(1);
        EndGameWindow test = new EndGameWindow(players);
        Assertions.assertNotNull(test);
    }

    @Test
    public void testLeaderBoardInit() {
        ArrayList<Player> players = new ArrayList<>(1);
        EndGameWindow test = new EndGameWindow(players);
        Assertions.assertNotNull(test.getLeaderBoard());
    }

    @Test
    public void testButtonInit() {
        ArrayList<Player> players = new ArrayList<>(1);
        EndGameWindow test = new EndGameWindow(players);
        Assertions.assertNotNull(test.getToTitleScreen());
    }
}
