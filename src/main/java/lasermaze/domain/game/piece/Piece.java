package lasermaze.domain.game.piece;

import lasermaze.domain.game.piece.common.Direction;
import lasermaze.domain.game.piece.common.ImagePath;
import lasermaze.domain.game.piece.common.Point;
import lasermaze.domain.game.piece.common.Rotation;
import lasermaze.domain.game.piece.properties.Playable;
import lasermaze.domain.game.user.GameUser;
import lasermaze.domain.game.user.UserDelimiter;
import lasermaze.dto.PieceDto;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class Piece implements Pieceable, Cloneable {
    private static final Logger log = getLogger(Piece.class);

    private UserDelimiter userDelimiter;
    protected Direction direction;
    protected Point point;
    private Playable playable;
    private ImagePath imagePath;

    public Piece(UserDelimiter userDelimiter, Direction direction, Point point, Playable playable, ImagePath imagePath) {
        this.userDelimiter = userDelimiter;
        this.direction = direction;
        this.point = point;
        this.playable = playable;
        this.imagePath = imagePath;
    }

    public Piece makeEnemy(UserDelimiter userDelimiter) throws CloneNotSupportedException {
        Piece enemy = clone();
        enemy.point = point.getSymmetrical();
        enemy.direction = direction.getDiametricalDirection();
        enemy.userDelimiter = userDelimiter;
        enemy.imagePath = imagePath.makeEnemy();
        return enemy;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isSameUser(GameUser gameUser) {
        return gameUser.isSameDelimiter(userDelimiter);
    }

    @Override
    public void move(Direction direction) {
        playable.move(point, direction);
    }

    @Override
    public void rotate(Rotation rotation) {
        direction = playable.rotate(direction, rotation);
    }

    public PieceDto _toDto() {
        return new PieceDto(direction.toString(), imagePath.getImagePath());
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
        return userDelimiter == piece.userDelimiter &&
                direction == piece.direction &&
                Objects.equals(point, piece.point) &&
                Objects.equals(playable, piece.playable) &&
                imagePath == piece.imagePath;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDelimiter, direction, point, playable, imagePath);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "userDelimiter=" + userDelimiter +
                ", direction=" + direction +
                ", point=" + point +
                ", playable=" + playable +
                ", imagePath=" + imagePath +
                '}';
    }
}
