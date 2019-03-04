package lasermaze.domain.game;

import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.domain.message.CommandMessage;
import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class LaserPointer {
    private static final Logger log = getLogger(LaserPointer.class);

    private Direction direction;
    private Point point;
    private boolean end = false;

    public LaserPointer(Direction direction, Point point) {
        this.direction = direction;
        this.point = point;
    }

    public void move() {
        point.move(direction);
    }

    public void reflect(Direction pieceDirection) {
        if (pieceDirection.isSquareKnight()) {
            direction = pieceDirection;
            return;
        }
        Rotation reflectedRotation = direction.getTriangleRotation(pieceDirection);
        log.debug("reflectedRoation : {}", reflectedRotation);
        Direction rotatedDirection = direction.getRotatedDirection(reflectedRotation, 2);
        log.debug("rotatedDirection : {}", rotatedDirection);
        this.direction = rotatedDirection;
    }

    public LaserPointer generateNewLaserPointer() {
        return new LaserPointer(direction, point.generateNewPoint());
    }

    public boolean isEnd() {
        return this.end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getNextPoint() {
        return point.getNextPoint(direction);
    }

    public void putCommandMessage(List<CommandMessage> aMovement) {
        aMovement.add(new CommandMessage(point.getRow(), point.getCol(), direction.getDirectionNumber()));
        if(point.getNextPoint(direction).isOutOfBound()) {
            Point nextPoint = point.getNextPoint(direction);
            aMovement.add(new CommandMessage(nextPoint.getRow(), nextPoint.getCol(), direction.getDirectionNumber()));
        }
    }

    @Override
    public String toString() {
        return "LaserPointer{" +
                "direction=" + direction +
                ", point=" + point +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaserPointer that = (LaserPointer) o;
        return end == that.end &&
                direction == that.direction &&
                Objects.equals(point, that.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, point, end);
    }
}
