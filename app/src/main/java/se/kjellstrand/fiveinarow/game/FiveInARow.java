package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {

    private static final String TAG = "FiveInARow";

    private final FiveInARowBoard b;

    private AbstractPlayer currentPlayer;

    public FiveInARow(FiveInARowBoard _b) throws Exception {
        b = _b;
        currentPlayer = b.getP1();
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
            if (currentPlayer == b.getP1()) {
                return b.getP2();
            } else {
                return b.getP1();
            }
        }
        return currentPlayer;
    }

    private GameState advanceGame() {
        GameState state;
        Move move = currentPlayer.getNextMove(b);
        boolean moveLegal = b.isMoveLegal(move);
        //Log.d(TAG, "player: " + currentPlayer.playerNumber + ", move: " + move + ", move is legal: " + moveLegal);
        if (moveLegal) {
            state = b.makeMove(move, currentPlayer);
            if (state == GameState.UNDEFINED) {
                if (currentPlayer != b.getP1()) {
                    currentPlayer = b.getP1();
                } else {
                    currentPlayer = b.getP2();
                }
            }
        } else {
            state = GameState.LOSS;
        }
        return state;
    }

    public void printBoard() {
        b.print();
    }
}
