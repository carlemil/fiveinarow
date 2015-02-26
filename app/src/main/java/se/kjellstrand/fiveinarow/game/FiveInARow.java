package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARow {
    private AbstractPlayer p1;
    private AbstractPlayer p2;

    private AbstractPlayer lastPlayer;

    private Board b;

    public FiveInARow(Board _b, AbstractPlayer _p1, AbstractPlayer _p2) {
        b = _b;
        p1 = _p1;
        p2 = _p2;
        lastPlayer = p1;
    }

    public AbstractPlayer advanceGame() {
        AbstractPlayer player;
        if (lastPlayer == null || lastPlayer != p1) {
            player = p1;
        } else {
            player = p2;
        }

        Move move = player.getNextMove(b);
        int state = b.makeMove(move, player);

        if (state == BoardState.WIN) {
            return player;
        }
        return null;
    }

    public void printBoard() {
        b.print();
    }
}
