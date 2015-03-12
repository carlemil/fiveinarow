package se.kjellstrand.fiveinarow.game_model;

import android.util.Log;

import se.kjellstrand.fiveinarow.game_model.players.AbstractPlayer;

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

    private int madeMoves = 0;

    public FiveInARowBoard(int _width, int _height, AbstractPlayer _p1, AbstractPlayer _p2) {
        width = _width;
        height = _height;
        board = new int[width][height];
        madeMoves = 0;
        p1 = _p1;
        p2 = _p2;
        currentPlayer = p1;
    }

    public FiveInARowBoard(FiveInARowBoard b, AbstractPlayer _p1, AbstractPlayer _p2) {
        width = b.getWidth();
        height = b.getHeight();
        board = b.getCopyOfBoard();
        madeMoves = countMadeMoves();
        p1 = _p1;
        p2 = _p2;
        if (b.getCurrentPlayer().equals(b.getP1())) {
            currentPlayer = p1;
        } else {
            currentPlayer = p2;
        }
    }

    private int countMadeMoves() {
        int moves = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[x][y] != 0) {
                    moves++;
                }
            }
        }
        return moves;
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
            if (board[move.x][move.y] != 0) {
                Log.d(TAG, "\n---board---\n" + board.toString());
                throw new RuntimeException("Can not play on a occupied square. move: " + move);
            }
            //Log.d(TAG, "move: "+move);
            board[move.x][move.y] = player.getPlayerNumber();
            //TODO
            madeMoves++;
        }
        GameState state = getState(move);
        GameState state2 = getState2(move);

        if (state != state2) {
            print();
            Log.d(TAG, "move " + move);
            throw new RuntimeException("state missmatch");
        }
        if (state == GameState.UNDEFINED) {
            if (currentPlayer != p1) {
                currentPlayer = p1;
            } else {
                currentPlayer = p2;
            }
        }
        //Log.d(TAG, "state: "+state);
        return state;
    }

    public int getPlayerOnPosition(int x, int y) {
        return board[x][y];
    }

    public GameState getState(Move m) {
        // Check only around the new move
        int p = board[m.x][m.y];
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


    public GameState getState2(Move m) {
        // Check only around the new move
        int p = board[m.x][m.y];
        // Check left-right
        int c = 0;
        for (int i = 0; i < 5; i++) {
            if (m.x + i >= 0 && m.x + i < width && board[m.x + i][m.y] == p) {
                c++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (m.x - i >= 0 && m.x - i < width && board[m.x - i][m.y] == p) {
                c++;
            } else {
                break;
            }
        }
        if (c >= 5) {
            return GameState.WIN;
        }
        // Check up-down
        c = 0;
        for (int i = 0; i < 5; i++) {
            if (m.y + i >= 0 && m.y + i < height && board[m.x][m.y + i] == p) {
                c++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (m.y - i >= 0 && m.y - i < height && board[m.x][m.y - i] == p) {
                c++;
            } else {
                break;
            }
        }
        if (c >= 5) {
            return GameState.WIN;
        }
        // Check up/left-down/right
        c = 0;
        for (int i = 0; i < 5; i++) {
            if (m.x + i >= 0 && m.x + i < width && m.y + i >= 0 && m.y + i < height && board[m.x + i][m.y + i] == p) {
                c++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (m.x - i >= 0 && m.x - i < width && m.y - i >= 0 && m.y - i < height && board[m.x - i][m.y - i] == p) {
                c++;
            } else {
                break;
            }
        }
        if (c >= 5) {
            return GameState.WIN;
        }
        // Check up/right-down-left
        c = 0;
        for (int i = 0; i < 5; i++) {
            if (m.x + i >= 0 && m.x + i < width && m.y - i >= 0 && m.y - i < height && board[m.x + i][m.y - i] == p) {
                c++;
            } else {
                break;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (m.x - i >= 0 && m.x - i < width && m.y + i >= 0 && m.y + i < height && board[m.x - i][m.y + i] == p) {
                c++;
            } else {
                break;
            }
        }
        if (c >= 5) {
            return GameState.WIN;
        }

        return GameState.UNDEFINED;
    }

    public boolean isMoveLegal(Move move) {
        if (move != null) {
            return isMoveLegal(move.x, move.y);
        } else {
            return false;
        }
    }

    public boolean isMoveLegal(int x, int y) {
        if (x >= 0 && x < width &&
                y >= 0 && y < height &&
                board[x][y] == 0) {
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

    public boolean isBoardFull() {
        //Log.d(TAG, "board fullnes: " + madeMoves + " height * width " + height * width);
        return madeMoves >= height * width;
    }
}
