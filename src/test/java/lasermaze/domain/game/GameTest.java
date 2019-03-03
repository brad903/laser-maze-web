package lasermaze.domain.game;

import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.support.fixture.PieceFixture;
import org.junit.Before;
import org.junit.Test;

import static lasermaze.domain.game.user.UserTest.BRAD;
import static lasermaze.domain.game.user.UserTest.DOBY;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private ChessSquare chessSquare = new ChessSquare(DOBY, BRAD);
    private Board board;

    @Before
    public void setUp() throws Exception {
         board = new Board(chessSquare);
    }

    @Test
    public void winnerCheck() {
        Game game = new Game(DOBY, BRAD);
        chessSquare.putPiece(new Point(4, 0), PieceFixture.createKing(DOBY, Direction.EAST, new Point(4, 0)));
        Board board = new Board(chessSquare);
        assertThat(game.getResult(board)).isEqualTo(GameResult.USER1);
    }

    @Test
    public void winnerCheck2() {
        Game game = new Game(DOBY, BRAD);
        chessSquare.putPiece(new Point(4, 0), PieceFixture.createKing(DOBY, Direction.EAST, new Point(4, 0)));
        chessSquare.putPiece(new Point(4, 7), PieceFixture.createKing(BRAD, Direction.WEST, new Point(4, 7)));
        Board board = new Board(chessSquare);
        assertThat(game.getResult(board)).isEqualTo(GameResult.NOT_DECIDED);
    }

    @Test
    public void winnerCheck3() {
        Game game = new Game(DOBY, BRAD);
        Board board = new Board(chessSquare);
        assertThat(game.getResult(board)).isEqualTo(GameResult.DRAW);
    }

    @Test
    public void winnerCheck4() {
        Game game = new Game(DOBY, BRAD);
        chessSquare.putPiece(new Point(4, 0), PieceFixture.createKing(BRAD, Direction.EAST, new Point(4, 0)));
        Board board = new Board(chessSquare);
        assertThat(game.getResult(board)).isEqualTo(GameResult.USER2);
    }
}