package lasermaze.domain.game.piece.properties;

import lasermaze.domain.game.NotSupportedException;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Rotation;

public class LaserPiece extends CommonPlay {

    private static LaserPiece laserPiece;

    public static LaserPiece getInstance() {
        if(laserPiece != null) return laserPiece;

        laserPiece = new LaserPiece();
        return laserPiece;
    }

    @Override
    public Direction rotate(Direction direction, Rotation rotation) {
        int rotatedDirection = direction.getRotatedDirection(rotation, 2).getDirectionNumber();

        if ((direction.getDirectionNumber() == 1 || direction.getDirectionNumber() == 3) && rotatedDirection == 5 || rotatedDirection == 7) {
            throw new NotSupportedException("해당 방향으로 레이저를 회전시킬 수 없습니다!");
        }

        if ((direction.getDirectionNumber() == 5 || direction.getDirectionNumber() == 7) && rotatedDirection == 1 || rotatedDirection == 3) {
            throw new NotSupportedException("해당 방향으로 레이저를 회전시킬 수 없습니다!");
        }

        return Direction.getDirection(rotatedDirection);
    }
}
