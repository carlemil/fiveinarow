package se.kjellstrand.fiveinarow;

import android.util.Log;

import se.kjellstrand.fiveinarow.game.AbstractPlayer;
import se.kjellstrand.fiveinarow.game.Board;
import se.kjellstrand.fiveinarow.game.Move;
import se.kjellstrand.fiveinarow.game.RandomPlayer;

/**
 * Created by carlemil on 2/26/15.
 */
public class BoardTest {

    private static final String TAG = BoardTest.class.getCanonicalName();

    public void boardTest() {
        Board b = new Board(10, 10);
        AbstractPlayer p = new RandomPlayer();
        Move m = new Move();
        m.y = 0;
        for(int x=0;x<5;x++){
            m.x = x;
            b.makeMove(m, p);
            int s = b.getState(m);
            Log.d(TAG, "state: " + s);
        }
    }

}
