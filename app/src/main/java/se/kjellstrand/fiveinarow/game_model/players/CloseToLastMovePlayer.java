package se.kjellstrand.fiveinarow.game_model.players;

import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;

/**
 * Created by carlemil on 2015-02-25.
 */
public class CloseToLastMovePlayer extends AbstractPlayer {

    private Move lastMove = new Move(-1, -1);

    public CloseToLastMovePlayer(int _playerNumber) {
        super(_playerNumber);
    }

    @Override
    public String getName() {
        return "CTLM";
    }

    @Override
    public void getNextMove(FiveInARowBoard board, Move move) {
        if (lastMove.x != -1 && lastMove.y != -1) {
            int attempt = 0;
            do {
                move.x = lastMove.x + (int) ((Math.random() - 0.5f) * 6);
                move.y = lastMove.y + (int) ((Math.random() - 0.5f) * 6);
            } while (!board.isMoveLegal(move) && attempt++ < 100);
        } else {
            move.x = board.getWidth() / 2;
            move.y = board.getHeight() / 2;
        }
        lastMove.x = move.x;
        lastMove.y = move.y;
    }
}
