package lasermaze.domain.game.piece.properties;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;

public class HorizontalReflect extends CommonReflect {

    private static HorizontalReflect horizontalReflect;

    private HorizontalReflect() {
    }

    public static HorizontalReflect getInstance() {
        if(horizontalReflect != null) return horizontalReflect;

        horizontalReflect = new HorizontalReflect();
        return horizontalReflect;
    }

    @Override
    public void reflect(LaserPointer laserPointer, Direction direction) {
        laserPointer.reflect(direction);
    }
}
