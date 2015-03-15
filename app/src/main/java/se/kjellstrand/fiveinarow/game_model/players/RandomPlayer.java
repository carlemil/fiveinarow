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
    public void getNextMove(FiveInARowBoard board, Move move) {
        int x, y, c = 0;
        while (c++ < board.getHeight()) {
            x = (int) (Math.random() * board.getWidth());
            y = (int) (Math.random() * board.getHeight());

            if (board.isMoveLegal(x, y)) {
                move.x = x;
                move.y = y;
                return;
            }
        }

        double currentMaxRnd = 0f;
        for (y = 0; y < board.getWidth(); y++) {
            for (x = 0; x < board.getHeight(); x++) {
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
    }
}
