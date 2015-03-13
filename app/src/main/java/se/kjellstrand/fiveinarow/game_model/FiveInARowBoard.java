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

    public void init(FiveInARowBoard b) {
        b.setWidth(width);
        b.setHeight(height);
        b.setBoard(board);
        b.setMadeMoves(madeMoves);
        //b.setP1(p1);
        //b.setP2(p2);
        b.setCurrentPlayer(currentPlayer);
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
            board[move.x][move.y] = player.getPlayerNumber();
            madeMoves++;
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


    public GameState getState(Move m) {
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
        return madeMoves >= height * width;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBoard(int[][] board) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[x][y] = board[x][y];
            }
        }
    }

    public void setMadeMoves(int madeMoves) {
        this.madeMoves = madeMoves;
    }

    public int getMadeMoves() {
        return madeMoves;
    }

    public void setP1(AbstractPlayer p1) {
        this.p1 = p1;
    }

    public void setP2(AbstractPlayer p2) {
        this.p2 = p2;
    }

    public void setCurrentPlayer(AbstractPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
