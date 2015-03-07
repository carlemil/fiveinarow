package se.kjellstrand.fiveinarow.game_model.players;

import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;

/**
 * Created by carlemil on 2015-02-25.
 */
public class RandomPlayer extends AbstractPlayer {
    @Override
    public String getName() {
        return "RNDP";
    }

    public RandomPlayer(int _playerNumber) {
        super(_playerNumber);
    }

    @Override
    public Move getNextMove(FiveInARowBoard board) {
        Move move = new Move();
        int attempt = 0;
        do {
            move.x = (int) (Math.random() * board.getWidth());
            move.y = (int) (Math.random() * board.getHeight());
        } while (!board.isMoveLegal(move) && attempt++ < 100);

        return move;
    }
}