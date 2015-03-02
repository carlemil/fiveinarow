package se.kjellstrand.fiveinarow.game;

/**
 * Created by carlemil on 2015-02-25.
 */
public class Move {
    public int x;
    public int y;

    public Move(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public Move() {
        x = -1;
        y = -1;
    }
}
