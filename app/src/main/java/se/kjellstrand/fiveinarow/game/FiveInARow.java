package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {

    private static final String TAG = FiveInARow.class.getCanonicalName();

    private final AbstractPlayer p1;
    private final AbstractPlayer p2;

    private final Board b;

    private AbstractPlayer currentPlayer;

    public FiveInARow(Board _b, AbstractPlayer _p1, AbstractPlayer _p2) {
        b = _b;
        p1 = _p1;
        p2 = _p2;
        currentPlayer = p1;
    }

    public AbstractPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState advanceGame() {
        GameState state;
        Move move = currentPlayer.getNextMove(b);
        boolean moveLegal = b.isMoveLegal(move);
        if (moveLegal) {
            state = b.makeMove(move, currentPlayer);
            if (state == GameState.UNDEFINED) {
                if (currentPlayer != p1) {
                    currentPlayer = p1;
                } else {
                    currentPlayer = p2;
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
