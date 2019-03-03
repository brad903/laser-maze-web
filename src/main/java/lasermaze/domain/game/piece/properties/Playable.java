package lasermaze.domain.game.piece.properties;

import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;

public interface Playable {
    void move(Point point, Direction direction);
    Direction rotate(Direction direction, Rotation rotation);
}
