package lasermaze.domain.game.piece;

import lasermaze.domain.game.NotSupportedException;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.properties.LaserPiece;
import org.junit.Test;

public class LaserPieceTest {

    @Test(expected = NotSupportedException.class)
    public void 레이저말_이동() {
        LaserPiece laserPiece = new LaserPiece();
        laserPiece.move(new Point(7, 0), Direction.NORTHEAST);
    }
}