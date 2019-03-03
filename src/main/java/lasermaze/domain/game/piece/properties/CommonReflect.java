package lasermaze.domain.game.piece.properties;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;

public abstract class CommonReflect implements Reflectable{

    @Override
    public boolean canDead(LaserPointer laserPointer, Direction direction) {
        return !laserPointer.getDirection().isReflectable(direction);
    }
}
