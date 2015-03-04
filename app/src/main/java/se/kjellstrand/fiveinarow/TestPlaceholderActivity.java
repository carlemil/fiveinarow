package se.kjellstrand.fiveinarow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import se.kjellstrand.fiveinarow.game.AbstractPlayer;
import se.kjellstrand.fiveinarow.game.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game.Move;
import se.kjellstrand.fiveinarow.game.RandomPlayer;
import se.kjellstrand.fiveinarow.game.RandomSearchPlayer;

public class TestPlaceholderActivity extends Activity {

    private static final String TAG = "TestPlaceholderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AbstractPlayer p1 = new RandomSearchPlayer(1);

        AbstractPlayer p2 = new RandomPlayer(2);

        FiveInARowBoard b = new FiveInARowBoard(8, 8, p1, p2);

        Move move = new Move(4, 4);
        for (int i = 2; i < 6; i++) {
            move.x = i;
            b.makeMove(move, p1);
        }

        move = p1.getNextMove(b);

        Log.d(TAG, "final move: " + move);
        //assertEquals(true, false);
    }
}
