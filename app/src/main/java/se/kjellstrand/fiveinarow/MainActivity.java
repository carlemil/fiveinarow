package se.kjellstrand.fiveinarow;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import se.kjellstrand.fiveinarow.game.AbstractPlayer;
import se.kjellstrand.fiveinarow.game.Board;
import se.kjellstrand.fiveinarow.game.CloseToLastMovePlayer;
import se.kjellstrand.fiveinarow.game.FiveInARow;
import se.kjellstrand.fiveinarow.game.RandomPlayer;


public class MainActivity extends ActionBarActivity {

    private static final int BOARD_WIDTH = 12;
    private static final int BOARD_HEIGHT = 12;
    private static final String TAG = MainActivity.class.getCanonicalName();

    AbstractPlayer[] players;
    int[][] winners;
    int numberOfPlayers = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        players = new AbstractPlayer[numberOfPlayers];
        winners = new int[numberOfPlayers][numberOfPlayers];
        players[0] = new RandomPlayer();
        players[1] = new CloseToLastMovePlayer();
        players[2] = new CloseToLastMovePlayer();

        for (int i = 0; i < 1000; i++) {
            for (int p1 = 0; p1 < numberOfPlayers; p1++) {
                for (int p2 = 0; p2 < numberOfPlayers; p2++) {
                    if (p1 == p2) {
                        continue;
                    }
                    Board b = new Board(BOARD_WIDTH, BOARD_HEIGHT);
                    FiveInARow fir = new FiveInARow(b, players[p1], players[p2]);
                    AbstractPlayer winningPlayer = fir.playTheGame();
                    //Log.d(TAG, "Winner is : " + winningPlayer.playerNumber);
                    if (winningPlayer.playerNumber == players[p1].playerNumber) {
                        winners[p1][p2]++;
                    } else {
                        winners[p2][p1]++;
                    }
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        Log.d(TAG, "\n");
        for (int p1 = 0; p1 < numberOfPlayers; p1++) {
            for (int p2 = 0; p2 < numberOfPlayers; p2++) {
                sb.append(winners[p1][p2] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, sb.toString());

        finish();
    }
}
