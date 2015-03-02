package se.kjellstrand.fiveinarow;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import se.kjellstrand.fiveinarow.game.AbstractPlayer;
import se.kjellstrand.fiveinarow.game.Board;
import se.kjellstrand.fiveinarow.game.CloseToLastMovePlayer;
import se.kjellstrand.fiveinarow.game.FiveInARow;
import se.kjellstrand.fiveinarow.game.GameState;
import se.kjellstrand.fiveinarow.game.RandomPlayer;


public class MainActivity extends ActionBarActivity {

    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 10;
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Board b = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        AbstractPlayer p1 = new RandomPlayer();
        AbstractPlayer p2 = new CloseToLastMovePlayer();

        FiveInARow fir = new FiveInARow(b, p1, p2);

        GameState state = GameState.UNDEFINED;
        while (state == GameState.UNDEFINED) {
            state = fir.advanceGame();
        }
        fir.printBoard();
        if (state == GameState.WIN) {
            Log.d(TAG, "Winner is : " + fir.getCurrentPlayer().playerNumber);
        } else if (state == GameState.LOSS) {
            Log.d(TAG, "Looser is : " + fir.getCurrentPlayer().playerNumber);
        }

        finish();
    }

}
