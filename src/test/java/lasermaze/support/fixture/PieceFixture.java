package lasermaze.support.fixture;

import lasermaze.domain.game.piece.*;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.ImagePath;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.*;
import lasermaze.domain.game.user.UserDelimiter;

public class PieceFixture {

    public static Dummy createDummy(Point point) {
        return new Dummy(UserDelimiter.DUMMY, Direction.NONE, point, CommonPlay.getInstance(), ImagePath.DUMMY);
    }

    public static King createKing(UserDelimiter userDelimiter, Direction direction, Point point) {
        if(userDelimiter.getDelimiter().equals(UserDelimiter.BLACK)) return new King(userDelimiter, direction, point, NonLaserPiece.getInstance(), ImagePath.BLACK_KING);
        return new King(userDelimiter, direction, point, NonLaserPiece.getInstance(), ImagePath.WHITE_KING);
    }

    public static Knight createSquareNight(Direction direction, Point point) {
        return new Knight(UserDelimiter.BLACK, direction, point, NonLaserPiece.getInstance(), HorizontalReflect.getInstance(), ImagePath.BLACK_SQUARE_KNIGHT);
    }

    public static Knight createTriangleNight(Direction direction, Point point) {
        return new Knight(UserDelimiter.BLACK, direction, point, NonLaserPiece.getInstance(), DiagonalReflect.getInstance(), ImagePath.BLACK_TRIANGLE_KNIGHT);
    }

    public static Splitter createSplitter(Direction direction, Point point) {
        return new Splitter(UserDelimiter.BLACK, direction, point, NonLaserPiece.getInstance(), DiagonalReflect.getInstance(), ImagePath.BLACK_SPLITTER);
    }

    public static Laser createLaser(Direction direction, Point point) {
        return new Laser(UserDelimiter.BLACK, direction, point, LaserPiece.getInstance(), ImagePath.BLACK_LASER);
    }

}
