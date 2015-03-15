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

    private FiveInARow fir = null;

    public RandomSearchPlayer(int _playerNumber) {
        super(_playerNumber);
    }

    @Override
    public String getName() {
        return "RSP";
    }

    @Override
    public void getNextMove(FiveInARowBoard board, Move move) {
        int[][] results = new int[board.getWidth()][board.getHeight()];

        intiResultBoard(results, board);

        AbstractPlayer rp1 = new RandomPlayer(board.getP1().getPlayerNumber());
        AbstractPlayer rp2 = new RandomPlayer(board.getP2().getPlayerNumber());

        Log.d(TAG, ".\n.\n.\n----board-----");
//        board.print();

        AbstractPlayer currentPlayer = board.getCurrentPlayer();
        AbstractPlayer rp = new RandomPlayer(currentPlayer.getPlayerNumber());

        int draws = 0;

        Log.d(TAG, "+++current player " + currentPlayer.getPlayerNumber());
        FiveInARowBoard tmpBoard = new FiveInARowBoard(board.getWidth(), board.getHeight(), rp1, rp2);
        for (int i = 0; i < 10000; i++) {
            board.init(tmpBoard);
            rp.getNextMove(tmpBoard, move);
            //Log.d(TAG, "move " + move);

            tmpBoard.makeMove(move, currentPlayer);
            //printResultBoard(results);
            AbstractPlayer winner = playRandomGameOnBoard(currentPlayer, tmpBoard);
            if (winner != null) {
                updateResultBoard(results, currentPlayer, move, winner);
            } else {
                draws++;
            }
            //Log.d(TAG, "draws " + draws);
        }
        //board.print();
        //printResultBoard(results);
        getBestMove(board, results, move);
        Log.d(TAG, "best move: " + move + " draws: " + draws);

    }

    private void intiResultBoard(int[][] results, FiveInARowBoard board) {
        for (int y = 0; y < results.length; y++) {
            for (int x = 0; x < results[0].length; x++) {
                if (!board.isMoveLegal(x, y)) {
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
        if (fir == null) {
            fir = new FiveInARow(cloneBoard);
        } else {
            fir.setBoard(cloneBoard);
        }
        //Log.d(TAG, "new fir");
        AbstractPlayer winner = fir.playTheGame(null);
        //Log.d(TAG, "played a game");
        return winner;
    }

    public Move getBestMove(FiveInARowBoard board, int[][] results, Move move) {
        move.x = 0;
        move.y = 0;
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (//board.isMoveLegal(x, y) &&
                        results[move.x][move.y] < results[x][y]) {
                    move.x = x;
                    move.y = y;
                    Log.d(TAG, "legal: " + x + ", " + y + " value " + results[move.x][move.y]);
                }
            }
        }
        return move;
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
