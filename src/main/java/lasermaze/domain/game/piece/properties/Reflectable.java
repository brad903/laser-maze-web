package lasermaze.domain.game.piece.properties;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;

public interface Reflectable {
    void reflect(LaserPointer laserPointer, Direction direction);
    boolean canDead(LaserPointer laserPointer, Direction direction);
}
