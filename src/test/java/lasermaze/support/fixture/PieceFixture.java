package lasermaze.support.fixture;

import lasermaze.domain.game.piece.*;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.*;
import lasermaze.domain.game.user.GameUser;

import static lasermaze.domain.game.user.UserTest.DOBY;

public class PieceFixture {

    public static Dummy createDummy(Point point) {
        return new Dummy(GameUser.DUMMY_GAME_USER, Direction.NONE, point, CommonPlay.getInstance());
    }

    public static King createKing(GameUser gameUser, Direction direction, Point point) {
        return new King(gameUser, direction, point, NonLaserPiece.getInstance());
    }

    public static Knight createSquareNight(Direction direction, Point point) {
        return new Knight(DOBY, direction, point, NonLaserPiece.getInstance(), HorizontalReflect.getInstance());
    }

    public static Knight createTriangleNight(Direction direction, Point point) {
        return new Knight(DOBY, direction, point, NonLaserPiece.getInstance(), DiagonalReflect.getInstance());
    }

    public static Splitter createSplitter(Direction direction, Point point) {
        return new Splitter(DOBY, direction, point, NonLaserPiece.getInstance(), DiagonalReflect.getInstance());
    }

    public static Laser createLaser(Direction direction, Point point) {
        return new Laser(DOBY, direction, point, LaserPiece.getInstance());
    }

}
