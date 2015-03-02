package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class CloseToLastMovePlayer extends AbstractPlayer {

    private Move lastMove = new Move(-1, -1);

    @Override
    public Move getNextMove(Board board) {
        Move move = new Move();
        if (lastMove.x != -1 && lastMove.y != -1) {
            int attempt = 0;
            do {
                move.x = lastMove.x + (int) ((Math.random() - 0.5f) * 4);
                move.y = lastMove.y + (int) ((Math.random() - 0.5f) * 4);
            } while (!board.isMoveLegal(move) && attempt++ < 100);
        } else {
            move.x = 5;
            move.y = 5;
        }
        lastMove.x = move.x;
        lastMove.y = move.y;

        return move;
    }
}
