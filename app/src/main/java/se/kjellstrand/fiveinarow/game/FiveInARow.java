package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {
    AbstractPlayer p1;
    AbstractPlayer p2;

    AbstractPlayer lastPlayer;

    Board b;

    public FiveInARow(Board _b, AbstractPlayer _p1, AbstractPlayer _p2) {
        b = _b;
        p1 = _p1;
        p2 = _p2;
    }

    public BoardState advanceGame() {
        Move move = null;

        if (lastPlayer == null || lastPlayer != p1) {
            move = p1.getNextMove(b);
        } else {
            move = p2.getNextMove(b);
        }

        b.makeMove(move);
        
        BoardState gameState = b.getState();

        return gameState;
    }
}
