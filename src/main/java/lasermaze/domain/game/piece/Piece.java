package lasermaze.domain.game.piece;

import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.domain.game.piece.properties.Playable;
import lasermaze.domain.game.user.GameUser;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class Piece implements Pieceable, Cloneable {
    private static final Logger log = getLogger(Piece.class);

    private GameUser gameUser;
    protected Direction direction;
    protected Point point;
    private Playable playable;

    public Piece(GameUser gameUser, Direction direction, Point point, Playable playable) {
        this.gameUser = gameUser;
        this.direction = direction;
        this.point = point;
        this.playable = playable;
    }

    public Piece makeEnemy(GameUser gameUser) throws CloneNotSupportedException {
        Piece enemy = clone();
        enemy.point = point.getSymmetrical();
        enemy.direction = direction.getDiametricalDirection();
        enemy.gameUser = gameUser;
        return enemy;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isSameUser(GameUser gameUser) {
        return this.gameUser.equals(gameUser);
    }

    @Override
    public void move(Direction direction) {
        playable.move(point, direction);
    }

    @Override
    public void rotate(Rotation rotation) {
        direction = playable.rotate(direction, rotation);
    }

    @Override
    public Piece clone() throws CloneNotSupportedException {
        return (Piece) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(gameUser, piece.gameUser) &&
                direction == piece.direction &&
                Objects.equals(point, piece.point) &&
                Objects.equals(playable, piece.playable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameUser, direction, point, playable);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "gameUser=" + gameUser +
                ", direction=" + direction +
                ", point=" + point +
                ", playable=" + playable +
                '}';
    }
}
