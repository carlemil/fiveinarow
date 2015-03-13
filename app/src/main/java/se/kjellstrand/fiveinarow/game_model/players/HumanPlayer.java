package se.kjellstrand.fiveinarow.game_model.players;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;

/**
 * Created by carlemil on 2015-02-25.
 */
public class HumanPlayer extends AbstractPlayer {

    private static final String TAG = HumanPlayer.class.getSimpleName();
    private CountDownLatch countDownLatch = new CountDownLatch(0);

    private Move move = new Move();

    private FiveInARowBoard board = null;

    @Override
    public String getName() {
        return "Human";
    }

    public HumanPlayer(int _playerNumber) {
        super(_playerNumber);
    }

    public void makeMove(Move _move) {
        if (countDownLatch.getCount() == 1) {
            if (board != null && board.isMoveLegal(_move)) {
                move.x = _move.x;
                move.y = _move.y;
                countDownLatch.countDown();
                Log.d(TAG, "Human made a move: " + move);
            } else {
                Log.d(TAG, "illegal move " + board + " move: " + move);
            }
        }
    }

    @Override
    public void getNextMove(FiveInARowBoard _board, Move _move) {
        board = _board;
        countDownLatch = new CountDownLatch(1);

        try {
            countDownLatch.await();
            _move.x = move.x;
            _move.y = move.y;
            Log.d(TAG, "Returning human move " + move);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
