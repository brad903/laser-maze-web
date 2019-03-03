package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.ImagePath;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.Playable;
import lasermaze.domain.game.piece.properties.Reflectable;
import lasermaze.domain.game.user.GameUser;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Knight extends Piece {
    private static final Logger log = getLogger(Knight.class);

    private Reflectable reflectable;

    public Knight(GameUser gameUser, Direction direction, Point point, Playable playable, Reflectable reflectable, ImagePath imagePath) {
        super(gameUser, direction, point, playable, imagePath);
        this.reflectable = reflectable;
    }

    @Override
    public void hit(LaserPointer laserPointer) {
        if (reflectable.canDead(laserPointer, direction)) {
            laserPointer.setEnd(true);
            return;
        }
        reflectable.reflect(laserPointer, direction);
    }

}
