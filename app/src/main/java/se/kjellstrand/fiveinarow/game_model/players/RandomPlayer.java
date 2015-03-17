package se.kjellstrand.fiveinarow.game_model.players;

import java.util.ArrayList;

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

        //get list of valid moves from board, would speedup things alot.

        ArrayList<Move> vm = board.getValidMoves();
        Move m = vm.get((int) (Math.random() * vm.size()));
        move.x = m.x;
        move.y = m.y;

        /*int x, y, c = 0;
        // First try to get a random move the cheep way.
        while (c++ < board.getHeight()) {
            x = (int) (Math.random() * board.getWidth());
            y = (int) (Math.random() * board.getHeight());

            if (board.isMoveLegal(x, y)) {
                move.x = x;
                move.y = y;
                return;
            }
        }
        // If that fails make sure to get a proper random move.
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
        }*/
    }
}
