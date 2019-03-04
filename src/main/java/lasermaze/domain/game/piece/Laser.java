package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.ImagePath;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.Playable;
import lasermaze.domain.game.user.UserDelimiter;

public class Laser extends Piece {

    public Laser(UserDelimiter userDelimiter, Direction direction, Point point, Playable playable, ImagePath imagePath) {
        super(userDelimiter, direction, point, playable, imagePath);
    }

    @Override
    public void hit(LaserPointer laserPointer) {
    }


    public LaserPointer generateLaserPointer() {
        return new LaserPointer(direction, point.generateNewPoint());
    }
}
