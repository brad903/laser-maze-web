package lasermaze.domain.game.piece.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @Test
    public void getRotatedDirection() {
        Direction direction = Direction.NORTH;
        Rotation rotation = Rotation.COUNTERCLOCKWISE;
        assertThat(direction.getRotatedDirection(rotation, 2)).isEqualTo(Direction.WEST);
    }

    @Test
    public void getRotatedDirection2() {
        Direction direction = Direction.WEST;
        Rotation rotation = Rotation.CLOCKWISE;
        assertThat(direction.getRotatedDirection(rotation, 2)).isEqualTo(Direction.NORTH);
    }

    @Test
    public void getRotatedDirection3() {
        Direction direction = Direction.SOUTH;
        Rotation rotation = Rotation.CLOCKWISE;
        assertThat(direction.getRotatedDirection(rotation, 2)).isEqualTo(Direction.WEST);
    }

    @Test
    public void getRotatedDirection4() {
        Direction direction = Direction.SOUTH;
        Rotation rotation = Rotation.COUNTERCLOCKWISE;
        assertThat(direction.getRotatedDirection(rotation, 2)).isEqualTo(Direction.EAST);
    }

    @Test
    public void getRotatedDirection5() {
        Direction direction = Direction.NORTHEAST;
        Rotation rotation = Rotation.COUNTERCLOCKWISE;
        assertThat(direction.getRotatedDirection(rotation, 2)).isEqualTo(Direction.NORTHWEST);
    }

    @Test
    public void getRotatedDirection6() {
        Direction direction = Direction.EAST;
        Rotation rotation = Rotation.COUNTERCLOCKWISE;
        assertThat(direction.getRotatedDirection(rotation, 2)).isEqualTo(Direction.NORTH);
    }

    @Test
    public void getDiametricalDirection() {
        Direction direction = Direction.NORTHEAST;
        assertThat(direction.getDiametricalDirection()).isEqualTo(Direction.SOUTHWEST);
    }

    @Test
    public void getDiametricalNumber() {
        Direction direction = Direction.NORTH;
        assertThat(direction.getDiametricalNumber()).isEqualTo(5);
    }

    @Test
    public void getDiametricalNumber2() {
        Direction direction = Direction.WEST;
        assertThat(direction.getDiametricalNumber()).isEqualTo(3);
    }

    @Test
    public void canDead() {
        Direction direction = Direction.NORTH;
        assertThat(!direction.isReflectable(Direction.SOUTH)).isEqualTo(false);
    }

    @Test
    public void canDead2() {
        Direction direction = Direction.NORTH;
        assertThat(!direction.isReflectable(Direction.EAST)).isEqualTo(true);
    }

    @Test
    public void canDead3() {
        Direction direction = Direction.EAST;
        assertThat(!direction.isReflectable(Direction.NORTHWEST)).isEqualTo(false);
    }

    @Test
    public void canDead4() {
        Direction direction = Direction.EAST;
        assertThat(!direction.isReflectable(Direction.NORTHEAST)).isEqualTo(true);
    }

    @Test
    public void getTriangleRotation() {
        Direction laserDirection = Direction.SOUTH;
        assertThat(laserDirection.getTriangleRotation(Direction.NORTHWEST)).isEqualTo(Rotation.CLOCKWISE);
    }

    @Test
    public void getTriangleRotation2() {
        Direction laserDirection = Direction.SOUTH;
        assertThat(laserDirection.getTriangleRotation(Direction.NORTHEAST)).isEqualTo(Rotation.COUNTERCLOCKWISE);
    }

    @Test
    public void getTriangleRotation3() {
        Direction laserDirection = Direction.EAST;
        assertThat(laserDirection.getTriangleRotation(Direction.NORTHEAST)).isEqualTo(Rotation.COUNTERCLOCKWISE);
    }

    @Test
    public void getTriangleRotation4() {
        Direction laserDirection = Direction.WEST;
        assertThat(laserDirection.getTriangleRotation(Direction.SOUTHEAST)).isEqualTo(Rotation.COUNTERCLOCKWISE);
    }
}