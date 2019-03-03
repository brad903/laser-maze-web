package lasermaze.domain.game.piece;

import lasermaze.domain.game.LaserPointer;
import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.support.fixture.LaserPointerFixture;
import lasermaze.support.fixture.PieceFixture;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitterTest {
    private static final Logger log = LoggerFactory.getLogger(SplitterTest.class);

    @Test
    public void move() {
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, new Point(3, 3));
        splitter.move(Direction.NORTHWEST);
        assertThat(splitter.direction).isEqualTo(Direction.NORTHWEST);
        assertThat(splitter.point).isEqualTo(new Point(2, 2));
    }

    @Test
    public void move2() {
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, new Point(3, 3));
        splitter.move(Direction.SOUTHEAST);
        assertThat(splitter.direction).isEqualTo(Direction.NORTHWEST);
        assertThat(splitter.point).isEqualTo(new Point(4, 4));
    }

    @Test
    public void roate() {
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, new Point(3, 3));
        splitter.rotate(Rotation.COUNTERCLOCKWISE);
        assertThat(splitter.direction).isEqualTo(Direction.SOUTHWEST);
        assertThat(splitter.point).isEqualTo(new Point(3, 3));
    }

    @Test
    public void hit_반사할경우() {
        Point point = new Point(3, 3);
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.EAST, point);
        splitter.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.NORTH, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void hit_반사할경우2() {
        Point point = new Point(3, 3);
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.SOUTH, point);
        splitter.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.WEST, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void hit_반사할경우3() {
        Point point = new Point(3, 3);
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.NORTH, point);
        splitter.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.EAST, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void hit_반사할경우4() {
        Point point = new Point(3, 3);
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, point);
        LaserPointer laserPointer = new LaserPointer(Direction.WEST, point);
        splitter.hit(laserPointer);
        assertThat(laserPointer).isEqualTo(LaserPointerFixture.createLaserPointer(Direction.SOUTH, point));
        assertThat(laserPointer.isEnd()).isFalse();
    }

    @Test
    public void canRemove() {
        Point point = new Point(3, 3);
        Splitter splitter = PieceFixture.createSplitter(Direction.NORTHWEST, point);
        LaserPointer laserPointer = LaserPointerFixture.createLaserPointer(Direction.NORTH, point);
        splitter.hit(laserPointer);
        assertThat(laserPointer.isEnd()).isFalse();
    }
}