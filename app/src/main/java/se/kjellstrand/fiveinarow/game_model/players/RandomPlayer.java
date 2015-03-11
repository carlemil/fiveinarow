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
        double currentMaxRnd = 0f;
        for (int y = 0; y < board.getWidth(); y++) {
            for (int x = 0; x < board.getHeight(); x++) {
                if (board.isMoveLegal(x, y)) {
                    double rnd = Math.random();
                    if (rnd > currentMaxRnd) {
                        currentMaxRnd = rnd;
                        move.x = x;
                        move.y = y;
                    }
                }
            }
        }
        return move;
    }
}
