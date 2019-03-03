package lasermaze.support.fixture;

import lasermaze.domain.game.piece.*;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.ImagePath;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.*;
import lasermaze.domain.game.user.GameUser;

import static lasermaze.domain.game.user.UserTest.DOBY;

public class PieceFixture {

    public static Dummy createDummy(Point point) {
        return new Dummy(GameUser.DUMMY_GAME_USER, Direction.NONE, point, CommonPlay.getInstance(), ImagePath.DUMMY);
    }

    public static King createKing(GameUser gameUser, Direction direction, Point point) {
        return new King(gameUser, direction, point, NonLaserPiece.getInstance(), ImagePath.BLACK_KING);
    }

    public static Knight createSquareNight(Direction direction, Point point) {
        return new Knight(DOBY, direction, point, NonLaserPiece.getInstance(), HorizontalReflect.getInstance(), ImagePath.BLACK_SQUARE_KNIGHT);
    }

    public static Knight createTriangleNight(Direction direction, Point point) {
        return new Knight(DOBY, direction, point, NonLaserPiece.getInstance(), DiagonalReflect.getInstance(), ImagePath.BLACK_TRIANGLE_KNIGHT);
    }

    public static Splitter createSplitter(Direction direction, Point point) {
        return new Splitter(DOBY, direction, point, NonLaserPiece.getInstance(), DiagonalReflect.getInstance(), ImagePath.BLACK_SPLITTER);
    }

    public static Laser createLaser(Direction direction, Point point) {
        return new Laser(DOBY, direction, point, LaserPiece.getInstance(), ImagePath.BLACK_LASER);
    }

}
