package edu.gonzaga.ScoreCard;

import java.util.ArrayList;
import edu.gonzaga.Player;

public class LeaderBoard extends ScoreCard {

    LeaderBoardView view;

    public LeaderBoard(ArrayList<Player> players) {
        super("Leaderboard", 6, players.size());
        view = new LeaderBoardView(super.getView(), players);
    }

    public LeaderBoardView getLeaderBoardView() { return view; }
    
}
