package se.kjellstrand.fiveinarow.game;

import android.util.Log;

/**
 * Created by carlemil on 2015-02-25.
 */
public class FiveInARowBoard {

    private static final String TAG = FiveInARowBoard.class.getCanonicalName();

    private int width;
    private int height;

    private AbstractPlayer p1;
    private AbstractPlayer p2;
    private AbstractPlayer currentPlayer;

    private int[][] board = null;

    public FiveInARowBoard(int _width, int _height, AbstractPlayer _p1, AbstractPlayer _p2) {
        width = _width;
        height = _height;
        board = new int[width][height];
        p1 = _p1;
        p2 = _p2;
        currentPlayer = p1;

    }

    public FiveInARowBoard(FiveInARowBoard b, AbstractPlayer _p1, AbstractPlayer _p2) {
        width = b.getWidth();
        height = b.getHeight();
        board = b.getCopyOfBoard();
        p1 = _p1;
        p2 = _p2;
        if (b.getCurrentPlayer().equals(b.getP1())) {
            currentPlayer = p1;
        } else {
            currentPlayer = p2;
        }
    }

    public int[][] getCopyOfBoard() {
        int[][] b = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                b[x][y] = board[x][y];
            }
        }
        return b;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameState makeMove(Move move, AbstractPlayer player) {
        if (move != null && move.x >= 0 && move.x < width &&
                move.y >= 0 && move.y < height) {

            board[move.x][move.y] = player.playerNumber;
        }

        GameState state = getState(move);
        if (state == GameState.UNDEFINED) {
            if (currentPlayer != p1) {
                currentPlayer = p1;
            } else {
                currentPlayer = p2;
            }
        }

        return state;
    }

    public int getPlayerOnPosition(int x, int y) {
        return board[x][y];
    }

    private GameState getState(Move m) {
        // TODO speedup kolla frpn m och utåt, såfort nuvarande :0, bryt och returnera UNDEF.
        int p = board[m.x][m.y];
        // Check only around the new move
        int cx = 0;
        int cy = 0;
        int cxy = 0;
        int cyx = 0;
        for (int i = -4; i <= 4; i++) {
            cx = (m.x + i >= 0 && m.x + i < width &&
                    p == board[m.x + i][m.y]) ? (cx + 1) : 0;
            cy = (m.y + i >= 0 && m.y + i < height &&
                    p == board[m.x][m.y + i]) ? (cy + 1) : 0;
            cxy = (m.x + i >= 0 && m.x + i < width &&
                    m.y + i >= 0 && m.y + i < height &&
                    p == board[m.x + i][m.y + i]) ? (cxy + 1) : 0;
            cyx = (m.x - i >= 0 && m.x - i < width &&
                    m.y + i >= 0 && m.y + i < height &&
                    p == board[m.x - i][m.y + i]) ? (cyx + 1) : 0;
            if (cx >= 5 || cy >= 5 || cxy >= 5 || cyx >= 5) {
                return GameState.WIN;
            }
        }
        return GameState.UNDEFINED;
    }

    public boolean isMoveLegal(Move move) {
        if (move != null &&
                move.x >= 0 && move.x < width &&
                move.y >= 0 && move.y < height &&
                board[move.x][move.y] == 0) {
            return true;
        }
        return false;
    }

    public void print() {
        StringBuffer sb = new StringBuffer();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(board[x][y] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, "Board\n" + sb.toString());
    }

    public AbstractPlayer getP1() {
        return p1;
    }

    public AbstractPlayer getP2() {
        return p2;
    }

    public AbstractPlayer getCurrentPlayer() {
        return currentPlayer;
    }

}
