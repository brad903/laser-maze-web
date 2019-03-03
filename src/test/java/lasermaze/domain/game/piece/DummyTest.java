package lasermaze.domain.game.piece;

import lasermaze.domain.game.NotSupportedException;
import lasermaze.support.fixture.PieceFixture;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import org.junit.Test;

public class DummyTest {

    @Test(expected = NotSupportedException.class)
    public void move() {
        Dummy dummy = PieceFixture.createDummy(new Point(3, 3));
        dummy.move(Direction.EAST);
    }

    @Test(expected = NotSupportedException.class)
    public void rotate() {
        Dummy dummy = PieceFixture.createDummy(new Point(3, 3));
        dummy.rotate(Rotation.CLOCKWISE);
    }
}