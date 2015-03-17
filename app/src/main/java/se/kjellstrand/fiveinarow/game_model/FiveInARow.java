package se.kjellstrand.fiveinarow.game_model;

import android.util.Log;

import se.kjellstrand.fiveinarow.game_model.players.AbstractPlayer;
import se.kjellstrand.fiveinarow.game_model.players.RandomSearchPlayer;
import se.kjellstrand.fiveinarow.game_page.BoardView;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {

    private static final String TAG = "FiveInARow";

    private FiveInARowBoard b;

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
            state = advanceGame();
            if(b.getCurrentPlayer()instanceof RandomSearchPlayer){
                Log.d(TAG, "advanced game one step, state: "+state);
            }
            if (boardView != null) {
                boardView.redraw(b.getBoard());
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
            return GameState.DRAW;
        }
        if(b.getCurrentPlayer()instanceof RandomSearchPlayer){
            Log.d(TAG, "advanceGame1 "+this);
        }
        b.getCurrentPlayer().getNextMove(b, move);

        if(b.getCurrentPlayer()instanceof RandomSearchPlayer){
            Log.d(TAG, "advanceGame2");
        }
        boolean moveLegal = b.isMoveLegal(move);
        if (moveLegal) {
            state = b.makeMove(move, b.getCurrentPlayer());
        } else {
            state = GameState.LOSS;
        }
        return state;
    }

    public void setBoard(FiveInARowBoard board) {
        this.b = board;
    }

}
