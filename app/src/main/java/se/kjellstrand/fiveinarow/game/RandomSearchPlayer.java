package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class RandomSearchPlayer extends AbstractPlayer {

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

        Move bestMove = new Move(0, 0);

        for (int x = 0; x < cloneBoard.getWidth(); x++) {
            for (int y = 0; y < cloneBoard.getHeight(); y++) {
                if (results[bestMove.x][bestMove.y] < results[x][y]) {
                    bestMove.x = x;
                    bestMove.y = y;
                }
            }
        }

        return bestMove;
    }
}
