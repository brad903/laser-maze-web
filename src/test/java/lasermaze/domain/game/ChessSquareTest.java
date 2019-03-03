package lasermaze.domain.game;


import lasermaze.domain.game.piece.Dummy;
import lasermaze.domain.game.piece.King;
import lasermaze.domain.game.piece.Laser;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.ImagePath;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.NonLaserPiece;
import lasermaze.domain.game.user.UserDelimiter;
import lasermaze.support.fixture.PieceFixture;
import org.junit.Test;
import org.slf4j.Logger;

import static lasermaze.domain.game.user.UserTest.BRAD;
import static lasermaze.domain.game.user.UserTest.DOBY;
import static org.slf4j.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

public class ChessSquareTest {
    private static final Logger log = getLogger(ChessSquareTest.class);

    public static final ChessSquare CHESS_SQUARE = new ChessSquare();

    @Test
    public void putSymmetryPieces() {
        CHESS_SQUARE.putSymmetryPieces(UserDelimiter.WHITE, new King(UserDelimiter.BLACK, Direction.EAST, new Point(4, 0), NonLaserPiece.getInstance(), ImagePath.BLACK_KING));
        Point point = new Point(3, 7);
        assertThat(CHESS_SQUARE.getPiece(point)).isEqualTo(PieceFixture.createKing(UserDelimiter.WHITE, Direction.WEST, point));
    }

    @Test
    public void 왕_장기확인() {
        CHESS_SQUARE.pieceInit();
        assertThat(CHESS_SQUARE.getPiece(new Point(4, 0)) instanceof King).isTrue();
    }


    @Test
    public void getLaser() {
        CHESS_SQUARE.pieceInit();
        Laser laser = CHESS_SQUARE.getLaser(DOBY);
        assertThat(laser instanceof Laser).isTrue();
    }

    @Test
    public void getLaser2() {
        CHESS_SQUARE.pieceInit();
        Laser laser = CHESS_SQUARE.getLaser(BRAD);
        assertThat(laser instanceof Laser).isTrue();
    }

    @Test
    public void pieceInitTest() {
        CHESS_SQUARE.pieceInit();
        log.debug("piece : {}", CHESS_SQUARE.getPiece(new Point(4, 0)));
        assertThat(CHESS_SQUARE.getPiece(new Point(4, 0)) instanceof King).isTrue();
    }

    @Test
    public void swap() {
        ChessSquare chessSquare = new ChessSquare();
        chessSquare.pieceInit();
        chessSquare.swap(new Point(3, 7), Direction.WEST);
        assertThat(chessSquare.getPiece(new Point(3, 6)) instanceof King).isTrue();
        assertThat(chessSquare.getPiece(new Point(3, 7)) instanceof Dummy).isTrue();
    }
}