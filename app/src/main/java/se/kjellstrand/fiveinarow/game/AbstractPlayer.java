package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public abstract class AbstractPlayer {

    private static final String TAG = AbstractPlayer.class.getCanonicalName();

    private static int players = 0;

    public final int playerNumber;

    public AbstractPlayer(int _playerNumber) {
        if (_playerNumber == 0) {
            throw new RuntimeException("playerNumber MUST NOT be 0.");
        }
        playerNumber = _playerNumber;
        //Log.d(TAG, "Player# " + playerNumber + " initialized.");
    }

    abstract public String getName();

    abstract public Move getNextMove(FiveInARowBoard board);
}
