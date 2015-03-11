package se.kjellstrand.fiveinarow.game_model.players;

import android.util.Log;

import se.kjellstrand.fiveinarow.game_model.FiveInARow;
import se.kjellstrand.fiveinarow.game_model.FiveInARowBoard;
import se.kjellstrand.fiveinarow.game_model.Move;

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

        intiResultBoard(results, board);

        AbstractPlayer rp1 = new RandomPlayer(board.getP1().getPlayerNumber());
        AbstractPlayer rp2 = new RandomPlayer(board.getP2().getPlayerNumber());

//        Log.d(TAG, ".\n.\n.\n----board-----");
//        board.print();

        AbstractPlayer currentPlayer = board.getCurrentPlayer();
        AbstractPlayer rp = new RandomPlayer(currentPlayer.getPlayerNumber());

        //Log.d(TAG, "+++current player " + currentPlayer.getPlayerNumber());
        for (int i = 0; i < 10000; i++) {
            FiveInARowBoard cloneBoard;
            Move move;

            cloneBoard = new FiveInARowBoard(board, rp1, rp2);

            move = rp.getNextMove(cloneBoard);
            //Log.d(TAG, "move " + move);

            cloneBoard.makeMove(move, currentPlayer);
            //printResultBoard(results);
            AbstractPlayer winner = playRandomGameOnBoard(currentPlayer, cloneBoard);

            updateResultBoard(results, currentPlayer, move, winner);
        }
        board.print();
        printResultBoard(results);
        Move bestMove = getBestMove(board, results);
        Log.d(TAG, "best move: " + bestMove);

        return bestMove;
    }

    private void intiResultBoard(int[][] results, FiveInARowBoard board) {
        for (int y = 0; y < results.length; y++) {
            for (int x = 0; x < results[0].length; x++) {
                if (!board.isMoveLegal(x,y)){
                    results[x][y] = Integer.MIN_VALUE;
                }
            }
        }
    }

    public void updateResultBoard(int[][] results, AbstractPlayer currentPlayer, Move move, AbstractPlayer winner) {
        if (winner.getPlayerNumber() == currentPlayer.getPlayerNumber()) {
            results[move.x][move.y]++;
        } else {
            results[move.x][move.y]--;
        }
    }

    public AbstractPlayer playRandomGameOnBoard(AbstractPlayer currentPlayer, FiveInARowBoard cloneBoard) {
        FiveInARow fir = null;
        try {
            fir = new FiveInARow(cloneBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AbstractPlayer winner = fir.playTheGame(null);
        return winner;
    }

    public Move getBestMove(FiveInARowBoard board, int[][] results) {
        Move bestMove = new Move(0, 0);
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (//board.isMoveLegal(x, y) &&
                        results[bestMove.x][bestMove.y] < results[x][y]) {
                    bestMove.x = x;
                    bestMove.y = y;
                    Log.d(TAG, "legal: "+x+", "+y+" value "+results[bestMove.x][bestMove.y]);
                }
            }
        }
        return bestMove;
    }

    public void printResultBoard(int[][] results) {
        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < results.length; y++) {
            for (int x = 0; x < results[0].length; x++) {
                sb.append(results[x][y] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, "----result-----\n" + sb.toString());
    }
}
