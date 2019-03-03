package lasermaze.domain.game.piece.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static lasermaze.domain.game.ChessSquare.CHESSBOARD_SIZE;

public enum Direction {
    NONE(0),
    NORTH(1), NORTHEAST(2), EAST(3), SOUTHEAST(4),
    SOUTH(5), SOUTHWEST(6), WEST(7), NORTHWEST(8);

    private static final Logger log = LoggerFactory.getLogger(Direction.class);

    private int commandNumber;

    Direction(int commandNumber) {
        this.commandNumber = commandNumber;
    }

    public static Direction getDirection(int commandNumber) {
        for (Direction value : values()) {
            if (value.commandNumber == commandNumber) return value;
        }
        return Direction.NONE;
    }

    public Direction getRotatedDirection(Rotation rotation, int degree) {
        int operand = rotation.equals(Rotation.COUNTERCLOCKWISE) ? -1 : 1;
        int newDirection = commandNumber + (degree * operand);

        if (newDirection <= 0 || newDirection > CHESSBOARD_SIZE)
            return getDirection(newDirection - (CHESSBOARD_SIZE * operand));

        return getDirection(newDirection);
    }

    public int getDirectionNumber() {
        return this.commandNumber;
    }

    public boolean isReflectable(Direction pieceDirection) {
        if (pieceDirection.isSquareKnight()) {
            return pieceDirection.commandNumber == getDiametricalNumber();
        }
        return pieceDirection.getRotatedDirection(Rotation.CLOCKWISE, 1).commandNumber != commandNumber
                && pieceDirection.getRotatedDirection(Rotation.COUNTERCLOCKWISE, 1).commandNumber != commandNumber;
    }

    public boolean isSquareKnight() {
        return commandNumber % 2 == 1;
    }

    public Direction getDiametricalDirection() {
        return getRotatedDirection(Rotation.COUNTERCLOCKWISE, 4);
    }

    public int getDiametricalNumber() {
        return getDiametricalDirection().commandNumber;
    }

    public Rotation getTriangleRotation(Direction pieceDirection) {
        int num = pieceDirection.commandNumber + 5;
        if (num > 8) num -= 8;
        if (num == commandNumber) return Rotation.CLOCKWISE;
        return Rotation.COUNTERCLOCKWISE;
    }
}
