package lasermaze.domain.game;

import lasermaze.domain.game.piece.*;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.*;
import lasermaze.domain.game.user.GameUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ChessSquare {
    private static final Logger log = LoggerFactory.getLogger(ChessSquare.class);

    public static final int CHESSBOARD_SIZE = 8;

    private List<List<Piece>> chessSquares = new ArrayList<>();
    private GameUser gameUser1;
    private GameUser gameUser2;

    public ChessSquare(GameUser gameUser1, GameUser gameUser2) {
        this.gameUser1 = gameUser1;
        this.gameUser2 = gameUser2;
        dummyInit();
    }

    private void dummyInit() {
        for (int row = 0; row < CHESSBOARD_SIZE; row++) {
            List<Piece> line = new ArrayList<>();
            for (int col = 0; col < CHESSBOARD_SIZE; col++) {
                line.add(createDummy(new Point(row, col)));
            }
            this.chessSquares.add(line);
        }
    }

    public ChessSquare pieceInit() {
        putSymmetryPieces(gameUser2, new King(gameUser1, Direction.EAST, new Point(4, 0), NonLaserPiece.getInstance()));
        putSymmetryPieces(gameUser2, new Laser(gameUser1, Direction.EAST, new Point(7, 0), LaserPiece.getInstance()));
        putSymmetryPieces(gameUser2, new Splitter(gameUser1, Direction.NORTHEAST, new Point(7, 7), NonLaserPiece.getInstance(), DiagonalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.NORTHWEST, new Point(7, 4), NonLaserPiece.getInstance(), DiagonalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.NORTHWEST, new Point(1, 7), NonLaserPiece.getInstance(), DiagonalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.NORTHEAST, new Point(2, 0), NonLaserPiece.getInstance(), DiagonalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.NORTHEAST, new Point(3, 3), NonLaserPiece.getInstance(), DiagonalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.SOUTHEAST, new Point(4, 3), NonLaserPiece.getInstance(), DiagonalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.EAST, new Point(3, 0), NonLaserPiece.getInstance(), HorizontalReflect.getInstance()));
        putSymmetryPieces(gameUser2, new Knight(gameUser1, Direction.EAST, new Point(5, 0), NonLaserPiece.getInstance(), HorizontalReflect.getInstance()));
        return this;
    }

    public void putSymmetryPieces(GameUser otherGameUser, Piece piece) {
        try {
            Point point = piece.getPoint();
            putPiece(point, piece);
            putPiece(point.getSymmetrical(), piece.makeEnemy(otherGameUser));
        } catch (CloneNotSupportedException e) {
            log.error("말 생성 오류 발생 : {}", e);
        }
    }

    public Dummy createDummy(Point point) {
        return new Dummy(GameUser.DUMMY_GAME_USER, Direction.NONE, point, CommonPlay.getInstance());
    }

    public void putPiece(Point point, Piece piece) {
        chessSquares.get(point.getRow()).set(point.getCol(), piece);
    }

    public Piece getPiece(Point point) {
        return chessSquares.get(point.getRow()).get(point.getCol());
    }

    public void swap(Point prevPoint, Direction direction) {
        Point nextPoint = prevPoint.getNextPoint(direction);
        putPiece(nextPoint, getPiece(prevPoint));
        putPiece(prevPoint, createDummy(prevPoint));
    }

    public boolean isDummy(Point point) {
        return getPiece(point) instanceof Dummy;
    }

    public Laser getLaser(GameUser gameUser) {
        for (List<Piece> line : chessSquares) {
            for (Piece piece : line) {
                if (piece.isSameUser(gameUser) && piece instanceof Laser) return (Laser) piece;
            }
        }
        throw new NotSupportedException("cannot find laser piece");
    }
}
