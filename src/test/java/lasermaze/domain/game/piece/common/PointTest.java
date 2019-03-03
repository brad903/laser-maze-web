package lasermaze.domain.game.piece.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PointTest {

    @Test
    public void getNextPoint() {
        Point point = new Point(4, 1);
        Point nextPoint = point.getNextPoint(Direction.EAST);
        assertThat(nextPoint.equals(new Point(4, 2))).isTrue();
    }

    @Test
    public void modify() {
        Point piecePoint = new Point(4, 0);
        piecePoint.modify(Direction.EAST);
        assertThat(piecePoint).isEqualTo(new Point(4, 1));
    }

    @Test
    public void getOpposite() {
        Point point = new Point(3, 4);
        assertThat(point.getSymmetrical()).isEqualTo(new Point(4, 3));
    }

    @Test
    public void getOpposite2() {
        Point point = new Point(1, 6);
        assertThat(point.getSymmetrical()).isEqualTo(new Point(6, 1));
    }

    @Test
    public void isOutOfBound() {
        Point nextPoint = new Point(-1, 0);
        assertThat(nextPoint.isOutOfBound()).isTrue();
    }

}