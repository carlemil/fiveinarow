package se.kjellstrand.fiveinarow.game;

import android.app.Activity;
import android.test.AndroidTestCase;
import android.util.Log;

import junit.framework.TestCase;

public class RandomSearchPlayerTest extends AndroidTestCase {

    private static final String TAG = "RandomSearchPlayerTest";

    public void testGetNextMove() throws Exception {

        AbstractPlayer p1 = new RandomSearchPlayer(1);

        AbstractPlayer p2 = new RandomPlayer(2);

        FiveInARowBoard b = new FiveInARowBoard(8,8,p1,p2);

        Move move = new Move(4,4);
        for(int i=2;i<6;i++) {
            move.x = i;
            b.makeMove(move, p1);
        }

        move = p1.getNextMove(b);

        Log.d(TAG, "final move: " + move);
        //assertEquals(true, false);
    }
}