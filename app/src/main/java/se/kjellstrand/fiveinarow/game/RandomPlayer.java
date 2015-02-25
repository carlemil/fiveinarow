package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class RandomPlayer extends AbstractPlayer {
    @Override
    public Move getNextMove(Board board) {
        Move move = new Move();

        move.x = (int) (Math.random() * board.getWidth());
        move.y = (int) (Math.random() * board.getHeight());

        return move;
    }
}
