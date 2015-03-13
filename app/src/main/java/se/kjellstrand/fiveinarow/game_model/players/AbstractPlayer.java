package se.kjellstrand.fiveinarow.game_model.players;

import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;

/**
 * Created by carlemil on 2015-02-25.
 */
public abstract class AbstractPlayer {

    private static final String TAG = AbstractPlayer.class.getCanonicalName();

    private static int players = 0;

    private final int playerNumber;

    public AbstractPlayer(int _playerNumber) {
        if (_playerNumber == 0) {
            throw new RuntimeException("playerNumber MUST NOT be 0.");
        }
        playerNumber = _playerNumber;
        //Log.d(TAG, "Player# " + playerNumber + " initialized.");
    }

    abstract public String getName();

    abstract public void getNextMove(FiveInARowBoard board, Move move);

    public int getPlayerNumber() {
        return playerNumber;
    }
}
