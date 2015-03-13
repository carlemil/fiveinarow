package se.kjellstrand.fiveinarow.game_model;

import android.util.Log;

import se.kjellstrand.fiveinarow.game_model.players.AbstractPlayer;
import se.kjellstrand.fiveinarow.game_page.BoardView;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {

    private static final String TAG = "FiveInARow";

    private final FiveInARowBoard b;

    private Move move = new Move();


    public FiveInARow(FiveInARowBoard _b) {
        b = _b;
        if (b.getP1().equals(b.getP2())) {
            throw new RuntimeException("A player can not play itself.");
        }
    }

    public AbstractPlayer playTheGame(BoardView boardView) {
        GameState state = GameState.UNDEFINED;
        while (state == GameState.UNDEFINED) {
            //Log.d(TAG, "game loop");
            state = advanceGame();
            if (boardView != null) {
                boardView.redraw(b.getBoard());
               // Log.d(TAG, "playing the game");
                //SystemClock.sleep(100);
            }
        }

        if (state == GameState.LOSS) {
            if (b.getCurrentPlayer() == b.getP1()) {
                return b.getP2();
            } else {
                return b.getP1();
            }
        } else if (state == GameState.DRAW) {
            return null;
        }
        return b.getCurrentPlayer();
    }

    public GameState advanceGame() {
        GameState state;
        if (b.isBoardFull()) {
            //Log.d(TAG, "ERROR: BOARD FULL");
            return GameState.DRAW;
        }
        //Log.d(TAG, "get next move, player: "+b.getCurrentPlayer().getName());
        b.getCurrentPlayer().getNextMove(b, move);
        //Log.d(TAG, "got next move");
        //Log.d(TAG, "MOVE: "+move);
        boolean moveLegal = b.isMoveLegal(move);
        if (moveLegal) {
            state = b.makeMove(move, b.getCurrentPlayer());
        } else {
            state = GameState.LOSS;
        }
        //Log.d(TAG, "STATE: "+state);
        return state;
    }

//    public void getBoardCopy(int[][] board) {
//        b.getCopyOfBoard(board);
//    }
}
