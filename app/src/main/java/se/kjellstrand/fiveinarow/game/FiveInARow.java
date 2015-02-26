package se.kjellstrand.fiveinarow.game;

import android.util.Log;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {
    private static final String TAG = FiveInARow.class.getCanonicalName();
    private AbstractPlayer p1;
    private AbstractPlayer p2;

    private AbstractPlayer currentPlayer;

    private Board b;

    public FiveInARow(Board _b, AbstractPlayer _p1, AbstractPlayer _p2) {
        b = _b;
        p1 = _p1;
        p2 = _p2;
        currentPlayer = p1;
    }

    public AbstractPlayer advanceGame() {
        Move move = currentPlayer.getNextMove(b);
        int state = b.makeMove(move, currentPlayer);

        if (state == BoardState.WIN) {
            Log.d(TAG, "winning move, x:" + move.x + " y: " + move.y);
            return currentPlayer;
        }

        if (currentPlayer != p1) {
            currentPlayer = p1;
        } else {
            currentPlayer = p2;
        }
        return null;
    }

    public void printBoard() {
        b.print();
    }
}
