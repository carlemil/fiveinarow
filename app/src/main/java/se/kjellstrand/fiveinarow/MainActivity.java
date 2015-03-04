package se.kjellstrand.fiveinarow;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import se.kjellstrand.fiveinarow.game.AbstractPlayer;
import se.kjellstrand.fiveinarow.game.CloseToLastMovePlayer;
import se.kjellstrand.fiveinarow.game.FiveInARow;
import se.kjellstrand.fiveinarow.game.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game.RandomPlayer;
import se.kjellstrand.fiveinarow.game.RandomSearchPlayer;


public class MainActivity extends ActionBarActivity {

    private static final int BOARD_WIDTH = 8;
    private static final int BOARD_HEIGHT = 8;
    private static final String TAG = MainActivity.class.getCanonicalName();

    AbstractPlayer[] players;
    int[][] winners;
    int numberOfPlayers = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //playTournament();
        playSingleMatch();

        finish();
    }

    private void playSingleMatch() {
        AbstractPlayer p1 = new RandomPlayer(1);
        AbstractPlayer p2 = new RandomSearchPlayer(2);

        FiveInARowBoard b = new FiveInARowBoard(BOARD_WIDTH, BOARD_HEIGHT, p1, p2);
        FiveInARow fir = null;
        fir = new FiveInARow(b);
        AbstractPlayer winningPlayer = fir.playTheGame();
        Log.d(TAG, "Winner is : " + winningPlayer.getPlayerNumber());

        b.print();
    }

    private void playTournament() {
        players = new AbstractPlayer[numberOfPlayers];
        winners = new int[numberOfPlayers][numberOfPlayers];
        players[0] = new RandomPlayer(1);
        players[1] = new CloseToLastMovePlayer(2);
        players[2] = new RandomSearchPlayer(3);

        for (int i = 0; i < 1; i++) {
            for (int p1 = 2; p1 < numberOfPlayers; p1++) {
                for (int p2 = 0; p2 < numberOfPlayers; p2++) {
                    if (p1 == p2) {
                        continue;
                    }
                    FiveInARowBoard b = new FiveInARowBoard(BOARD_WIDTH, BOARD_HEIGHT, players[p1], players[p2]);
                    FiveInARow fir = null;
                    try {
                        fir = new FiveInARow(b);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    AbstractPlayer winningPlayer = fir.playTheGame();
                    Log.d(TAG, "Winner is : " + winningPlayer.getPlayerNumber());
                    if (winningPlayer.getPlayerNumber() == players[p1].getPlayerNumber()) {
                        winners[p1][p2]++;
                    } else {
                        winners[p2][p1]++;
                    }
                    b.print();
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(".\n");
        for (int p = 0; p < numberOfPlayers; p++) {
            sb.append(players[p].getName() + ", ");
        }
        sb.append("\n");
        for (int p1 = 0; p1 < numberOfPlayers; p1++) {
            for (int p2 = 0; p2 < numberOfPlayers; p2++) {
                sb.append(winners[p1][p2] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, sb.toString());
    }
}
