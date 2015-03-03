package se.kjellstrand.fiveinarow.game;

import android.util.Log;

/**
 * Created by carlemil on 2015-02-25.
 */
public class RandomSearchPlayer extends AbstractPlayer {

    private static final String TAG = "RandomSearchPlayer";

    public RandomSearchPlayer(int _playerNumber) {
        super(_playerNumber);
    }

    @Override
    public String getName() {
        return "RSP";
    }

    @Override
    public Move getNextMove(FiveInARowBoard board) {
        int[][] results = new int[board.getWidth()][board.getHeight()];

        AbstractPlayer rp1 = new RandomPlayer(board.getP1().playerNumber);
        AbstractPlayer rp2 = new RandomPlayer(board.getP2().playerNumber);

        Log.d(TAG, ".\n.\n.\n----board-----");
        board.print();

        FiveInARowBoard cloneBoard = new FiveInARowBoard(board, rp1, rp2);

        AbstractPlayer currentPlayer = cloneBoard.getCurrentPlayer();

        for (int i = 0; i < 100; i++) {
            Move move = currentPlayer.getNextMove(cloneBoard);
            FiveInARow fir = null;
            try {
                fir = new FiveInARow(cloneBoard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AbstractPlayer winner = fir.playTheGame();
            if (winner.equals(currentPlayer)) {
                results[move.x][move.y]++;
            } else {
                results[move.x][move.y]--;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int x = 0; x < cloneBoard.getWidth(); x++) {
            for (int y = 0; y < cloneBoard.getHeight(); y++) {
                sb.append(results[x][y] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, "----result-----\n" + sb.toString());

        Move bestMove = new Move(0, 0);

        for (int x = 0; x < cloneBoard.getWidth(); x++) {
            for (int y = 0; y < cloneBoard.getHeight(); y++) {
                if (results[bestMove.x][bestMove.y] < results[x][y]) {
                    bestMove.x = x;
                    bestMove.y = y;
                }
            }
        }
        Log.d(TAG, "best move: " + bestMove);

        return bestMove;
    }
}
