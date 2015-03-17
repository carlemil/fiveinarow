package se.kjellstrand.fiveinarow.game_model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (x != move.x) return false;
        if (y != move.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
