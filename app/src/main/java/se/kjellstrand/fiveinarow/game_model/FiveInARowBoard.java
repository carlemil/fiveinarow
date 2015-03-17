package se.kjellstrand.fiveinarow.game_model;

import android.util.Log;

import java.util.ArrayList;

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

    //private int madeMoves = 0;

    private ArrayList<Move> validMoves= new ArrayList<Move>();

    public FiveInARowBoard(int _width, int _height, AbstractPlayer _p1, AbstractPlayer _p2) {
        width = _width;
        height = _height;
        board = new int[width][height];
        //madeMoves = 0;
        setValidMoves();
        p1 = _p1;
        p2 = _p2;
        currentPlayer = p1;
    }

    public void init(FiveInARowBoard b) {
        b.setWidth(width);
        b.setHeight(height);
        b.setBoard(board);
        //b.setMadeMoves(madeMoves);
        b.setValidMoves();
        b.setCurrentPlayer(currentPlayer);
    }

//    private void setValidMoves(ArrayList<Move> _validMoves) {
//        _validMoves.clear();
//        StringBuffer sb = new StringBuffer();
//        for(Move m: validMoves){
//            _validMoves.add(m);
//            sb.append(" - "+m);
//        }
//        Log.d(TAG, sb.toString());
//    }

    private void setValidMoves() {
        validMoves.clear();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board != null && board[x][y] == 0) {
                    validMoves.add(new Move(x, y));
                }
            }
        }
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
            //madeMoves++;
            validMoves.remove(move);
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
        return validMoves.isEmpty();
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

    public void setCurrentPlayer(AbstractPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Move> getValidMoves() {
        return validMoves;
    }
}
