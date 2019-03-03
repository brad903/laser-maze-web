package lasermaze.domain.game.piece.common;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static lasermaze.domain.game.ChessSquare.CHESSBOARD_SIZE;
import static org.slf4j.LoggerFactory.getLogger;

public class Point {
    private static final Logger log = getLogger(Point.class);

    private static final Map<Direction, Point> MOVE_TABLE = new HashMap<>();

    static {
        MOVE_TABLE.put(Direction.NORTH, new Point(-1, 0));
        MOVE_TABLE.put(Direction.NORTHEAST, new Point(-1, 1));
        MOVE_TABLE.put(Direction.EAST, new Point(0, 1));
        MOVE_TABLE.put(Direction.SOUTHEAST, new Point(1, 1));
        MOVE_TABLE.put(Direction.SOUTH, new Point(1, 0));
        MOVE_TABLE.put(Direction.SOUTHWEST, new Point(1, -1));
        MOVE_TABLE.put(Direction.WEST, new Point(0, -1));
        MOVE_TABLE.put(Direction.NORTHWEST, new Point(-1, -1));
    }

    private int row;
    private int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void move(Direction direction) {
        modify(direction);
    }

    public Point getNextPoint(Direction direction) {
        Point nextPoint = new Point(this.row, this.col);
        return nextPoint.modify(direction);
    }

    public Point modify(Direction direction) {
        Point moveStandard = MOVE_TABLE.get(direction);
        this.row += moveStandard.row;
        this.col += moveStandard.col;
        return this;
    }

    public Point generateNewPoint() {
        return new Point(row, col);
    }

    public boolean isOutOfBound() {
        return row < 0 || row >= CHESSBOARD_SIZE || col < 0 || col >= CHESSBOARD_SIZE;
    }

    public Point getSymmetrical() {
        return new Point(getOpposite(row), getOpposite(col));
    }

    private int getOpposite(int num) {
        return CHESSBOARD_SIZE - num - 1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Point[row=" + row + ", col=" + col + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return row == point.row &&
                col == point.col;
    }
}
