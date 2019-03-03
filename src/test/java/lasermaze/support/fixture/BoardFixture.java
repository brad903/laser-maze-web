package lasermaze.support.fixture;

import lasermaze.domain.game.ChessSquare;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.user.UserDelimiter;

public class BoardFixture {

    public static void putDummy(ChessSquare chessSquare, Point point) {
        chessSquare.putPiece(point, PieceFixture.createDummy(point));
    }

    public static void putKing(ChessSquare chessSquare, UserDelimiter userDelimiter, Direction direction, Point point) {
        chessSquare.putPiece(point, PieceFixture.createKing(userDelimiter, direction, point));
    }

    public static void putSquareNight(ChessSquare chessSquare, Direction direction, Point point) {
        chessSquare.putPiece(point, PieceFixture.createSquareNight(direction, point));
    }

    public static void putTriangleNight(ChessSquare chessSquare, Direction direction, Point point) {
        chessSquare.putPiece(point, PieceFixture.createTriangleNight(direction, point));
    }

    public static void putSplitter(ChessSquare chessSquare, Direction direction, Point point) {
        chessSquare.putPiece(point, PieceFixture.createSplitter(direction, point));
    }

    public static void putLaser(ChessSquare chessSquare, Direction direction, Point point) {
        chessSquare.putPiece(point, PieceFixture.createLaser(direction, point));
    }
}
