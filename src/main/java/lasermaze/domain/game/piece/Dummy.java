package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.Playable;
import lasermaze.domain.game.user.GameUser;

public class Dummy extends Piece {
    public Dummy(GameUser gameUser, Direction direction, Point point, Playable playable) {
        super(gameUser, direction, point, playable);
    }

    @Override
    public void hit(LaserPointer laserPointer) {
    }
}
