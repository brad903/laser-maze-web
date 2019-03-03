package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.support.fixture.LaserPointerFixture;
import lasermaze.support.fixture.PieceFixture;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareKnightTest {

    @Test
    public void move() {
        Knight squareNight = PieceFixture.createSquareNight(Direction.WEST, new Point(3, 3));
        squareNight.move(Direction.NORTHWEST);
        assertThat(squareNight.direction).isEqualTo(Direction.WEST);
        assertThat(squareNight.point).isEqualTo(new Point(2, 2));
    }

    @Test
    public void move2() {
        Knight squareNight = PieceFixture.createSquareNight(Direction.WEST, new Point(3, 3));
        squareNight.move(Direction.SOUTHEAST);
        assertThat(squareNight.direction).isEqualTo(Direction.WEST);
        assertThat(squareNight.point).isEqualTo(new Point(4, 4));
    }

    @Test
    public void roate() {
        Knight squareNight = PieceFixture.createSquareNight(Direction.WEST, new Point(3, 3));
        squareNight.rotate(Rotation.COUNTERCLOCKWISE);
        assertThat(squareNight.direction).isEqualTo(Direction.SOUTH);
        assertThat(squareNight.point).isEqualTo(new Point(3, 3));
    }

    @Test
    public void hit_반사할경우() {
        Point point = new Point(3, 3);
        Knight squareNight = PieceFixture.createSquareNight(Direction.WEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.EAST, point);
        squareNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.WEST, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void hit_죽는경우() {
        Point point = new Point(3, 3);
        Knight squareNight = PieceFixture.createSquareNight(Direction.WEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.WEST, point);
        squareNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createEndLaserPointer(Direction.WEST, point));
        assertThat(laserPointer.isEnd()).isTrue();
    }

    @Test
    public void hit_죽는경우2() {
        Point point = new Point(3, 3);
        Knight squareNight = PieceFixture.createSquareNight(Direction.WEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.NORTH, point);
        squareNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createEndLaserPointer(Direction.NORTH, point));
        assertThat(laserPointer.isEnd()).isTrue();
    }
}