package se.kjellstrand.fiveinarow.game;

import android.util.Log;

/**
 * Created by carlemil on 2015-02-25.
 */
public abstract class AbstractPlayer {

    private static final String TAG = AbstractPlayer.class.getCanonicalName();

    private static int players = 0;

    public final int playerNumber;

    public AbstractPlayer() {
        playerNumber = ++players;
        Log.d(TAG, "Player# " + playerNumber + " initialized.");
    }

    abstract public Move getNextMove(Board board);
}
