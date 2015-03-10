package se.kjellstrand.fiveinarow.game_model.players;

import java.util.concurrent.CountDownLatch;

import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;

/**
 * Created by carlemil on 2015-02-25.
 */
public class HumanPlayer extends AbstractPlayer {

    private CountDownLatch countDownLatch = new CountDownLatch(0);

    private Move move;

    @Override
    public String getName() {
        return "Human";
    }

    public HumanPlayer(int _playerNumber) {
        super(_playerNumber);
    }

    public void makeMove(Move _move) {
        if (countDownLatch.getCount() == 1) {
            move.x = _move.x;
            move.y = _move.y;
            countDownLatch.countDown();
        }
    }

    @Override
    public Move getNextMove(FiveInARowBoard board) {
        move = new Move();
        countDownLatch = new CountDownLatch(1);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return move;
    }
}
