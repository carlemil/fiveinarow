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

        AbstractPlayer rp1 = new RandomPlayer(board.getP1().getPlayerNumber());
        AbstractPlayer rp2 = new RandomPlayer(board.getP2().getPlayerNumber());

        Log.d(TAG, ".\n.\n.\n----board-----");
        board.print();

        AbstractPlayer currentPlayer = board.getCurrentPlayer();
        AbstractPlayer rp = new RandomPlayer(currentPlayer.getPlayerNumber());

        Log.d(TAG, "+++current player " + currentPlayer.getPlayerNumber());
        for (int i = 0; i < 1; i++) {
            FiveInARowBoard cloneBoard = new FiveInARowBoard(board, rp1, rp2);
            Move move = rp.getNextMove(cloneBoard);
            Log.d(TAG, "move " + move);
            cloneBoard.makeMove(move, currentPlayer);
            FiveInARow fir = null;
            try {
                fir = new FiveInARow(cloneBoard);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "before win");
            cloneBoard.print();
            AbstractPlayer winner = fir.playTheGame();
            Log.d(TAG, "after win");
            cloneBoard.print();
            Log.d(TAG, "current player " + currentPlayer.getPlayerNumber());
            Log.d(TAG, "move " + move + ", winner " + winner.getPlayerNumber());
            if (winner.getPlayerNumber() == currentPlayer.getPlayerNumber()) {
                results[move.x][move.y]++;
            } else {
                results[move.x][move.y]--;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                sb.append(results[x][y] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, "----result-----\n" + sb.toString());

        Move bestMove = new Move(0, 0);

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
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
