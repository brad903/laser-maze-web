package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.support.fixture.LaserPointerFixture;
import lasermaze.support.fixture.PieceFixture;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TriangleKnightTest {

    @Test
    public void move() {
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, new Point(3, 3));
        triangleNight.move(Direction.NORTHWEST);
        assertThat(triangleNight.direction).isEqualTo(Direction.NORTHWEST);
        assertThat(triangleNight.point).isEqualTo(new Point(2, 2));
    }

    @Test
    public void move2() {
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, new Point(3, 3));
        triangleNight.move(Direction.SOUTHEAST);
        assertThat(triangleNight.direction).isEqualTo(Direction.NORTHWEST);
        assertThat(triangleNight.point).isEqualTo(new Point(4, 4));
    }

    @Test
    public void roate() {
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, new Point(3, 3));
        triangleNight.rotate(Rotation.COUNTERCLOCKWISE);
        assertThat(triangleNight.direction).isEqualTo(Direction.SOUTHWEST);
        assertThat(triangleNight.point).isEqualTo(new Point(3, 3));
    }

    @Test
    public void hit_반사할경우() {
        Point point = new Point(3, 3);
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.EAST, point);
        triangleNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.NORTH, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void hit_반사할경우2() {
        Point point = new Point(3, 3);
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.SOUTH, point);
        triangleNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.WEST, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void canRemove() {
        Point point = new Point(3, 3);
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, point);
        LaserPointer laserPointer = LaserPointerFixture.createLaserPointer(Direction.NORTH, point);
        triangleNight.hit(laserPointer);
        assertThat(laserPointer.isEnd()).isTrue();
    }

    @Test
    public void hit_죽는경우() {
        Point point = new Point(3, 3);
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.NORTH, point);
        triangleNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createEndLaserPointer(Direction.NORTH, point));
        assertThat(laserPointer.isEnd()).isTrue();
    }

    @Test
    public void hit_죽는경우2() {
        Point point = new Point(3, 3);
        Knight triangleNight = PieceFixture.createTriangleNight(Direction.SOUTHEAST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.SOUTH, point);
        triangleNight.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createEndLaserPointer(Direction.SOUTH, point));
        assertThat(laserPointer.isEnd()).isTrue();
    }

}