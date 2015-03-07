package se.kjellstrand.fiveinarow.game_model;

import se.kjellstrand.fiveinarow.game_model.players.AbstractPlayer;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {

    private static final String TAG = "FiveInARow";

    private final FiveInARowBoard b;

    public FiveInARow(FiveInARowBoard _b) {
        b = _b;
        if (b.getP1().equals(b.getP2())) {
            throw new RuntimeException("A player can not play itself.");
        }
    }

    public AbstractPlayer playTheGame() {
        GameState state = GameState.UNDEFINED;
        while (state == GameState.UNDEFINED) {
            state = advanceGame();
        }

        if (state == GameState.LOSS) {
            if (b.getCurrentPlayer() == b.getP1()) {
                return b.getP2();
            } else {
                return b.getP1();
            }
        } else if(state == GameState.DRAW) {
            return null;
        }
        return b.getCurrentPlayer();
    }

    public GameState advanceGame() {
        GameState state;
        if(b.isBoardFull()){
            return GameState.DRAW;
        }
        Move move = b.getCurrentPlayer().getNextMove(b);
        boolean moveLegal = b.isMoveLegal(move);
        if (moveLegal) {
            state = b.makeMove(move, b.getCurrentPlayer());
        } else {
            state = GameState.LOSS;
        }
        return state;
    }

    public void printBoard() {
        b.print();
    }
}
