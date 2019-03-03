package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Rotation;

public interface Pieceable {
    void hit(LaserPointer laserPointer);

    void move(Direction direction);

    void rotate(Rotation rotation);
}
