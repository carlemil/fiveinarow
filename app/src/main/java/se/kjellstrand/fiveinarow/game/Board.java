package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class Board {
    private double width;
    private double height;
    private BoardState bs = null;

    public Board(int _width, int _height){
        width = _width;
        height = _height;
        bs = new BoardState();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void makeMove(Move move){

    }

    public BoardState getState(){

        return null;
    }
}
