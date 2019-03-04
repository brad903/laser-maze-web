package lasermaze.domain.game.piece.properties;

import lasermaze.domain.game.NotSupportedException;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;

public class CommonPlay implements Playable {

    private static CommonPlay commonPlay;

    CommonPlay() {
    }

    public static CommonPlay getInstance() {
        if(commonPlay != null) return commonPlay;

        commonPlay = new CommonPlay();
        return commonPlay;
    }

    @Override
    public void move(Point point, Direction direction) {
        throw new NotSupportedException("해당 장기를 움직일 수 없습니다!");
    }

    @Override
    public Direction rotate(Direction direction, Rotation rotation) {
        throw new NotSupportedException("해당 장기를 회전시킬 수 없습니다!");
    }
}
