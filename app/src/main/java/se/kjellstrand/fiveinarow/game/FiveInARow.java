package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {

    private static final String TAG = "FiveInARow";

    private final FiveInARowBoard b;

    public FiveInARow(FiveInARowBoard _b) throws Exception {
        b = _b;
        if (b.getP1().equals(b.getP2())) {
            throw new Exception("A player can not play itself.");
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
        }
        return b.getCurrentPlayer();
    }

    private GameState advanceGame() {
        GameState state;
        Move move = b.getCurrentPlayer().getNextMove(b);
        boolean moveLegal = b.isMoveLegal(move);
        //Log.d(TAG, "player: " + currentPlayer.playerNumber + ", move: " + move + ", move is legal: " + moveLegal);
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
