package se.kjellstrand.fiveinarow.game;

import android.util.Log;

/**
 * Created by carlemil on 2015-02-25.
 */
public class Board {
    private static final String TAG = Board.class.getCanonicalName();
    private int width;
    private int height;

    private int[][] board = null;

    public Board(int _width, int _height) {
        width = _width;
        height = _height;
        board = new int[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int makeMove(Move move, AbstractPlayer player) {
        if (move != null && move.x >= 0 && move.x < width &&
                move.y >= 0 && move.y < height &&
                board[move.x][move.y] == 0) {
            board[move.x][move.y] = player.playerNumber;
            return getState(move);
        }
        return BoardState.UNDEFINED;
    }

    public int getState(Move m) {
        int p = board[m.x][m.y];
        // Check only around the new move
        int cx = 0;
        int cy = 0;
        int cxy = 0;
        int cyx = 0;
        for (int i = -4; i <= 4; i++) {
            cx = (m.x + i > 0 && m.x + i < width && p == board[m.x + i][m.y]) ? (cx + 1) : 0;
            cy = (m.y + i > 0 && m.y + i < height && p == board[m.x][m.y + i]) ? (cy + 1) : 0;
            //cxy = p == board[i][m.y] ? (cx+1): 0;
            //cyx = p == board[i][m.y] ? (cx+1): 0;
            if (cx >= 5 || cy >= 5 || cxy >= 5 || cyx >= 5) {
                return BoardState.WIN;
            }
        }
        return BoardState.UNDEFINED;
    }

    public void print() {
        StringBuffer sb = new StringBuffer();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sb.append(board[x][y] + ", ");
            }
            sb.append("\n");
        }
        Log.d(TAG, "Board\n" + sb.toString());
    }
}
